package com.mygdx.gameobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.simpleobjects.*;


public class GameObject {	
	String id;
	int idGroup;	
	private Dimension dimension;	
	
	public GameObject(){
		
	}	
	
	public GameObject(float x, float y, int width, int height ,int idGroup, String id){
		setDimension(new MyRectangle(x,y,width,height));
		this.id=id;
		this.idGroup=idGroup;		
	}
	public GameObject(float x, float y, float radius ,int idGroup, String id){
		setDimension(new MyCircle(x,y,radius));
		this.id=id;
		this.idGroup=idGroup;		
	}
	
	public GameObject collides(List<? extends GameObject> objectList) {
		for(GameObject object:objectList){
			if(this!=object){
				 if(object.getDimension().collides(this.dimension.position.x,this.getDimension().position.y,((MyRectangle) this.dimension).getWidth(),((MyRectangle) this.dimension).getHeight())){
					 return object;
				 }
			}
		}
		return null;
	}
	public boolean collides(ArrayList<? extends GameObject> objectList,float differenceX,float differenceY,float differenceWidth,float differenceHeight) {	
		for(GameObject object:objectList){
			if(this!=object){
				if(object.getDimension().collides(this.dimension.position.x+differenceX,this.getDimension().position.y+differenceY,(int)(((MyRectangle) this.dimension).getWidth()+differenceWidth),(int)(((MyRectangle) this.dimension).getHeight()+differenceHeight))){
					 return true;
				 }		
			}
		}
		return false;
	}
	public Object collides(GameObject object) {	
		if(object.getDimension().collides(this.dimension.position.x,this.getDimension().position.y,((MyRectangle) this.dimension).getWidth(),((MyRectangle) this.dimension).getHeight())){
			 return this;
		 }		
	return null;
}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public String getId() {
		return id;
	}
	public int getIdGroup() {
		return idGroup;
	}
	
}
