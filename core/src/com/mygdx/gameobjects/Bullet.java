package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.simpleobjects.MyRectangle;

public class Bullet extends GameObject {
	float velocityConstant;
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	String targetID;
	int targetType;//1 gdy samolat, 0 gdy budynek
	
	public Bullet(float x, float y,float radius, int idGroup, String id, GameObject targetObject){
		super(x,y,radius,idGroup,id);
		this.targetID=targetObject.id;
		this.pointTarget=new Vector2(targetObject.getDimension().getPosition().x+((MyRectangle) targetObject.getDimension()).getWidth()/2,targetObject.getDimension().getPosition().y+((MyRectangle) targetObject.getDimension()).getHeight()/2);
		if(targetObject instanceof Plane){
			this.targetType=1;
		}else if(targetObject instanceof Building){
			this.targetType=0;
		}
		velocityConstant=5;
		progres=0;
		pointInit=new Vector2(this.getDimension().getPosition());
		float distance=this.getDimension().getPosition().dst(pointTarget);
		speed=velocityConstant/distance;
	}
	public Bullet(){
		
	}
	public boolean move(){
	    progres += speed;
	    this.getDimension().getPosition().x=pointInit.x+(pointTarget.x-pointInit.x)*progres;
	    this.getDimension().getPosition().y=pointInit.y+(pointTarget.y-pointInit.y)*progres; 
	    if(this.getDimension().getPosition().dst2(pointTarget)<10){//5 to dok³adnosc sprawdzanie kolizji
	    	return true;
	    }
	    return false;
	}
	public String getTargetBuildingID() {
		return targetID;
	}
	public void updatePointTarget(GameObject target) {
		this.pointTarget=new Vector2(target.getDimension().getPosition().x+((MyRectangle) target.getDimension()).getWidth()/2,target.getDimension().getPosition().y+((MyRectangle) target.getDimension()).getHeight()/2);
	}

	public int getTargetType() {
		return targetType;
	}
}

