package com.mygdx.gameworld;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.helpers.AssetLoaderSingleton;
import com.mygdx.hud.IButton;
import com.mygdx.hud.ICoins;

public class HUD {	
	int gameWidth, midPointY;
	ShapeRenderer shapeRenderer;
	IButton iTower;
	IButton iMine;
	IButton iFactory;
	ICoins iCoins;
	SpriteBatch batcher;
	int connectionId;
	int connections;
	BitmapFont font;
	boolean endGame;
	int idWinner;	
	IButton restartGameButton;
	IButton endGameButton;
	AssetLoaderSingleton singleton;

	
	public HUD(ShapeRenderer shapeRenderer, SpriteBatch batcher, BitmapFont font, int gameWidth, int midPointY){
		this.gameWidth=gameWidth;
		this.midPointY=midPointY;
		this.shapeRenderer=shapeRenderer;
		this.batcher=batcher;
		this.font=font;	
		this.endGame=false;
		this.idWinner=0;
		singleton=AssetLoaderSingleton.getInstance();
	}
	public void intHUD(){
		if(connectionId==1){
			iTower= new IButton(1,670,50,50);
			iMine= new IButton(1,610,50,50);
			iFactory= new IButton(1,550,50,50);
			iCoins= new ICoins(5, 10, 15);
			}
		else if(connectionId==2){
			iTower= new IButton(1229,670,50,50);
			iMine= new IButton(1229,610,50,50);
			iFactory= new IButton(1229,550,50,50);
			iCoins= new ICoins(1250, 10, 15);
			}
		restartGameButton=new IButton(320,260,640,100);
		endGameButton=new IButton(320,400,640,100);
	}
	public void render(float runTime) {		
	     if(iTower!=null&&iMine!=null&&iFactory!=null&&iCoins!=null){
	    	
	         batcher.begin();
	         
	         batcher.disableBlending();
	         batcher.draw(singleton.tower[connectionId-1], iTower.getPosition().x, iTower.getPosition().y, iTower.getWidth(), iTower.getHeight());
	         batcher.draw(singleton.mine[connectionId-1], iMine.getPosition().x, iMine.getPosition().y, iMine.getWidth(), iMine.getHeight());
	         batcher.draw(singleton.factory[connectionId-1],iFactory.getPosition().x, iFactory.getPosition().y, iFactory.getWidth(), iFactory.getHeight());
	         batcher.enableBlending();
	         batcher.draw(singleton.coins, iCoins.getPosition().x, iCoins.getPosition().y, iCoins.getRadius()*2, iCoins.getRadius()*2);
	         batcher.end();
	         
	     }
	     if(connections<1){		    
		     batcher.begin();
		     batcher.draw(singleton.waiting, 320, 60, 640,100);
			 batcher.end();
	     }
	     if(endGame){	    	 
		     batcher.begin();
		     if(idWinner==0){
		    	 batcher.draw(singleton.lostConnection, 320, 60, 640,100);
		    	 batcher.end();
		     }else if(idWinner==connectionId){
		    	 batcher.enableBlending();
		    	 batcher.draw(singleton.win, 320, 60, 640, 100);
		    	 batcher.end();
		     }else{
		    	 batcher.enableBlending();
		    	 batcher.draw(singleton.lose, 320, 60, 640, 100);
		    	 batcher.end();
		     }
		     if(idWinner!=0){				 
			     batcher.begin();
			     batcher.draw(singleton.restart, restartGameButton.getPosition().x, restartGameButton.getPosition().y, restartGameButton.getWidth(), restartGameButton.getHeight());
			     batcher.end();
			 }	
		     batcher.begin();
		     batcher.draw(singleton.end, endGameButton.getPosition().x, endGameButton.getPosition().y, endGameButton.getWidth(), endGameButton.getHeight());
		     batcher.end();
	     }
	 }
	public IButton getiTower() {
		return iTower;
	}
	public IButton getiMine() {
		return iMine;
	}
	public IButton getiFactory() {
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

	public boolean isEndGame() {
		return endGame;
	}
	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}	
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public int getIdWinner() {
		return idWinner;
	}
	public void setIdWinner(int idWinner) {
		this.idWinner = idWinner;
	}
	public IButton getRestartGameButton() {
		return restartGameButton;
	}
	public void setRestartGameButton(IButton restartGameButton) {
		this.restartGameButton = restartGameButton;
	}
	public IButton getEndGameButton() {
		return endGameButton;
	}
	public void setEndGameButton(IButton endGameButton) {
		this.endGameButton = endGameButton;
	}
}
