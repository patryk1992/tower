package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion base[];

    public static TextureRegion tower[], factory[], mine[];
    public static TextureRegion plane[];

    public static Animation bulletsAnimation[];
    public static TextureRegion bullets[];
    public static TextureRegion bullets2[];
    
    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture2.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

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

//        TextureRegion[] birds = { birdDown, bird, birdUp };
//        birdAnimation = new Animation(0.06f, birds);
//        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
//
//        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
//        // Create by flipping existing skullUp
//        skullDown = new TextureRegion(skullUp);
//        skullDown.flip(false, true);
//
//        bar = new TextureRegion(texture, 136, 16, 22, 3);
//        bar.flip(false, true);

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }

}