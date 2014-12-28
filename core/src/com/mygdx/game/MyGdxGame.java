package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.helpers.AssetLoaderSingleton;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;

public class MyGdxGame  extends Game{	
	MenuScreen menuScreen;
	 @Override
	    public void create() {
	        Gdx.app.log("ZBGame", "created");
	        menuScreen=new MenuScreen(this);				        
	        setScreen(menuScreen);
	       
	    }	    

		@Override
	    public void dispose() {	    	
	        super.dispose();
	        AssetLoaderSingleton.dispose();
	    }
		
}
