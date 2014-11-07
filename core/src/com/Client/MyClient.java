package com.Client;

import java.io.IOException;
import java.util.Scanner;















import com.Client.Packet.Packet0LoginRequest;
import com.Client.Packet.Packet1LoginAnswer;
import com.Client.Packet.Packet2Message;
import com.Client.packets.Packet3CreateTowerRequest;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;

public class MyClient {
	public Client client;
	GameRenderer renderer;
	
	public MyClient(String IP, GameRenderer renderer){
		this.renderer=renderer;
		client= new Client();
		register();
		
		NetworkListener nl = new NetworkListener();
		nl.init(client,renderer);
		client.addListener(nl);		
		client.start();
		
		try {
			Log.info("Please enter IP: ");
			client.connect(500000,IP, 54556);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void register(){
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Castle[].class);
		kryo.register(Castle.class);
		kryo.register(Vector2.class);
		kryo.register(GameWorld.class);
		kryo.register(GameObject.class);
		kryo.register(Color.class);
		kryo.register(Packet3CreateTowerRequest.class);
	}
	

}
