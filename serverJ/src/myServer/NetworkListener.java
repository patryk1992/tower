package myServer;

import java.util.UUID;

import myServer.Packet.Packet0LoginRequest;
import myServer.Packet.Packet1LoginAnswer;
import myServer.Packet.Packet2Message;

import com.Client.packets.Packet3CreateTowerRequest;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Tower;
import com.server.logic.ServerGameWorld;

public class NetworkListener extends Listener {
	
	private ServerGameWorld serverGameWorld;
	
	public void setServerGameWorld(ServerGameWorld serverGameWorld) {
		this.serverGameWorld = serverGameWorld;
	}

	@Override
	public void connected(Connection arg0) {
		Log.info("[SERVER]Someone is trying to connected");
	}

	@Override
	public void disconnected(Connection arg0) {
		Log.info("[SERVER]Someone is trying to disconnected");
	}

	@Override
	public void received(Connection c, Object o) {
		
		if(o instanceof Packet0LoginRequest){
			Packet1LoginAnswer loginAnswer= new Packet1LoginAnswer();
			loginAnswer.accepted=true;
			c.sendTCP(loginAnswer);
			
		}
		else if(o instanceof Packet3CreateTowerRequest){
			Packet3CreateTowerRequest request=(Packet3CreateTowerRequest)o;	
			Tower tower=new Tower(request.position.x-25,request.position.y-25,50,50,c.getID(),UUID.randomUUID().toString());
			if(c.getID()==1&&request.position.x<640){
				synchronized(serverGameWorld.getGameWorld()){
					serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(tower);
				}
			}
			else if(c.getID()==2&&request.position.x>640){
				synchronized(serverGameWorld.getGameWorld()){
					serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(tower);
				}
			}
			
		}
	
		
	}
}
