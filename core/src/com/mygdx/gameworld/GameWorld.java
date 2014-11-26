package com.mygdx.gameworld;

import java.util.ArrayList;
import java.util.UUID;






import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Tank;

public class GameWorld {

	private Base []castles;

	ArrayList<ArrayList<Building>> towerList = new ArrayList<ArrayList<Building>>(3);
	ArrayList<ArrayList<Vector2>> targetLine =new ArrayList<ArrayList<Vector2>>(3);
	ArrayList<ArrayList<Tank>> tankList =new ArrayList<ArrayList<Tank>>(3);
	ArrayList<ArrayList<Bullet>> bulletList =new ArrayList<ArrayList<Bullet>>(3);
	

	public GameWorld(int midPointY){
		castles=new Base[2];
		castles[0]=new Base(5,midPointY-40,80,80,1,UUID.randomUUID().toString(),Color.GREEN);
		castles[1]=new Base(1195,midPointY-40,80,80,2,UUID.randomUUID().toString(),Color.RED);
		ArrayList<Building> towerList1;
		ArrayList<Building> towerList2;
		towerList1=new ArrayList<Building>();
		towerList2=new ArrayList<Building>();
		towerList.add(towerList1);
		towerList.add(towerList2);
		ArrayList<Vector2> targetLine1=new ArrayList<Vector2>(3);
		ArrayList<Vector2> targetLine2=new ArrayList<Vector2>(3);
		targetLine1.add(new Vector2(640, midPointY));
		targetLine1.add(new Vector2(960, midPointY));
		targetLine1.add(new Vector2(1240, midPointY));
		targetLine2.add(new Vector2(640, midPointY));
		targetLine2.add(new Vector2(320, midPointY));
		targetLine2.add(new Vector2(40, midPointY));			
		targetLine.add(targetLine1);
		targetLine.add(targetLine2);
		ArrayList<Tank> tankList1=new ArrayList<Tank>(3);
		ArrayList<Tank> tankList2=new ArrayList<Tank>(3);
		tankList.add(tankList1);
		tankList.add(tankList2);
		ArrayList<Bullet> bulletList1=new ArrayList<Bullet>(3);
		ArrayList<Bullet> bulletList2=new ArrayList<Bullet>(3);
		bulletList.add(bulletList1);
		bulletList.add(bulletList2);
		
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
	public ArrayList<ArrayList<Bullet>> getBulletList() {
		return bulletList;
	}
	public Base[] getCastles() {
		return castles;
	}
	public GameObject getObjectFromList(String id,ArrayList<? extends GameObject> list) {
		for(GameObject object:list){
			if(object.getId().equals(id)){
				return object;
			}
		}
		return null;
	}
	
	public void setCastles(Base[] castles) {
		this.castles = castles;
	}
	
	
		

}
