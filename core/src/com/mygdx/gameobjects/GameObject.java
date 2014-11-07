package com.mygdx.gameobjects;
import java.util.UUID;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
	Vector2 position;
	int width;
	int height;
	String id;
	int idGroup;
	public GameObject(){
		
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public GameObject(float x, float y, int width, int height ,int idGroup, String id){
		this.width = width;
		this.height = height;
		this.id=id;
		this.idGroup=idGroup;
		position = new Vector2(x, y);
	}
}
