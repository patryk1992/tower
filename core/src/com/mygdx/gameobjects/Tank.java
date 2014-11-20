package com.mygdx.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.simpleobjects.MyRectangle;

public class Tank extends GameObject {
	int lives;
	float velocityConstant;
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	long storedTime;
	long produceTime;
	public Tank(float x, float y, int width, int height, int idGroup, String id){
		super(x,y,width,height,idGroup,id);		
		lives=3;
	}
	public Tank(){
		
	}
	
	public Bullet fire(long time,List<? extends Building> objectList){
		long timeDifference=time-storedTime;
		Building targetBuilding=scanForTarget(objectList);
		if(timeDifference>produceTime&&targetBuilding!=null){			
			storedTime=time;
			return new Bullet(getDimension().position.x, getDimension().position.y, 10, getIdGroup(),UUID.randomUUID().toString(),targetBuilding);
			
		}
		return null;
		
	}
	public Building scanForTarget(List<? extends Building> objectList){
		for(Building building:objectList){
			if(this.getDimension().getPosition().dst(building.getDimension().getPosition())<150){
				return building;
			}
		}
		return null;
	}
	public void goTo(Vector2 point){
		velocityConstant=1;
		progres=0;
		pointInit=new Vector2(this.getDimension().getPosition());
		pointTarget=point;
		float distance=this.getDimension().getPosition().dst(point);
		speed=velocityConstant/distance;
	}
	public boolean move(){
	    progres += speed;
	    this.getDimension().getPosition().x=pointInit.x+(pointTarget.x-pointInit.x)*progres;
	    this.getDimension().getPosition().y=pointInit.y+(pointTarget.y-pointInit.y)*progres; 
	    if(this.getDimension().getPosition().dst2(pointTarget)<1){
	    	return true;
	    }
	    return false;
	}
	
	public long getStoredTime() {
		return storedTime;
	}
	public void setStoredTime(long storedTime) {
		this.storedTime = storedTime;
	}

}
