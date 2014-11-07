package com.server.logic;

import java.util.UUID;

import myServer.MyServer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameworld.GameWorld;

public class ServerGameWorld {
	
	private Server server;
	int midPointY;
	GameWorld gameWorld;
	
	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public ServerGameWorld(int midPointY, Server server){
		this.server=server;
		this.midPointY=midPointY;
		Castle []castles=new Castle[2];		
		castles[0]=new Castle(5,midPointY-40,80,80,server.getConnections()[0].getID(),UUID.randomUUID().toString(),Color.GREEN);
		castles[1]=new Castle(1195,midPointY-40,80,80,server.getConnections()[1].getID(),UUID.randomUUID().toString(),Color.RED);
		gameWorld=new GameWorld(midPointY);
		gameWorld.setCastles(castles);		
		server.sendToAllTCP(gameWorld);
		
	}	

	public void update(float delta) {
//		gameWorld.getCastles()[0].setPosition(new Vector2(delta,midPointY-40));
		server.sendToAllTCP(gameWorld);
		
	}
}
