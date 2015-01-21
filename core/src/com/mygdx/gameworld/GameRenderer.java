package com.mygdx.gameworld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Plane;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameobjects.iFire;
import com.mygdx.helpers.AssetLoaderSingleton;
import com.mygdx.simpleobjects.MyCircle;
import com.mygdx.simpleobjects.MyRectangle;
import com.sun.org.apache.bcel.internal.generic.I2F;

public class GameRenderer {

	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private GameWorld myWorld;
	
	private HUD hud;	
	private SpriteBatch batcher;	
	private BitmapFont font;
	
	private int midPointY;
    private int gameWidth;  
    private AssetLoaderSingleton singleton;

    public GameRenderer(int gameWidth, int midPointY) {                    
        Gdx.gl20.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        this.gameWidth = gameWidth;
        this.midPointY = midPointY;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, midPointY*2);
        singleton=AssetLoaderSingleton.getInstance();
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);  
        font = new BitmapFont(true);
        font.setColor(Color.BLACK);
        hud=new HUD(shapeRenderer,batcher,font,gameWidth, midPointY);     
        
     
    }
    
   
    public void render(float runTime) {
    	
    	Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);           
      
		
	
        batcher.begin();
        batcher.enableBlending();
        batcher.draw(singleton.background, 0, 0, gameWidth, midPointY*2);
        batcher.end();
        if (myWorld!=null) {
        	batcher.begin();
	        batcher.disableBlending();
	        batcher.draw(singleton.base[0], myWorld.getCastles()[0].getDimension().getPosition().x, myWorld.getCastles()[0].getDimension().getPosition().y, ((MyRectangle) myWorld.getCastles()[0].getDimension()).getWidth(), ((MyRectangle) myWorld.getCastles()[0].getDimension()).getHeight());        
	        batcher.draw(singleton.base[1], myWorld.getCastles()[1].getDimension().getPosition().x, myWorld.getCastles()[1].getDimension().getPosition().y, ((MyRectangle) myWorld.getCastles()[1].getDimension()).getWidth(), ((MyRectangle) myWorld.getCastles()[1].getDimension()).getHeight());        
	        batcher.enableBlending();
	        font.draw(batcher, Integer.toString(myWorld.getCastles()[0].getLives()), myWorld.getCastles()[0].getDimension().getPosition().x+4, myWorld.getCastles()[0].getDimension().getPosition().y+4);
	        font.draw(batcher, Integer.toString(myWorld.getCastles()[1].getLives()), myWorld.getCastles()[1].getDimension().getPosition().x+4, myWorld.getCastles()[1].getDimension().getPosition().y+4);
	        batcher.end();      
        
	        batcher.begin();       
	        batcher.disableBlending();
	        for(ArrayList<Building> towerList : myWorld.getTowerList()){
	        	for(Building building :towerList ){        		
	        		
	        			if(building instanceof Mine){
	        				batcher.draw(singleton.mine[building.getIdGroup()-1], building.getDimension().getPosition().x, building.getDimension().getPosition().y, ((MyRectangle) building.getDimension()).getWidth(), ((MyRectangle) building.getDimension()).getHeight());        
	       				
	        			}
	        			else if(building instanceof Tower){
	        				batcher.draw(singleton.tower[building.getIdGroup()-1], building.getDimension().getPosition().x, building.getDimension().getPosition().y, ((MyRectangle) building.getDimension()).getWidth(), ((MyRectangle) building.getDimension()).getHeight());        
	        				
	        			}
	        			else if(building instanceof Factory){   
	        				batcher.draw(singleton.factory[building.getIdGroup()-1], building.getDimension().getPosition().x, building.getDimension().getPosition().y, ((MyRectangle) building.getDimension()).getWidth(), ((MyRectangle) building.getDimension()).getHeight());        
	        				
	                        batcher.enableBlending();;
	        		        font.draw(batcher, Integer.toString(((Factory) building).getTankNumber()),building.getDimension().getPosition().x+2, building.getDimension().getPosition().y+1);
	        		        batcher.disableBlending();;
	        			}
	        		}        	
	        	}        
	        batcher.enableBlending();
	        batcher.end();
	        		
	        shapeRenderer.begin(ShapeType.Line);
	        shapeRenderer.setColor(Color.RED);
	        shapeRenderer.line(myWorld.getTargetLine().get(hud.connectionId-1).get(0).x-5,myWorld.getTargetLine().get(hud.connectionId-1).get(0).y+10,myWorld.getTargetLine().get(hud.connectionId-1).get(1).x,myWorld.getTargetLine().get(hud.connectionId-1).get(1).y+10);        		       
	        shapeRenderer.end();
	        shapeRenderer.begin(ShapeType.Line);
	        shapeRenderer.setColor(Color.RED);
	        shapeRenderer.line(myWorld.getTargetLine().get(hud.connectionId-1).get(1).x,myWorld.getTargetLine().get(hud.connectionId-1).get(1).y+10,myWorld.getTargetLine().get(hud.connectionId-1).get(2).x,myWorld.getTargetLine().get(hud.connectionId-1).get(2).y+10);        		       
	        shapeRenderer.end();
	        
	        batcher.begin();
	        batcher.enableBlending();
	        for(ArrayList<Plane> towerList : myWorld.getTankList()){
	        	   for(Plane plane :towerList ){       		   
	        		   float x=plane.getDimension().getPosition().x;
	        		   float y=plane.getDimension().getPosition().y;
	        		   float width=((MyRectangle) plane.getDimension()).getWidth();
	        		   float height=((MyRectangle) plane.getDimension()).getHeight();       		           	
	        		   batcher.draw(AssetLoaderSingleton.plane[plane.getIdGroup()-1], x, y,width/2,height/2, width, height, 1, 1, (float) plane.getDegrees(), true); //90 stopni to poziomo
	        		     	               
	        	    }
	        }
	         
	        for (ArrayList<Bullet> bulletList : myWorld.getBulletList()) {
	        	for (Bullet bullet:bulletList) {
	        		batcher.draw(singleton.bulletsAnimation[bullet.getTargetType()].getKeyFrame(runTime), bullet.getDimension().getPosition().x, bullet.getDimension().getPosition().y, ((MyCircle) bullet.getDimension()).getRadius()*2,((MyCircle) bullet.getDimension()).getRadius()*2);        		
	        	}
	        }
	        batcher.end();
	       
      }
       hud.render(runTime);
       if(myWorld!=null){
    	   batcher.begin();
    	   font.draw(batcher, Integer.toString(myWorld.getCastles()[hud.connectionId-1].getCoins()),hud.getiCoins().getPosition().x,hud.getiCoins().getPosition().y+8);
    	   batcher.end();
       }
    }  
    public HUD getHud() {
		return hud;
	}
    public void setMyWorld(GameWorld myWorld) {
		this.myWorld = myWorld;
		
	}
    public GameWorld getMyWorld() {
		return myWorld;
	}
}
