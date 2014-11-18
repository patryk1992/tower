package com.mygdx.gameobjects;

import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Building extends GameObject{
	public Building(float x, float y, int width, int height ,int idGroup, String id){
		super(x,y,width,height,idGroup,id);
	}

	public Building() {
		// TODO Auto-generated constructor stub
	}
	public boolean collides(List<Building> buildingList) {
		for(Building building:buildingList){
		 if(Intersector.overlaps(new Rectangle(position.x, position.y, width, height), new Rectangle(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight()))){
			 return true;
		 }
		}
		return false;
	}
}
