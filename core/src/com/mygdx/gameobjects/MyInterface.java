package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class MyInterface {
	Vector2 position;
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
	int width;
	int height;
	public MyInterface(){
		
	}
	public MyInterface(float x, float y, int width, int height){
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
	}
}