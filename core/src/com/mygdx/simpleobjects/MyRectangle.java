package com.mygdx.simpleobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyRectangle extends Dimension{
	int width;
	int height;
	
	public MyRectangle(){
		
	}
	
	public MyRectangle(float x, float y, int width, int height) {
		// TODO Auto-generated constructor stub
		super(x,y);
		this.width=width;
		this.height=height;
	}
	

	@Override
	public boolean collides(int x2, int y2) {
		if (x2 > position.x && x2 < position.x + width 
				&& y2 > position.y && y2 < position.y + height) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean collides(float x2, float y2, int w2, int h2) {
		if(Intersector.overlaps(new Rectangle(this.position.x,this.position.y, width, height), new Rectangle(x2,y2, w2, h2))){
			 return true;
		 }
		return false;
	}

	@Override
	public boolean collides(int x, int y, float radius) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
