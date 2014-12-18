package com.Client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;









import com.Client.packets.Packet.PacketEndGameAnswer;
import com.Client.packets.Packet.PacketEndGameRequest;
import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.Client.packets.Packet6CreateAttackPointRequest;
import com.Client.packets.Packet7ClickTowerRequest;
import com.Client.packets.Packet.Packet0LoginRequest;
import com.Client.packets.Packet.Packet1LoginAnswer;
import com.Client.packets.Packet.Packet2Message;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Plane;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.simpleobjects.Dimension;
import com.mygdx.simpleobjects.MyCircle;
import com.mygdx.simpleobjects.MyRectangle;

public class MyClient {
	public Client client;
	GameRenderer renderer;
	
	public MyClient( GameRenderer renderer)throws Exception{
		this.renderer=renderer;
		client= new Client(998192,9948192);
		register();
		
		NetworkListener nl = new NetworkListener();
		nl.init(renderer);
		client.addListener(nl);		
		client.start();
		
		InetAddress adressIP=client.discoverHost(43445, 3000);		
		Log.info("Please enter IP: ");
		client.connect(500000,adressIP, 54556,43445);
		
	}
	
	private void register(){
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Base[].class);
		kryo.register(Base.class);
		kryo.register(Vector2.class);
		kryo.register(GameWorld.class);
		kryo.register(GameObject.class);
		kryo.register(Color.class);
		kryo.register(Packet3CreateFactoryRequest.class);
		kryo.register(Packet4CreateMineRequest.class);
		kryo.register(Packet5CreateTowerRequest.class);
		kryo.register(ArrayList.class);
		kryo.register(Building.class);
		kryo.register(Dimension.class);
		kryo.register(Factory.class);
		kryo.register(Mine.class);
		kryo.register(Tower.class);
		kryo.register(Packet6CreateAttackPointRequest.class);
		kryo.register(Packet7ClickTowerRequest.class);
		kryo.register(Plane.class);
		kryo.register(MyRectangle.class);
		kryo.register(Bullet.class);
		kryo.register(MyCircle.class);
		kryo.register(PacketEndGameRequest.class);
		kryo.register(PacketEndGameAnswer.class);
	}
	

}
