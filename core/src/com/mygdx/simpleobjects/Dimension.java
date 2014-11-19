package com.mygdx.simpleobjects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Dimension {
	public Vector2 position;
	public Dimension() {

	}

	public Dimension(float x, float y) {		
		position = new Vector2(x, y);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}	

	public abstract boolean collides(int x2, int y2);
	
	public abstract boolean collides(float x, float y, int w, int h);
	
	public abstract boolean collides(int x, int y, int radius);
	
	
}


	

