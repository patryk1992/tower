package com.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;























import com.Client.Packet.Packet0LoginRequest;
import com.Client.Packet.Packet1LoginAnswer;
import com.Client.Packet.Packet2Message;
import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.MyInterface;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;

public class MyClient {
	public Client client;
	GameRenderer renderer;
	
	public MyClient(String IP, GameRenderer renderer){
		this.renderer=renderer;
		client= new Client(8192,18192);
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
		kryo.register(Packet3CreateFactoryRequest.class);
		kryo.register(Packet4CreateMineRequest.class);
		kryo.register(Packet5CreateTowerRequest.class);
		kryo.register(ArrayList.class);
		kryo.register(Building.class);
		kryo.register(MyInterface.class);
		kryo.register(Factory.class);
		kryo.register(Mine.class);
		kryo.register(Tower.class);
		
		
		
	}
	

}
