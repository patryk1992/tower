package myServer;

import java.io.IOException;
import java.util.ArrayList;

import myServer.Packet.Packet0LoginRequest;
import myServer.Packet.Packet1LoginAnswer;
import myServer.Packet.Packet2Message;

import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.Client.packets.Packet6CreateAttackPointRequest;
import com.Client.packets.Packet7ClickTowerRequest;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.MyInterface;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Tank;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameworld.GameWorld;
import com.server.logic.ServerGameWorld;

public class MyServer {
	public Server server;
	public NetworkListener networkListener;
	
	public MyServer() throws IOException{
		
		server = new Server(18192,8192);
		registerPackets();
		networkListener=new NetworkListener();
		server.addListener(networkListener);
		server.bind(54556);
		server.start();		
	}
	
	private void registerPackets(){
		Kryo kryo = server.getKryo();
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
		kryo.register(Packet6CreateAttackPointRequest.class);
		kryo.register(Packet7ClickTowerRequest.class);
		kryo.register(Tank.class);
		
	}
	
}
