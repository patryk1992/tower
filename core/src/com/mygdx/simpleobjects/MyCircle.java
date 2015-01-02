package com.mygdx.simpleobjects;

public class MyCircle extends Dimension {
	float radius;
	
	public MyCircle(){
		
	}
	public MyCircle(float x, float y, float radius){
		super(x,y);
		this.radius=radius;
	}
	@Override
	public boolean collides(int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collides(float x, float y, int w, int h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean collides(int x, int y, float radius) {
		// TODO Auto-generated method stub
		return false;
	}
	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
}