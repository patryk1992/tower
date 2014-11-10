package com.mygdx.gameobjects;

public class IMine extends MyInterface{
	Boolean pressed=false; 
	
	public IMine(float x, float y, int width, int height){
		super(x,y,width,height);
	}
	public Boolean getPressed() {
		return pressed;
	}
	public void setPressed(Boolean pressed) {
		this.pressed = pressed;
	}
}
