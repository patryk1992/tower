package com.mygdx.gameobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MyInterface {
	Vector2 position;

	int width;
	int height;

	public MyInterface() {

	}

	public MyInterface(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
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

	public boolean collides(int x2, int y2) {
		if (x2 > position.x && x2 < position.x + width 
				&& y2 > position.y && y2 < position.y + width) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
