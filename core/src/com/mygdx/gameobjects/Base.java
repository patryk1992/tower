package com.mygdx.gameobjects;



import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Base extends GameObject{
	
	Color color;
	int lives;
	int coins=500;
	public Base(){
		
	}
	public Base(float x, float y, int width, int height, int idGroup, String id, Color color){
		super(x,y,width,height,idGroup,id);
		this.color=color;
		lives=10;
	}
	
	public void reduceLives() {
		this.lives--;
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
	public int getCoins() {
		return coins;
	}
	public void addCoins(int coins) {
		this.coins += coins;
	}
	public void withdrawCoins(int coins) {
		this.coins -= coins;
	}
}
