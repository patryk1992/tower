package myServer;

import java.io.IOException;

import com.Client.packets.Packet.Packet1LoginAnswer;
import com.Client.packets.Packet.Packet2Message;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.minlog.Log;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameworld.GameWorld;
import com.server.logic.ServerGameWorld;

public class LogicGame {
	static ServerGameWorld serverGameWorld;
	static int delta = 0;
	static int fps=30;
	static MyServer myServer = null;
	static long time;
	
	public static void start(){

		try {
			myServer = new MyServer();
//			Log.set(Log.LEVEL_DEBUG);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			sleepFps(fps);
			if (myServer.server.getConnections().length == 2) {
				Log.info("server" + myServer.server.getConnections().length);				
				Packet2Message message = new Packet2Message();
				message.message = "Start";
				myServer.server.sendToAllTCP(message);				
				serverGameWorld = new ServerGameWorld(360, myServer.server);				
				myServer.networkListener.setServerGameWorld(serverGameWorld);			
				
				sendGameWorldUpdate();
				waitForSecondPlayerPacket();
				break;
				
			}

		}
		loopGame();

	}
	
	static void loopGame(){
		while (true) {
			delta++;
			sleepFps(fps);
			time=delta/fps;
			serverGameWorld.update(time);					
		}
	}	
	
	private static void sendGameWorldUpdate() {
		Thread thread=new Thread(){
			public void run(){
				while (true) {
					sleepFps(fps);
					synchronized (serverGameWorld.getGameWorld()) {									
//						myServer.server.sendToUDP(myServer.server.getConnections()[0].getID(), serverGameWorld.getGameWorld());
//						myServer.server.sendToUDP(myServer.server.getConnections()[1].getID(), serverGameWorld.getGameWorld());
						myServer.server.sendToAllUDP(serverGameWorld.getGameWorld());
					}
				}
			}
		};
		thread.start();
	}
	
	static void waitForSecondPlayerPacket(){		//komunikat czekam na gracza
		Packet1LoginAnswer loginAnswer= new Packet1LoginAnswer();			
		loginAnswer.accepted=true;
		if(serverGameWorld==null){
			loginAnswer.connections=0;					
		}else {
			loginAnswer.connections=serverGameWorld.getServer().getConnections().length;					
		}
		myServer.server.sendToAllTCP(loginAnswer);
	}

	static long sleepFps(int fps){
		try {
			Thread.sleep(1000/fps);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1000/fps;
	}
}
