package com.server.logic;

import java.util.ArrayList;
import java.util.UUID;

import myServer.MyServer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Tank;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameobjects.iFire;
import com.mygdx.gameworld.GameWorld;

public class ServerGameWorld {

	private Server server;
	int midPointY;
	GameWorld gameWorld;
	ArrayList<ArrayList<Tank>> temporaryTankList =new ArrayList<ArrayList<Tank>>(3);
	long time;

	public long getTime() {
		return time;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public ServerGameWorld(int midPointY, Server server) {
		this.server = server;
		this.midPointY = midPointY;
		gameWorld = new GameWorld(midPointY);
		server.sendToAllTCP(gameWorld);
		ArrayList<Tank> tankList1=new ArrayList<Tank>(3);
		ArrayList<Tank> tankLIst2=new ArrayList<Tank>(3);
		temporaryTankList.add(tankList1);
		temporaryTankList.add(tankLIst2);
	}

	public void update(long time) {
		this.time=time;
		synchronized (gameWorld) {		
			try{
			server.sendToUDP(server.getConnections()[1].getID(), gameWorld);
			server.sendToUDP(server.getConnections()[0].getID(), gameWorld);
			}
			catch(Exception e){
				System.out.print(e.toString());
			}
//			server.sendToAllUDP( gameWorld);
		}
		performTowerAction(time);		
		setOnStartPositionTanks();
		goAttack(time);
		moveBullets();
	}
	
	private void moveBullets() {		
		for (ArrayList<Bullet> bulletList : gameWorld.getBulletList()) {
			ArrayList<Bullet> removeBulletList=new ArrayList<Bullet>();
			for (Bullet bullet:bulletList) {
				int tmpGroupId;//id wroga
				if(bullet.getIdGroup()==2){
					tmpGroupId=0;
				}
				else
				{
					tmpGroupId=1;
				}
				/*
				 * Update target gdy czo³g/////////////opopraw
				 */
				GameObject targetGameObject= gameWorld.getObjectFromList(bullet.getTargetBuildingID(), gameWorld.getTankList().get(tmpGroupId));
				if(targetGameObject!=null){
					bullet.updatePointTarget(targetGameObject);
				}
				/*
				 * Gdy pocisk dotar³ do celu
				 */
				if(bullet.move()){
					removeBulletList.add(bullet);
					
					GameObject target=gameWorld.getObjectFromList(bullet.getTargetBuildingID(), gameWorld.getTowerList().get(tmpGroupId));
					if(target==null){
						 target=gameWorld.getObjectFromList(bullet.getTargetBuildingID(), gameWorld.getTankList().get(tmpGroupId));	
					}
					if(target instanceof Building){
						if(target!=null&&target.shooted()<=0){
							synchronized(gameWorld){
								gameWorld.getTowerList().get(tmpGroupId).remove(target);
							}
						}
					}
					else if(target instanceof Tank){
						if(target!=null&&target.shooted()<=0){
							synchronized(gameWorld){
								gameWorld.getTankList().get(tmpGroupId).remove(target);
							}
						}
					}
				}
			}
			bulletList.removeAll(removeBulletList);
		}
		
	}
	

	public void performTowerAction(long time) {
		for (ArrayList<Building> towerList : gameWorld.getTowerList()) {
			synchronized(gameWorld){
				for (Building building : towerList) {
					if(building instanceof Factory){
	    				((Factory) building).produce(time);
	    			}
					else if(building instanceof Tower){
						int tmpGroupId;//odejmowanie ¿yæ z budynku
						if(building.getIdGroup()==2){
							tmpGroupId=0;
						}
						else
						{
							tmpGroupId=1;
						}
						Bullet bullet=(Bullet) ((Tower) building).fire(time, gameWorld.getTankList().get(tmpGroupId));
						if(bullet!=null){
							gameWorld.getBulletList().get(building.getIdGroup()-1).add(bullet);
						}
	    			}
					else if(building instanceof Mine){
						gameWorld.getCastles()[building.getIdGroup()-1].addCoins(((Mine) building).extract(time));
	    			}
				}
			}
		}

	}
	public void goAttack(long time) {
		Tank tmp;
		int tmpGroupId;
		for (ArrayList<Tank> tankList : gameWorld.getTankList()) {
			for (Tank tank : tankList) {
				/////////////////////////////////////////////////////////////////////////////////sprawdzenie koloizi z Base przeciwnika dlategi inne idgroup
				if(tank.getIdGroup()==2){
					tmpGroupId=0;
				}
				else
				{
					tmpGroupId=1;
				}
				if(tank.collides(gameWorld.getCastles()[tmpGroupId])!=null){
					tankList.remove(tank);
					gameWorld.getCastles()[tmpGroupId].reduceLives();
					break;
				}
				/////////////////////////////////////////////////////////////////////////////////strzelanie do innych budynków	zainicjowanie wystrza³u		 				
				Bullet bullet=tank.fire(time, gameWorld.getTowerList().get(tmpGroupId));
				if(bullet!=null){
					gameWorld.getBulletList().get(tank.getIdGroup()-1).add(bullet);
				}
			
				/*
				 * sprawdzanie kolizji z innymi samolotami
				 * */
				tmp=(Tank) tank.collides(tankList);
				if(tmp==null){//jesli jest kolizja z innym tankiem to stop
					if(tank.move()){
						tank.goTo(gameWorld.getTargetLine().get(tank.getIdGroup()-1).get(2));
					}
				}
				else{
					tankList.remove(tank);//wybuch
					tankList.remove(tmp);					
					break;
				}
			}
		
		}

		
	}
	
	public void deployTanks(int tanksNumber,int idConnection){
		
		for(int i=0;i<tanksNumber;i++){
			Tank tank=new Tank(gameWorld.getTargetLine().get(idConnection-1).get(0).x,gameWorld.getTargetLine().get(idConnection-1).get(0).y,20,20,2,idConnection,UUID.randomUUID().toString());
			tank.goTo(gameWorld.getTargetLine().get(idConnection-1).get(1));
			temporaryTankList.get(idConnection-1).add(tank);
		}
	}
	public void setOnStartPositionTanks(){		
			for (ArrayList<Tank> tmpTankList : temporaryTankList) {			
				for (Tank newTank : tmpTankList) {									
							if(!newTank.collides(gameWorld.getTankList().get(newTank.getIdGroup()-1),-20,-20,40,40)){
								gameWorld.getTankList().get(newTank.getIdGroup()-1).add(newTank);
								tmpTankList.remove(newTank);
								break;//ConcurrentModificationException i lepsza wydajnoœæ 
								}
							}					
			
		}
	}

}
