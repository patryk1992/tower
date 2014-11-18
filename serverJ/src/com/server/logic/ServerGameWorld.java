package com.server.logic;

import java.util.ArrayList;
import java.util.UUID;

import myServer.MyServer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Castle;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.Tank;
import com.mygdx.gameworld.GameWorld;

public class ServerGameWorld {

	private Server server;
	int midPointY;
	GameWorld gameWorld;
	ArrayList<ArrayList<Tank>> temporaryTankList =new ArrayList<ArrayList<Tank>>(3);

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
		synchronized (gameWorld) {
			server.sendToAllTCP(gameWorld);
		}
		produce(time);
		goAttack();
		setOnStartPositionTanks();
	}
	
	public void produce(long time) {
		for (ArrayList<Building> towerList : gameWorld.getTowerList()) {
			for (Building building : towerList) {
				if(building instanceof Factory){
    				((Factory) building).produce(time);
    			}//dopisac mine i tower
			}
		}

	}
	public void goAttack() {
		Tank tmp;
//		ArrayList<Tank> StoppedTankList=new ArrayList<Tank>();//stop
		for (ArrayList<Tank> tankList : gameWorld.getTankList()) {
			for (Tank tank : tankList) {
				tmp=tank.collides(tankList);
				if(tmp==null){//jesli jest kolizja z innym tankiem to stop
					if(tank.move()){
						tank.goTo(gameWorld.getTargetLine().get(tank.getIdGroup()-1).get(2));
					}
				}
				else{
//					StoppedTankList.add(tmp);//stop
					tankList.remove(tank);//wybuch
					tankList.remove(tmp);					
					break;
				}
			}
		
		}
	}
	public void deployTanks(int tanksNumber,int idConnection){
		
		for(int i=0;i<tanksNumber;i++){
			Tank tank=new Tank(gameWorld.getTargetLine().get(idConnection-1).get(0).x,gameWorld.getTargetLine().get(idConnection-1).get(0).y,20,20,idConnection,UUID.randomUUID().toString());
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
