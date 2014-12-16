package com.mygdx.gameobjects;

import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.simpleobjects.MyRectangle;

public class Building extends GameObject{
	long storedTime;
	long produceTime;
	int bulletsCanTake;
	public Building(float x, float y, int width, int height ,long producedTime,int idGroup, String id,int bulletsCanTake){
		super(x,y,width,height,idGroup,id);
		this.produceTime=producedTime;
		storedTime=0;
		this.bulletsCanTake=bulletsCanTake;
	}

	public Building() {
		// TODO Auto-generated constructor stub
	}
	public int getBulletsCanTake() {
		return bulletsCanTake;
	}
	public int shooted() {
		return bulletsCanTake--;
	}
	
}
