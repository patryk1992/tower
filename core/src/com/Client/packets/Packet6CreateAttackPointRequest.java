package com.Client.packets;

import com.badlogic.gdx.math.Vector2;

public class Packet6CreateAttackPointRequest {
	public Vector2 position;
	public Packet6CreateAttackPointRequest(){
		
	}
	public Packet6CreateAttackPointRequest(float x,float y){
		position=new Vector2(x, y);
	}
	
}