package com.mygdx.gameobjects;

import com.mygdx.simpleobjects.MyRectangle;

public class IFactory extends MyRectangle{
	Boolean pressed=false; 
	
	public IFactory(float x, float y, int width, int height){
		super(x,y,width,height);
	}
	public Boolean getPressed() {
		return pressed;
	}
	public void setPressed(Boolean pressed) {
		this.pressed = pressed;
	}
}
