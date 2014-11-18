package com.mygdx.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.Barricade;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Tank;
import com.mygdx.gameobjects.Tower;


public class GameRenderer {
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private GameWorld myWorld;
	
	private HUD hud;	
	private SpriteBatch batcher;	
	private BitmapFont font;
	
	private int midPointY;
    private int gameWidth;    
   

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
        hud=new HUD(shapeRenderer,gameWidth, midPointY);
        font = new BitmapFont(true);
        font.setColor(Color.BLACK);
        
    }
    
    public void setMyWorld(GameWorld myWorld) {
		this.myWorld = myWorld;
		
	}
    public GameWorld getMyWorld() {
		return myWorld;
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
        batcher.begin();
        font.draw(batcher, Integer.toString(myWorld.getCastles()[0].getLives()), myWorld.getCastles()[0].getPosition().x, myWorld.getCastles()[0].getPosition().y);
        batcher.end();
        
        
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(myWorld.getCastles()[1].getColor());
        shapeRenderer.rect(myWorld.getCastles()[1].getPosition().x, myWorld.getCastles()[1].getPosition().y, myWorld.getCastles()[1].getWidth(), myWorld.getCastles()[1].getHeight());
        shapeRenderer.end();
        batcher.begin();
        font.draw(batcher, Integer.toString(myWorld.getCastles()[1].getLives()), myWorld.getCastles()[1].getPosition().x, myWorld.getCastles()[1].getPosition().y);
        batcher.end();
        
        for(ArrayList<Building> towerList : myWorld.getTowerList()){
        	for(Building building :towerList ){        		
        		if(building.getIdGroup()==1){
        			if(building instanceof Mine){
        				shapeRenderer.begin(ShapeType.Filled);
        				shapeRenderer.setColor(Color.YELLOW);
        				shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
        	            shapeRenderer.end();
        			}
        			else if(building instanceof Tower){
        				shapeRenderer.begin(ShapeType.Filled);
        				shapeRenderer.setColor(Color.GRAY);
        				shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
        	            shapeRenderer.end();
        			}
        			else if(building instanceof Factory){    
        				shapeRenderer.begin(ShapeType.Filled);
        		        shapeRenderer.setColor(Color.PURPLE);
        		        shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
                        shapeRenderer.end();
                        batcher.begin();
        		        font.draw(batcher, Integer.toString(((Factory) building).getTankNumber()),building.getPosition().x, building.getPosition().y);
        		        batcher.end();
        			}
        		}
        		else if(building.getIdGroup()==2)
        		{
        			if(building instanceof Mine){
        				shapeRenderer.begin(ShapeType.Filled);
        				shapeRenderer.setColor(Color.YELLOW);
        				shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
        	            shapeRenderer.end();
        			}
        			else if(building instanceof Tower){
        				shapeRenderer.begin(ShapeType.Filled);
        				shapeRenderer.setColor(Color.GRAY);
        				shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
        	            shapeRenderer.end();
        			}
        			else if(building instanceof Factory){
        				shapeRenderer.begin(ShapeType.Filled);
        		        shapeRenderer.setColor(Color.PURPLE);
        				shapeRenderer.rect(building.getPosition().x, building.getPosition().y, building.getWidth(), building.getHeight());
        	            shapeRenderer.end();
        	            batcher.begin();
        		        font.draw(batcher, Integer.toString(((Factory) building).getTankNumber()),building.getPosition().x, building.getPosition().y);
        		        batcher.end();
        			}
        		}
               
        	}
        }        
        	      		
        		if(hud.connectionId==1){
        			shapeRenderer.begin(ShapeType.Line);
        		    shapeRenderer.setColor(Color.RED);
        		    shapeRenderer.line(myWorld.getTargetLine().get(0).get(0),myWorld.getTargetLine().get(0).get(1));        		       
        		    shapeRenderer.end();
        		    shapeRenderer.begin(ShapeType.Line);
        		    shapeRenderer.setColor(Color.RED);
        		    shapeRenderer.line(myWorld.getTargetLine().get(0).get(1),myWorld.getTargetLine().get(0).get(2));        		       
        		    shapeRenderer.end();
        		}
        		else if(hud.connectionId==2)
        		{
        			shapeRenderer.begin(ShapeType.Line);
        		    shapeRenderer.setColor(Color.RED);
        		    shapeRenderer.line(myWorld.getTargetLine().get(1).get(0),myWorld.getTargetLine().get(1).get(1));        		       
        		    shapeRenderer.end();
        		    shapeRenderer.begin(ShapeType.Line);
        		    shapeRenderer.setColor(Color.RED);
        		    shapeRenderer.line(myWorld.getTargetLine().get(1).get(1),myWorld.getTargetLine().get(1).get(2));        		       
        		    shapeRenderer.end();
        		}
        		 for(ArrayList<Tank> towerList : myWorld.getTankList()){
        	        	for(Tank tank :towerList ){        		
        	        		shapeRenderer.begin(ShapeType.Filled);
	        		        shapeRenderer.setColor(Color.PINK);
	        				shapeRenderer.rect(tank.getPosition().x, tank.getPosition().y, tank.getWidth(), tank.getHeight());
	        	            shapeRenderer.end();
        	        		
        	               
        	        	}
        	        }
        
       hud.render(runTime);
       
       //testy
//       shapeRenderer.begin(ShapeType.Line);
//       shapeRenderer.setColor(Color.RED);
//       shapeRenderer.line(0,360,1280,360);
//       
//       shapeRenderer.end();
         
    }  
    public HUD getHud() {
		return hud;
	}

}
