package myServer;

import java.io.IOException;

import myServer.Packet.Packet0LoginRequest;
import myServer.Packet.Packet1LoginAnswer;
import myServer.Packet.Packet2Message;

import com.Client.packets.Packet3CreateTowerRequest;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameworld.GameWorld;
import com.server.logic.ServerGameWorld;

public class MyServer {
	public Server server;
	public NetworkListener networkListener;
	
	public MyServer() throws IOException{
		
		server = new Server();
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
		kryo.register(Packet3CreateTowerRequest.class);
		
	}
	
}
