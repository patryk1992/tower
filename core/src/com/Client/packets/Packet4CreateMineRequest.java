package com.Client.packets;

import com.badlogic.gdx.math.Vector2;

public class Packet4CreateMineRequest {
	public Vector2 position;
	public Packet4CreateMineRequest(){
		
	}
	public Packet4CreateMineRequest(float x,float y){
		position=new Vector2(x, y);
	}
	
}