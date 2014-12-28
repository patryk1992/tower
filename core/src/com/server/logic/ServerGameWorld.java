package com.server.logic;

import java.util.ArrayList;
import java.util.UUID;

import sun.nio.cs.ext.TIS_620;
import myServer.MyServer;

import com.Client.packets.Packet.PacketEndGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameobjects.Base;
import com.mygdx.gameobjects.Bullet;
import com.mygdx.gameobjects.Factory;
import com.mygdx.gameobjects.GameObject;
import com.mygdx.gameobjects.Mine;
import com.mygdx.gameobjects.Plane;
import com.mygdx.gameobjects.Tower;
import com.mygdx.gameobjects.iFire;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.patterncommand.Command;
import com.mygdx.patterncommand.FactoryCommand;
import com.mygdx.patterncommand.MineCommand;
import com.mygdx.patterncommand.TowerCommand;

public class ServerGameWorld {

	Server server;
	int midPointY;
	GameWorld gameWorld;
	ArrayList<ArrayList<Plane>> temporaryTankList =new ArrayList<ArrayList<Plane>>(3);
	long time;
	Boolean endGameBoolean;
	
	
	public ServerGameWorld(int midPointY, Server server) {
		this.server = server;
		this.midPointY = midPointY;
		this.endGameBoolean=false;
		gameWorld = new GameWorld(midPointY);
		server.sendToAllTCP(gameWorld);
		ArrayList<Plane> tankList1=new ArrayList<Plane>(3);
		ArrayList<Plane> tankLIst2=new ArrayList<Plane>(3);
		temporaryTankList.add(tankList1);
		temporaryTankList.add(tankLIst2);
		
	}

	public void update(long time) {
		this.time=time;
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
				 * Update target gdy czo³g/
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
						if(target!=null&&((Building) target).shooted()<=0){
							synchronized(gameWorld){
								gameWorld.getTowerList().get(tmpGroupId).remove(target);
							}
						}
					}
					else if(target instanceof Plane){
						if(target!=null&&((Plane) target).shooted()<=0){
							synchronized(gameWorld){
								gameWorld.getTankList().get(tmpGroupId).remove(target);
							}
						}
					}
				}
			}
			synchronized (gameWorld) {
				bulletList.removeAll(removeBulletList);
			}
			
		}
		
	}
	

	public void performTowerAction(long time) {
		ArrayList<Command> listCommand =new ArrayList<Command>();
		for (ArrayList<Building> towerList : gameWorld.getTowerList()) {
			synchronized(gameWorld){
				for (Building building : towerList) {
					if(building instanceof Factory){
//	    				((Factory) building).produce(time);
	    				listCommand.add(new FactoryCommand(((Factory) building), time));
	    			}
					else if(building instanceof Tower){
						listCommand.add(new TowerCommand(((Tower) building),time,gameWorld.getTankList(),gameWorld.getBulletList()));
	    			}
					else if(building instanceof Mine){
//						gameWorld.getCastles()[building.getIdGroup()-1].addCoins(((Mine) building).extract(time));
						listCommand.add(new MineCommand(((Mine) building), time, gameWorld.getCastles()[building.getIdGroup()-1]));
	    			}
				}
			}
			for(Command tmpCommand:listCommand ){
				tmpCommand.execute();
			}
		}

	}
	public void goAttack(long time) {
		Plane tmp;
		int tmpGroupId;
		for (ArrayList<Plane> planesList : gameWorld.getTankList()) {
			for (Plane plane : planesList) {
				/////////////////////////////////////////////////////////////////////////////////sprawdzenie koloizi z Base przeciwnika dlategi inne idgroup
				if(plane.getIdGroup()==2){
					tmpGroupId=0;
				}
				else
				{
					tmpGroupId=1;
				}
				if(plane.collides(gameWorld.getCastles()[tmpGroupId])!=null){
					planesList.remove(plane);
					gameWorld.getCastles()[tmpGroupId].reduceLives();
					if(gameWorld.getCastles()[tmpGroupId].getLives()<1){
						endGameBoolean=true;
						PacketEndGame packetEndGameAnswer=new PacketEndGame();
						packetEndGameAnswer.idWinner=plane.getIdGroup();
						server.sendToAllTCP(packetEndGameAnswer);
						cleanBeforRestart(); //wyczyszczenie list w przypadku restartu
					}
					break;
				}
				/////////////////////////////////////////////////////////////////////////////////strzelanie do innych budynków	zainicjowanie wystrza³u		 				
				synchronized (gameWorld) {
					Bullet bullet=plane.fire(time, gameWorld.getTowerList().get(tmpGroupId));				
					if(bullet!=null){					
						gameWorld.getBulletList().get(plane.getIdGroup()-1).add(bullet);
					}
				}
			
				/*
				 * sprawdzanie kolizji z innymi samolotami
				 * */
				tmp=(Plane) plane.collides(planesList);
				if(tmp==null){//jesli jest kolizja z innym
					if(plane.move()){
						plane.goTo(gameWorld.getTargetLine().get(plane.getIdGroup()-1).get(2));
					}
				}
				else{
					planesList.remove(plane);//wybuch
					planesList.remove(tmp);					
					break;
				}
			}
		
		}

		
	}
	
	public void deployTanks(int tanksNumber,int idConnection){
		
		for(int i=0;i<tanksNumber;i++){
			Plane tank=new Plane(gameWorld.getTargetLine().get(idConnection-1).get(0).x,gameWorld.getTargetLine().get(idConnection-1).get(0).y,20,20,idConnection,UUID.randomUUID().toString());
			tank.goTo(gameWorld.getTargetLine().get(idConnection-1).get(1));
			temporaryTankList.get(idConnection-1).add(tank);
		}
	}
	public void setOnStartPositionTanks(){	
		synchronized (gameWorld) {		
					for (ArrayList<Plane> tmpTankList : temporaryTankList) {			
						for (Plane newTank : tmpTankList) {									
									if(!newTank.collides(gameWorld.getTankList().get(newTank.getIdGroup()-1),-20,-20,40,40)){
										gameWorld.getTankList().get(newTank.getIdGroup()-1).add(newTank);
										tmpTankList.remove(newTank);
										break;//ConcurrentModificationException i lepsza wydajnoœæ 
										}
									}					
					
					}
		}
	}
	public Server getServer() {
		return server;
	}
	public Boolean getEndGameBoolean() {
		return endGameBoolean;
	}

	public void setEndGameBoolean(Boolean endGameBoolean) {
		this.endGameBoolean = endGameBoolean;
	}

	public long getTime() {
		return time;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}
	
	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}
	public void intRestart(){
		endGameBoolean=false;
		this.gameWorld= new GameWorld(midPointY);		
	}
	public void cleanBeforRestart(){
		temporaryTankList.get(0).clear();
		temporaryTankList.get(1).clear();
		gameWorld= new GameWorld(midPointY);	
	}

}
