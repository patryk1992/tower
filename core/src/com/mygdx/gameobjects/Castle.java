package com.mygdx.gameobjects;



import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Castle extends GameObject{
	Color color;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Castle(){
		
	}
	public Castle(float x, float y, int width, int height, int idGroup, String id, Color color){
		super(x,y,width,height,idGroup,id);
		this.color=color;
	}
}
