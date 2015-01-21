package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class AssetLoaderSingleton {

	private static volatile AssetLoaderSingleton instance = null;
	
    public static Texture texture;
    public static Texture background;
    public static TextureRegion base[];
    public static Texture coins;
    public static Texture win,lose;
    public static Texture communiques;
    public static TextureRegion lostConnection,waiting;
    
    public static Texture buttons;
    public static TextureRegion end,restart;
    
    public static TextureRegion tower[], factory[], mine[];
    public static TextureRegion plane[];

    public static Animation bulletsAnimation[];
    public static TextureRegion bullets[];
    public static TextureRegion bullets2[];    
    
    public static AssetLoaderSingleton getInstance() {
        if (instance == null) {
            synchronized (AssetLoaderSingleton.class) {
                if (instance == null) {
                    instance = new AssetLoaderSingleton();
                }
            }
        }
        return instance;
    }
    private AssetLoaderSingleton() {

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);      
        
        background = new Texture(Gdx.files.internal("data/background.png"));
        background.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); 
        
        coins = new Texture(Gdx.files.internal("data/coins.png"));
        coins.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        win = new Texture(Gdx.files.internal("data/win.png"));
        win.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        lose = new Texture(Gdx.files.internal("data/lose.png"));
        lose.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        buttons = new Texture(Gdx.files.internal("data/buttons.png"));
        buttons.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        communiques = new Texture(Gdx.files.internal("data/communiques.png"));
        communiques.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        lostConnection = new TextureRegion(communiques,0,0,5837,505);
        lostConnection.flip(false, true);        
        waiting = new TextureRegion(communiques, 0, 505, 5837, 1080);
        waiting.flip(false, true);
        
        restart = new TextureRegion(buttons, 0, 0, 320, 72);
        restart.flip(false, true);         
       	end = new TextureRegion(buttons, 0, 90, 320, 65);
        end.flip(false, true);      
        
        base=new TextureRegion[2];
        base[0] = new TextureRegion(texture, 436, 404, 212, 212);
        base[0].flip(false, true);        
        base[1] = new TextureRegion(texture, 675, 404, 212, 212);
        base[1].flip(false, true);

        factory=new TextureRegion[2];
        factory[0] = new TextureRegion(texture, 1, 0, 188, 188);
        factory[0].flip(false, true);        
        factory[1] = new TextureRegion(texture, 212, 0, 188, 188);
        factory[1].flip(false, true);
        
        mine=new TextureRegion[2];
        mine[0] = new TextureRegion(texture, 1, 200, 188, 188);
        mine[0].flip(false, true);        
        mine[1] = new TextureRegion(texture, 212, 200, 188, 188);
        mine[1].flip(false, true);
        
        tower=new TextureRegion[2];
        tower[0] = new TextureRegion(texture, 1, 403, 188, 188);
        tower[0].flip(false, true);        
        tower[1] = new TextureRegion(texture, 212, 403, 188, 188);
        tower[1].flip(false, true);

        plane=new TextureRegion[2];
        plane[0] = new TextureRegion(texture, 452, 11, 186, 152);
        plane[0].flip(false, true);        
        plane[1] = new TextureRegion(texture, 452, 221, 186, 152);
        plane[1].flip(false, true);
        
        bullets=new TextureRegion[4];
        bullets[0]= new TextureRegion(texture, 735, 44, 37, 37);
        bullets[0].flip(false, true);
        bullets[1] = new TextureRegion(texture, 830, 44, 37, 37);
        bullets[1].flip(false, true);
        bullets[2] = new TextureRegion(texture, 914, 44, 37, 37);
        bullets[2].flip(false, true);
        bullets[3] = new TextureRegion(texture, 987, 44, 37, 37);
        bullets[3].flip(false, true);
        bulletsAnimation = new Animation[2];
        bulletsAnimation[0] = new Animation(0.06f, bullets);
        bulletsAnimation[0].setPlayMode(Animation.PlayMode.LOOP_RANDOM);
       
        bullets2=new TextureRegion[4];
        bullets2[0]= new TextureRegion(texture, 735, 110, 37, 37);
        bullets2[0].flip(false, true);
        bullets2[1] = new TextureRegion(texture, 830, 110, 37, 37);
        bullets2[1].flip(false, true);
        bullets2[2] = new TextureRegion(texture, 914, 110, 37, 37);
        bullets2[2].flip(false, true);
        bullets2[3] = new TextureRegion(texture, 987, 110, 37, 37);
        bullets2[3].flip(false, true);
        bulletsAnimation[1] = new Animation(0.06f, bullets2);
        bulletsAnimation[1].setPlayMode(Animation.PlayMode.LOOP_RANDOM);

	}
	

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        background.dispose();
    }

}