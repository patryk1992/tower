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
		gameWorld=new GameWorld(midPointY);			
		server.sendToAllTCP(gameWorld);
		
	}	

	public void update(float delta) {
		
		synchronized(gameWorld){
		server.sendToAllTCP(gameWorld);
		}
	}
}
