package com.mygdx.patterncommand;

import java.util.ArrayList;

import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Plane;
import com.mygdx.gameobjects.Tower;

public class TowerCommand implements Command{
	private Tower tower;
	private long time;
	private ArrayList<ArrayList<Plane>> arrayListPlane;
	private ArrayList<ArrayList<Bullet>> arrayListBullet;
	public TowerCommand(Tower tower,long time, ArrayList<ArrayList<Plane>> arrayListPlane, ArrayList<ArrayList<Bullet>> arrayListBullet) {
		this.tower = tower;
		this.time=time;
		this.arrayListPlane=arrayListPlane;
		this.arrayListBullet=arrayListBullet;
	}
	@Override
	public void execute() {		
			
			Bullet bullet=(Bullet) tower.fire(time, arrayListPlane.get(tower.getIdGroup()==2?0:1));
			if(bullet!=null){
				synchronized (arrayListBullet) {
					arrayListBullet.get(tower.getIdGroup()-1).add(bullet);
				}						
			}
		
	}
	

	
}
