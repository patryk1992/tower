package com.Client.packets;

import com.badlogic.gdx.math.Vector2;

public class Packet5CreateTowerRequest {
	public Vector2 position;
	public Packet5CreateTowerRequest(){
		
	}
	public Packet5CreateTowerRequest(float x,float y){
		position=new Vector2(x, y);
	}
	
}