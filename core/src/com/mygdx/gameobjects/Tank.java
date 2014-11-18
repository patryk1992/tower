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
		pointInit=new Vector2(this.getPosition());
		pointTarget=point;
		float distance=this.getPosition().dst(point);
		speed=velocityConstant/distance;
	}
	public boolean move(){
	    progres += speed;
	    this.getPosition().x=pointInit.x+(pointTarget.x-pointInit.x)*progres;
	    this.getPosition().y=pointInit.y+(pointTarget.y-pointInit.y)*progres; 
	    if(this.getPosition().dst2(pointTarget)<1){
	    	return true;
	    }
	    return false;
	}
	public Tank collides(ArrayList<Tank> tankList) {	
		for(Tank tank:tankList){
			if(this!=tank){
				 if(Intersector.overlaps(new Rectangle(position.x, position.y, width, height), new Rectangle(tank.getPosition().x, tank.getPosition().y, tank.getWidth(), tank.getHeight()))){//dodanie tyvh wartoœci aby sie nie naje¿dza³y czo³gi
					 return tank;
				 }		
			}
		}
		return null;
	}	
	public boolean collides(ArrayList<Tank> tankList,float differenceX,float differenceY,float differenceWidth,float differenceHeight) {	
		for(Tank tank:tankList){
			if(this!=tank){
				 if(Intersector.overlaps(new Rectangle(position.x, position.y, width, height), new Rectangle(tank.getPosition().x+differenceX, tank.getPosition().y+differenceY, tank.getWidth()+differenceWidth, tank.getHeight()+differenceHeight))){//dodanie tych wartoœci aby sie nie naje¿dza³y czo³gi
					 return true;
				 }		
			}
		}
		return false;
	}	
	public Tank collides(Base base) {	
			if(Intersector.overlaps(new Rectangle(position.x, position.y, width, height), new Rectangle(base.getPosition().x, base.getPosition().y, base.getWidth(), base.getHeight()))){
				 return this;
			}		
		return null;
	}

}
