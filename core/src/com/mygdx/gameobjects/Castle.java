package com.mygdx.gameobjects;



import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Castle extends GameObject{
	Color color;
	int lives;
	
	public Castle(){
		
	}
	public Castle(float x, float y, int width, int height, int idGroup, String id, Color color){
		super(x,y,width,height,idGroup,id);
		this.color=color;
		lives=10;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLives() {
		return lives;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}
