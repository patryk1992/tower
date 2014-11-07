package com.mygdx.gameworld;

import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gameobjects.Castle;

public class GameWorld {

	private Castle []castles;
	public GameWorld(){
		
	}
	public GameWorld(int midPointY){
		castles=new Castle[2];
		castles[0]=new Castle(5,midPointY-40,80,80,1,UUID.randomUUID().toString(),Color.GREEN);
		castles[1]=new Castle(1195,midPointY-40,80,80,2,UUID.randomUUID().toString(),Color.RED);
		
	}
	
	public Castle[] getCastles() {
		return castles;
	}


	public void setCastles(Castle[] castles) {
		this.castles = castles;
	}

		

}
