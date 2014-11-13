package myServer;

import java.io.IOException;

import myServer.Packet.Packet2Message;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameworld.GameWorld;
import com.server.logic.ServerGameWorld;

public class LogicGame {
	static ServerGameWorld serverGameWorld;
	static int delta = 0;
	static int fps=60;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer myServer = null;
		long time;
		try {
			myServer = new MyServer();
//			Log.set(Log.LEVEL_DEBUG);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			sleep(fps);
			if (myServer.server.getConnections().length == 2) {
				Log.info("server" + myServer.server.getConnections().length);				
				Packet2Message message = new Packet2Message();
				message.message = "Start";
				myServer.server.sendToAllTCP(message);
				Log.info("[server]" + message.message);
				serverGameWorld = new ServerGameWorld(360, myServer.server);				
				myServer.networkListener.setServerGameWorld(serverGameWorld);
				Log.info("[server]bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" + message.message);
				break;
			}

		}

		while (true) {
			myServer.networkListener.setServerGameWorld(serverGameWorld);
			delta++;
			sleep(fps);
			time=delta/fps;
			serverGameWorld.update(time);
			
			

		}

	}
	static long sleep(int fps){
		try {
			Thread.sleep(1000/fps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1000/fps;
	}
}
