package com.Client.packets;

import com.badlogic.gdx.math.Vector2;

public class Packet3CreateFactoryRequest {
	public Vector2 position;
	public Packet3CreateFactoryRequest(){
		
	}
	public Packet3CreateFactoryRequest(float x,float y){
		position=new Vector2(x, y);
	}
	
}