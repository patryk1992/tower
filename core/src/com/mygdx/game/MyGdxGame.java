package com.mygdx.game;



import com.Client.MyClient;
import com.Client.Packet.Packet2Message;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.minlog.Log;
import com.mygdx.screens.GameScreen;

public class MyGdxGame  extends Game{
	GameScreen gameScreen;
	 @Override
	    public void create() {
	        Gdx.app.log("ZBGame", "created");
	        gameScreen=new GameScreen();
	        setScreen(gameScreen);
	       
	    }

	    @Override
	    public void dispose() {
	        super.dispose();
	        
	    }
	
}
