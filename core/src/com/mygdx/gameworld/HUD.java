package com.mygdx.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.Barricade;
import com.mygdx.gameobjects.ICoins;
import com.mygdx.gameobjects.IFactory;
import com.mygdx.gameobjects.IMine;
import com.mygdx.gameobjects.ITower;

public class HUD {
	Barricade barricade;
	int gameWidth, midPointY;
	ShapeRenderer shapeRenderer;
	ITower iTower;
	IMine iMine;
	IFactory iFactory;
	ICoins iCoins;
	int connectionId;
	
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public HUD(ShapeRenderer shapeRenderer, int gameWidth, int midPointY){
		this.gameWidth=gameWidth;
		this.midPointY=midPointY;
		this.shapeRenderer=shapeRenderer;
		barricade=new Barricade((gameWidth/2)-4,0,8,midPointY*2);
	}
	public void intHUD(){
		if(connectionId==1){
			iTower= new ITower(1,670,50,50);
			iMine= new IMine(1,610,50,50);
			iFactory= new IFactory(1,550,50,50);
			iCoins= new ICoins(5, 10, 15);
			}
		else if(connectionId==2){
			iTower= new ITower(1229,670,50,50);
			iMine= new IMine(1229,610,50,50);
			iFactory= new IFactory(1229,550,50,50);
			iCoins= new ICoins(1250, 10, 15);
			}
	}
	public void render(float runTime) {
		 shapeRenderer.begin(ShapeType.Filled);
	     shapeRenderer.setColor(Color.GRAY);
	     shapeRenderer.rect(barricade.getPosition().x, barricade.getPosition().y, barricade.getWidth(), barricade.getHeight());
	     shapeRenderer.end();
	     if(iTower!=null&&iMine!=null&&iFactory!=null&&iCoins!=null){
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.GRAY);
		     shapeRenderer.rect(iTower.getPosition().x, iTower.getPosition().y, iTower.getWidth(), iTower.getHeight());
		     shapeRenderer.end();
		     
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.YELLOW);
		     shapeRenderer.rect(iMine.getPosition().x, iMine.getPosition().y, iMine.getWidth(), iMine.getHeight());
		     shapeRenderer.end();
		     
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.PURPLE);
		     shapeRenderer.rect(iFactory.getPosition().x, iFactory.getPosition().y, iFactory.getWidth(), iFactory.getHeight());
		     shapeRenderer.end();
		     
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.PINK);
		     shapeRenderer.circle(iCoins.getPosition().x, iCoins.getPosition().y, iCoins.getRadius());
		     shapeRenderer.end();
	     }
	     
	 }
	public ITower getiTower() {
		return iTower;
	}
	public IMine getiMine() {
		return iMine;
	}
	public IFactory getiFactory() {
		return iFactory;
	}
	public ICoins getiCoins() {
		return iCoins;
	}
}
