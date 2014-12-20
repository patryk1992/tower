package com.mygdx.screens;
import com.Client.MyClient;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MyGdxGame;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.helpers.Input;


public class GameScreen implements Screen{

	public GameWorld world;
	private GameRenderer renderer;
	private MyClient myClient;
	private float runTime =0;
	private Input input;
	MyGdxGame game;
	
	public GameScreen(MyGdxGame game) throws Exception{
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight =Gdx.graphics.getHeight();	
		int midPointY =(int) (screenHeight /2);
		this.game=game;
		world= new GameWorld(midPointY);
		renderer = new GameRenderer(world,(int) screenWidth,midPointY);
		myClient=new MyClient(renderer);	
		input=new Input(myClient.client,renderer,game);
		Gdx.input.setInputProcessor(input);
		
	}
	
	@Override
	public void render(float delta) {
		runTime+=delta;		
		renderer.render(runTime);		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
	
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
