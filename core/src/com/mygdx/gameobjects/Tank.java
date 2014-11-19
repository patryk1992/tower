package com.mygdx.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tank extends GameObject {
	int lives;
	float velocityConstant;
	float progres;
	float speed;
	Vector2 pointTarget;
	Vector2 pointInit;
	public Tank(float x, float y, int width, int height, int idGroup, String id){
		super(x,y,width,height,idGroup,id);		
		lives=3;
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
	
	

}
