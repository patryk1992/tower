package com.Client;

import com.Client.Packet.Packet0LoginRequest;
import com.Client.Packet.Packet1LoginAnswer;
import com.Client.Packet.Packet2Message;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;

public class NetworkListener extends Listener {
	private Client client;
	GameRenderer renderer;

	public void init(Client client, GameRenderer renderer) {
		this.client = client;
		this.renderer = renderer;
	}

	@Override
	public void connected(Connection arg0) {
		Log.info("[CLIENT]you have connected");
		Packet0LoginRequest packet = new Packet0LoginRequest();
		renderer.getHud().setConnectionId(arg0.getID());
		renderer.getHud().intHUD();
		client.sendTCP(packet);
	}

	@Override
	public void disconnected(Connection arg0) {
		Log.info("[Client]You have disconnected");
	}

	@Override
	public void received(Connection c, Object o) {
		
		if (o instanceof GameWorld) {
			GameWorld gameWorld = ((GameWorld) o);
			renderer.setMyWorld(gameWorld);
			
		}
		else if (o instanceof Packet1LoginAnswer) {
			boolean answer = ((Packet1LoginAnswer) o).accepted;

		}
		else if (o instanceof Packet2Message) {
			String message = ((Packet2Message) o).message;
			Log.info("[Client]" + message);
		}
		
		
	}

}
