package com.mygdx.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.simpleobjects.MyRectangle;

public class Tank extends GameObject implements iFire{
	int lives;
	float velocityConstant;
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	long storedTime;
	long produceTime;
	double degrees;
	int bulletsCanTake;
	
	public Tank(float x, float y, int width, int height,long produceTime ,int idGroup, String id){
		super(x,y,width,height,idGroup,id);		
		lives=3;
		this.produceTime=produceTime;
		degrees=90;
		this.bulletsCanTake=2;
	}
	public Tank(){
		
	}
	
	public void goTo(Vector2 point){
		velocityConstant=1;
		progres=0;
		pointInit=new Vector2(this.getDimension().getPosition());
		pointTarget=point;		
		float distance=this.getDimension().getPosition().dst(point);
		speed=velocityConstant/distance;
		/*
		 * Przeliczanie nachylania
		 * 
		 */
		
		Vector2 pointHelp=new Vector2(pointInit.x,pointTarget.y);
//		float radian=pointHelp.dst(pointTarget)/pointHelp.dst(pointInit);
//		degrees=Math.atan(radian)*180/Math.PI;
		degrees=MathUtils.atan2(pointHelp.dst(pointTarget), pointHelp.dst(pointInit))*180/Math.PI;
		if(pointInit.y>pointHelp.y){
			degrees=180-degrees;
		}
		if(idGroup==1){
			degrees=180-degrees;
		}
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
	public double getDegrees() {
		return degrees;
	}
	@Override
	public Bullet fire(long time, List<? extends GameObject> objectList) {
		long timeDifference=time-storedTime;
		Building targetBuilding=(Building) scanForTarget(objectList);
		if(timeDifference>produceTime&&targetBuilding!=null){			
			storedTime=time;
			return new Bullet(getDimension().position.x, getDimension().position.y, 5, getIdGroup(),UUID.randomUUID().toString(),targetBuilding);
			
		}
		return null;
	}
	@Override
	public GameObject scanForTarget(List<? extends GameObject> objectList) {
		for(GameObject building:objectList){
			if(this.getDimension().getPosition().dst(building.getDimension().getPosition())<150){
				return building;
			}
		}
		return null;
	}
	public int shooted() {
		return bulletsCanTake--;
	}

}
