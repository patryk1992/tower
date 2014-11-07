package com.mygdx.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.Barricade;
import com.mygdx.gameobjects.Castle;

public class GameRenderer {
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private GameWorld myWorld;
	
	private SpriteBatch batcher;
	
	private int midPointY;
    private int gameWidth;    
    Barricade barricade;

    public GameRenderer(GameWorld world, int gameWidth, int midPointY) {
        myWorld = world;

        // The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameWidth = gameWidth;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, midPointY*2);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);        
        
        barricade=new Barricade((gameWidth/2)-4,0,8,midPointY*2);
    }
    
    public void setMyWorld(GameWorld myWorld) {
		this.myWorld = myWorld;
		
	}
    public void render(float runTime) {
    	
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeType.Filled);
        // Draw Background color
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 1280, 720 );        
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(myWorld.getCastles()[0].getColor());
        shapeRenderer.rect(myWorld.getCastles()[0].getPosition().x, myWorld.getCastles()[0].getPosition().y, myWorld.getCastles()[0].getWidth(), myWorld.getCastles()[0].getHeight());
        shapeRenderer.end();
        
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(myWorld.getCastles()[1].getColor());
        shapeRenderer.rect(myWorld.getCastles()[1].getPosition().x, myWorld.getCastles()[1].getPosition().y, myWorld.getCastles()[1].getWidth(), myWorld.getCastles()[1].getHeight());
        shapeRenderer.end();
       
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(barricade.getPosition().x, barricade.getPosition().y, barricade.getWidth(), barricade.getHeight());
        shapeRenderer.end();
         
    }  


}