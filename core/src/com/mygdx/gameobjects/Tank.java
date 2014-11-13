package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;

public class Tank extends GameObject {
	int lives;
	public Tank(float x, float y, int width, int height, int idGroup, String id){
		super(x,y,width,height,idGroup,id);		
		lives=3;
	}
	public Tank(){
		
	}
}
