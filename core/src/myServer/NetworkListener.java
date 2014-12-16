package myServer;

import java.util.UUID;

import com.Client.packets.Packet.Packet0LoginRequest;
import com.Client.packets.Packet.Packet1LoginAnswer;
import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.Client.packets.Packet6CreateAttackPointRequest;
import com.Client.packets.Packet7ClickTowerRequest;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Tower;
import com.mygdx.simpleobjects.MyRectangle;
import com.server.logic.ServerGameWorld;

public class NetworkListener extends Listener {

	private ServerGameWorld serverGameWorld;

	public void setServerGameWorld(ServerGameWorld serverGameWorld) {
		this.serverGameWorld = serverGameWorld;
	}

	@Override
	public void connected(Connection arg0) {
		Log.info("[SERVER]Someone is trying to connected");
		float tmp=(float) 0.9;
		arg0.setIdleThreshold( tmp);
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
			if(serverGameWorld==null){
				loginAnswer.connections=0;
				c.sendTCP(loginAnswer);
			}else {
				loginAnswer.connections=serverGameWorld.getServer().getConnections().length;
				serverGameWorld.getServer().sendToAllTCP(loginAnswer);
			}
			
			
			
		}
		if(serverGameWorld!=null&&serverGameWorld.getServer().getConnections().length==2)
		{
			if(o instanceof Packet5CreateTowerRequest){
				Packet5CreateTowerRequest request=(Packet5CreateTowerRequest)o;	
				Tower tower=new Tower(request.position.x-25,request.position.y-25,50,50,1,c.getID(),UUID.randomUUID().toString());
				Base[] bases=serverGameWorld.getGameWorld().getCastles();
				if ((bases[0].getDimension().getPosition().x+((MyRectangle) bases[0].getDimension()).getWidth()+25 < request.position.x && c.getID() == 1)
						||( bases[1].getDimension().getPosition().x -25 > request.position.x && c.getID() == 2)) {
					if(serverGameWorld.getGameWorld().getCastles()[c.getID()-1].getCoins()>=100){//wybudowanie wie¿y kosztuje 100 coins
						serverGameWorld.getGameWorld().getCastles()[c.getID()-1].withdrawCoins(100);
						if(c.getID()==1&&request.position.x<615){
							if(tower.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
								synchronized(serverGameWorld.getGameWorld()){
									serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(tower);//-1 bo indeksacja listy jest od 0
								}
							}
						}
						else if(c.getID()==2&&request.position.x>665){
							if(tower.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
								synchronized(serverGameWorld.getGameWorld()){
									serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(tower);
								}
							}
						}
					}
				}
			}
			else if(o instanceof Packet3CreateFactoryRequest){
				Packet3CreateFactoryRequest request=(Packet3CreateFactoryRequest)o;
				Base[] bases=serverGameWorld.getGameWorld().getCastles();
				if ((bases[0].getDimension().getPosition().x+((MyRectangle) bases[0].getDimension()).getWidth()+25 < request.position.x && c.getID() == 1)
						||( bases[1].getDimension().getPosition().x -25 > request.position.x && c.getID() == 2)) {
					if(serverGameWorld.getGameWorld().getCastles()[c.getID()-1].getCoins()>=100){//wybudowanie wie¿y kosztuje 100 coins
						serverGameWorld.getGameWorld().getCastles()[c.getID()-1].withdrawCoins(100);
						Factory factory=new Factory(request.position.x-25,request.position.y-25,50,50,c.getID(),UUID.randomUUID().toString(),5,5);
						if(c.getID()==1&&request.position.x<640){
							if(factory.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
							synchronized(serverGameWorld.getGameWorld()){
								serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(factory);
							}}
						}
						else if(c.getID()==2&&request.position.x>640){
							if(factory.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
								synchronized(serverGameWorld.getGameWorld()){
									serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(factory);
								}
							}
						}
					}
				}
			}
			else if(o instanceof Packet4CreateMineRequest){
				Packet4CreateMineRequest request=(Packet4CreateMineRequest)o;
				Base[] bases=serverGameWorld.getGameWorld().getCastles();
				if ((bases[0].getDimension().getPosition().x+((MyRectangle) bases[0].getDimension()).getWidth()+25 < request.position.x && c.getID() == 1)
						||( bases[1].getDimension().getPosition().x -25 > request.position.x && c.getID() == 2)) {
					if(serverGameWorld.getGameWorld().getCastles()[c.getID()-1].getCoins()>=100){//wybudowanie wie¿y kosztuje 100 coins
						serverGameWorld.getGameWorld().getCastles()[c.getID()-1].withdrawCoins(100);
						Mine mine=new Mine(request.position.x-25,request.position.y-25,50,50,5,c.getID(),UUID.randomUUID().toString());
						if(c.getID()==1&&request.position.x<640){
							if(mine.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
								synchronized(serverGameWorld.getGameWorld()){		
									serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(mine);
								}
							}
						}
						
						else if(c.getID()==2&&request.position.x>640){
							if(mine.collides(serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1))==null){
								synchronized(serverGameWorld.getGameWorld()){
									serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1).add(mine);
								}
							}
						}
						
					}
				}
			}
			else if(o instanceof Packet6CreateAttackPointRequest){
				Packet6CreateAttackPointRequest request=(Packet6CreateAttackPointRequest)o;	
				synchronized(serverGameWorld.getGameWorld()){				
						serverGameWorld.getGameWorld().getTargetLine().get(c.getID()-1).set(1, new Vector2(request.position));
						serverGameWorld.getGameWorld().getTargetLine().get(c.getID()-1).set(0, new Vector2(640,request.position.y));
				}
				
			}
			else if(o instanceof Packet7ClickTowerRequest){
				Packet7ClickTowerRequest request=(Packet7ClickTowerRequest)o;
				for(Building building :serverGameWorld.getGameWorld().getTowerList().get(c.getID()-1) ){
	        		if(building.getId().equals(request.id)){
	        			if(building instanceof Factory){
		        			Factory factory=((Factory)building);        			
		        				synchronized(serverGameWorld.getGameWorld()){
		    						serverGameWorld.deployTanks(factory.getTankNumber(), c.getID());
		    						factory.setTankNumber(0);
		    	        			factory.setStoredTime(serverGameWorld.getTime());
		    					}        			
		        			
		        			}
	        			}
	        	}
				
				
				
			}
			
		}
	}
}
