package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.simpleobjects.MyRectangle;

public class Bullet extends GameObject {
	float velocityConstant;
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	String targetBuildingID;
	
	public Bullet(float x, float y,float radius, int idGroup, String id, GameObject targetBuilding){
		super(x,y,radius,idGroup,id);
		this.targetBuildingID=targetBuilding.id;
		this.pointTarget=new Vector2(targetBuilding.getDimension().getPosition().x+((MyRectangle) targetBuilding.getDimension()).getWidth()/2,targetBuilding.getDimension().getPosition().y+((MyRectangle) targetBuilding.getDimension()).getHeight()/2);
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
	    if(this.getDimension().getPosition().dst2(pointTarget)<5){//5 to dok³adnosc sprawdzanie kolizji
	    	return true;
	    }
	    return false;
	}
	public String getTargetBuildingID() {
		return targetBuildingID;
	}
	public void updatePointTarget(GameObject target) {
		this.pointTarget=new Vector2(target.getDimension().getPosition().x+((MyRectangle) target.getDimension()).getWidth()/2,target.getDimension().getPosition().y+((MyRectangle) target.getDimension()).getHeight()/2);
	}
}

