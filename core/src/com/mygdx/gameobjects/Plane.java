package com.mygdx.gameobjects;

import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.patternflyweight.PlaneFactory;
import com.mygdx.patternflyweight.PlaneModel;

public class Plane extends GameObject implements iFire{
	
	private PlaneModel planeModel;	
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	double storedTime;
	double degrees;
	int bulletsCanTake;
	
	public Plane(float x, float y, int width, int height,int idGroup, String id){
		super(x,y,width,height,idGroup,id);			
		degrees=90;
		this.bulletsCanTake=2;	
		planeModel=PlaneFactory.getPlaneModel();
	}
	public Plane(){
		
	}
	
	public void goTo(Vector2 point){
		progres=0;
		pointInit=new Vector2(this.getDimension().getPosition());
		pointTarget=point;		
		float distance=this.getDimension().getPosition().dst(point);
		speed=planeModel.getVelocityConstant()/distance;
		//Przeliczanie nachylania
		Vector2 pointHelp=new Vector2(pointInit.x,pointTarget.y);
		degrees=MathUtils.atan2(pointHelp.dst(pointTarget), pointHelp.dst(pointInit))*180/Math.PI;
		if(pointInit.y<pointHelp.y){
			degrees=180-degrees;
		}
		if(pointInit.x>pointTarget.x){
			degrees=360-degrees;
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
	
	public double getStoredTime() {
		return storedTime;
	}
	public void setStoredTime(long storedTime) {
		this.storedTime = storedTime;
	}
	public double getDegrees() {
		return degrees;
	}
	@Override
	public Bullet fire(double time, List<? extends GameObject> objectList) {
		double timeDifference=time-storedTime;
		Building targetBuilding=(Building) scanForTarget(objectList);
		if(timeDifference>planeModel.getProduceTime()&&targetBuilding!=null){			
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
