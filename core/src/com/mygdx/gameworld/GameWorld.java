package com.mygdx.gameworld;

import java.util.ArrayList;
import java.util.UUID;




import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Tank;

public class GameWorld {

	private Castle []castles;

	ArrayList<ArrayList<Building>> towerList = new ArrayList<ArrayList<Building>>(3);
	ArrayList<ArrayList<Vector2>> targetLine =new ArrayList<ArrayList<Vector2>>(3);
	ArrayList<ArrayList<Vector2>> tankList =new ArrayList<ArrayList<Vector2>>(3);

	

	public GameWorld(int midPointY){
		castles=new Castle[2];
		castles[0]=new Castle(5,midPointY-40,80,80,1,UUID.randomUUID().toString(),Color.GREEN);
		castles[1]=new Castle(1195,midPointY-40,80,80,2,UUID.randomUUID().toString(),Color.RED);
		ArrayList<Building> towerList1;
		ArrayList<Building> towerList2;
		towerList1=new ArrayList<Building>();
		towerList2=new ArrayList<Building>();
		towerList.add(towerList1);
		towerList.add(towerList2);
		ArrayList<Vector2> targetLine1=new ArrayList<Vector2>(3);
		ArrayList<Vector2> targetLine2=new ArrayList<Vector2>(3);
		targetLine1.add(new Vector2(640, midPointY));
		targetLine1.add(new Vector2(1280, midPointY));
		targetLine2.add(new Vector2(640, midPointY));
		targetLine2.add(new Vector2(0, midPointY));			
		targetLine.add(targetLine1);
		targetLine.add(targetLine2);
		
	}
	
	
	public GameWorld(){
		
	}
	public ArrayList<ArrayList<Building>> getTowerList() {
		return towerList;
	}
	public ArrayList<ArrayList<Vector2>> getTargetLine() {
		return targetLine;
	}

	public Castle[] getCastles() {
		return castles;
	}
	
	public void setCastles(Castle[] castles) {
		this.castles = castles;
	}
	
	
		

}
