package com.Client;

import com.Client.packets.Packet.Packet0LoginRequest;
import com.Client.packets.Packet.Packet1LoginAnswer;
import com.Client.packets.Packet.Packet2Message;
import com.Client.packets.Packet.PacketEndGame;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;

public class NetworkListener extends Listener {
	GameRenderer renderer;	
	
	public void init( GameRenderer renderer) {		
		this.renderer = renderer;
	}

	@Override
	public void connected(Connection arg0) {
		Log.info("[CLIENT]you have connected");
		Packet0LoginRequest packet = new Packet0LoginRequest();
		renderer.getHud().setConnectionId(arg0.getID());
		renderer.getHud().intHUD();
		arg0.sendTCP(packet);		
	}

	@Override
	public void disconnected(Connection arg0) {
		Log.info("[Client]You have disconnected");
		renderer.getHud().setEndGame(true);
		renderer.getHud().setIdWinner(0);
	}

	@Override
	public void received(Connection c, Object o) {
		
		if (o instanceof GameWorld) {
			GameWorld gameWorld = ((GameWorld) o);
			renderer.setMyWorld(gameWorld);
			
		}
		else if (o instanceof Packet1LoginAnswer) {
			Packet1LoginAnswer answer =  (Packet1LoginAnswer) o;
			renderer.getHud().setConnections(answer.connections);
		}
		else if (o instanceof Packet2Message) {
			String message = ((Packet2Message) o).message;
			Log.info("[Client]" + message);
		}else if (o instanceof PacketEndGame) {
					
			int idWinner=((PacketEndGame) o).idWinner;
			renderer.getHud().setIdWinner(idWinner);
			renderer.getHud().setEndGame(true);
			
		}
		
		
	}

}
