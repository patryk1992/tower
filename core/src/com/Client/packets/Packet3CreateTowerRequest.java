package com.Client.packets;

import com.badlogic.gdx.math.Vector2;

public class Packet3CreateTowerRequest {
	public Vector2 position;
	public Packet3CreateTowerRequest(){
		
	}
	public Packet3CreateTowerRequest(float x,float y){
		position=new Vector2(x, y);
	}
	
}