package com.mygdx.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.Barricade;

public class HUD {
	Barricade barricade;
	int gameWidth, midPointY;
	ShapeRenderer shapeRenderer;
	
	public HUD(ShapeRenderer shapeRenderer, int gameWidth, int midPointY){
		this.gameWidth=gameWidth;
		this.midPointY=midPointY;
		this.shapeRenderer=shapeRenderer;
		barricade=new Barricade((gameWidth/2)-4,0,8,midPointY*2);
	}
	 public void render(float runTime) {
		 shapeRenderer.begin(ShapeType.Filled);
	     shapeRenderer.setColor(Color.GRAY);
	     shapeRenderer.rect(barricade.getPosition().x, barricade.getPosition().y, barricade.getWidth(), barricade.getHeight());
	     shapeRenderer.end();
	 }
}
