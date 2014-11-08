package com.mygdx.gameworld;

import java.util.ArrayList;
import java.util.UUID;


import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Tower;

public class GameWorld {

	private Castle []castles;

	ArrayList<ArrayList<Tower>> towerList = new ArrayList<ArrayList<Tower>>(3);
	


	public GameWorld(int midPointY){
		castles=new Castle[2];
		castles[0]=new Castle(5,midPointY-40,80,80,1,UUID.randomUUID().toString(),Color.GREEN);
		castles[1]=new Castle(1195,midPointY-40,80,80,2,UUID.randomUUID().toString(),Color.RED);
		ArrayList<Tower> towerList1;
		ArrayList<Tower> towerList2;
		towerList1=new ArrayList<Tower>();
		towerList2=new ArrayList<Tower>();
		towerList.add(towerList1);
		towerList.add(towerList2);
	}
	
	
	public GameWorld(){
		
	}
	public ArrayList<ArrayList<Tower>> getTowerList() {
		return towerList;
	}
	public Castle[] getCastles() {
		return castles;
	}
	
	public void setCastles(Castle[] castles) {
		this.castles = castles;
	}

		

}
