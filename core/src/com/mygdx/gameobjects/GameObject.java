package com.mygdx.gameobjects;
import java.util.UUID;

import com.badlogic.gdx.math.Vector2;

public class GameObject extends MyInterface{	
	String id;
	int idGroup;
	public String getId() {
		return id;
	}
	public int getIdGroup() {
		return idGroup;
	}
	public GameObject(){
		
	}	
	
	public GameObject(float x, float y, int width, int height ,int idGroup, String id){
		super(x,y,width,height);
		this.id=id;
		this.idGroup=idGroup;
	}
}
