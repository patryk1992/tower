package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;

public class MyGdxGame  extends Game{
	GameScreen gameScreen;
	 @Override
	    public void create() {
	        Gdx.app.log("ZBGame", "created");
	        setScreen(new MenuScreen(this));
	       
	    }

	    @Override
	    public void dispose() {
	        super.dispose();
	        AssetLoader.dispose();
	    }
	
}
