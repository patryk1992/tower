package com.mygdx.simpleobjects;

public class MyCircle extends Dimension {
	int radius;
	public MyCircle(){
		
	}
	public MyCircle(float x, float y, int radius){
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
	public boolean collides(int x, int y, int radius) {
		// TODO Auto-generated method stub
		return false;
	}
}