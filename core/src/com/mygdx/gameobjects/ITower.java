package com.mygdx.gameobjects;

public class ITower extends MyInterface{
	Boolean pressed=false;
	
	public ITower(float x, float y, int width, int height){
		super(x,y,width,height);
	}
	public Boolean getPressed() {
		return pressed;
	}
	public void setPressed(Boolean pressed) {
		this.pressed = pressed;
	}
}
