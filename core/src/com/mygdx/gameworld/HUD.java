package com.mygdx.gameworld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.Barricade;
import com.mygdx.gameobjects.ICoins;
import com.mygdx.gameobjects.IFactory;
import com.mygdx.gameobjects.IMine;
import com.mygdx.gameobjects.ITower;
import com.mygdx.helpers.AssetLoader;

public class HUD {	
	int gameWidth, midPointY;
	ShapeRenderer shapeRenderer;
	ITower iTower;
	IMine iMine;
	IFactory iFactory;
	ICoins iCoins;
	SpriteBatch batcher;
	int connectionId;
	int connections;
	BitmapFont font;
	
	
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public HUD(ShapeRenderer shapeRenderer, SpriteBatch batcher, BitmapFont font, int gameWidth, int midPointY){
		this.gameWidth=gameWidth;
		this.midPointY=midPointY;
		this.shapeRenderer=shapeRenderer;
		this.batcher=batcher;
		this.font=font;		
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
	     if(iTower!=null&&iMine!=null&&iFactory!=null&&iCoins!=null){
	    	 
	    	// Begin SpriteBatch
	         batcher.begin();
	         // Disable transparency
	         // This is good for performance when drawing images that do not require
	         // transparency.
	         batcher.disableBlending();
	         batcher.draw(AssetLoader.tower[connectionId-1], iTower.getPosition().x, iTower.getPosition().y, iTower.getWidth(), iTower.getHeight());
	         batcher.draw(AssetLoader.mine[connectionId-1], iMine.getPosition().x, iMine.getPosition().y, iMine.getWidth(), iMine.getHeight());
	         batcher.draw(AssetLoader.factory[connectionId-1],iFactory.getPosition().x, iFactory.getPosition().y, iFactory.getWidth(), iFactory.getHeight());
	         batcher.enableBlending();
	         batcher.end();
	         
//		     shapeRenderer.begin(ShapeType.Filled);
//		     shapeRenderer.setColor(Color.GRAY);
//		     shapeRenderer.rect(iTower.getPosition().x, iTower.getPosition().y, iTower.getWidth(), iTower.getHeight());
//		     shapeRenderer.end();
		     
//		     shapeRenderer.begin(ShapeType.Filled);
//		     shapeRenderer.setColor(Color.YELLOW);
//		     shapeRenderer.rect(iMine.getPosition().x, iMine.getPosition().y, iMine.getWidth(), iMine.getHeight());
//		     shapeRenderer.end();
		     
//		     shapeRenderer.begin(ShapeType.Filled);
//		     shapeRenderer.setColor(Color.PURPLE);
//		     shapeRenderer.rect(iFactory.getPosition().x, iFactory.getPosition().y, iFactory.getWidth(), iFactory.getHeight());
//		     shapeRenderer.end();
		     
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.PINK);
		     shapeRenderer.circle(iCoins.getPosition().x, iCoins.getPosition().y, iCoins.getRadius());
		     shapeRenderer.end();
	     }
	     if(connections<1){
		     shapeRenderer.begin(ShapeType.Filled);
		     shapeRenderer.setColor(Color.RED);
		     shapeRenderer.rect(640, midPointY, 100,300);
		     shapeRenderer.end();
		     batcher.begin();
			 font.draw(batcher, "czekam na 2",650,midPointY);
			 batcher.end();
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
	public int getConnections() {
		return connections;
	}
	public void setConnections(int connections) {
		this.connections = connections;
	}
}
