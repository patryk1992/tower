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
	ArrayList<ArrayList<Tank>> tankList =new ArrayList<ArrayList<Tank>>(3);

	


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
		ArrayList<Tank> tankList1=new ArrayList<Tank>(3);
		ArrayList<Tank> tankLIst2=new ArrayList<Tank>(3);
		tankList.add(tankList1);
		tankList.add(tankLIst2);
		
	}
	
	
	public GameWorld(){
		
	}
	public ArrayList<ArrayList<Building>> getTowerList() {
		return towerList;
	}
	public ArrayList<ArrayList<Vector2>> getTargetLine() {
		return targetLine;
	}

	public ArrayList<ArrayList<Tank>> getTankList() {
		return tankList;
	}
	public Castle[] getCastles() {
		return castles;
	}
	
	public void setCastles(Castle[] castles) {
		this.castles = castles;
	}
	public void deployTanks(int tanksNumber,int idConnection){
		
		for(int i=0;i<tanksNumber;i++){
			Tank tank=new Tank(targetLine.get(idConnection-1).get(0).x-i*25,targetLine.get(idConnection-1).get(0).y,20,20,idConnection,UUID.randomUUID().toString());
			tankList.get(idConnection-1).add(tank);
		}
	}
	
		

}
