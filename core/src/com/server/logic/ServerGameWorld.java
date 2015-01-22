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
import com.mygdx.patterncommand.PerformBuildingAction;
import com.mygdx.patterncommand.TowerCommand;

public class ServerGameWorld {

	Server server;
	int midPointY;
	GameWorld gameWorld;
	ArrayList<ArrayList<Plane>> temporaryPlaneArrayList =new ArrayList<ArrayList<Plane>>(3);
	double time;
	Boolean endGameBoolean;
	PerformBuildingAction performBuildingAction;
	
	public ServerGameWorld(int midPointY, Server server) {
		this.server = server;
		this.midPointY = midPointY;
		this.endGameBoolean=false;
		gameWorld = new GameWorld(midPointY);
		server.sendToAllTCP(gameWorld);
		ArrayList<Plane> tankList1=new ArrayList<Plane>(3);
		ArrayList<Plane> tankLIst2=new ArrayList<Plane>(3);
		temporaryPlaneArrayList.add(tankList1);
		temporaryPlaneArrayList.add(tankLIst2);
		performBuildingAction=new PerformBuildingAction();
	}

	public void update(double elapsed) {
		this.time=elapsed;
		performTowerAction(elapsed);				
		setOnStartPositionPlane();
		goAttack(elapsed);
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
				GameObject targetGameObject= gameWorld.getObjectFromList(bullet.getTargetBuildingID(), gameWorld.getPlaneList().get(tmpGroupId));
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
						 target=gameWorld.getObjectFromList(bullet.getTargetBuildingID(), gameWorld.getPlaneList().get(tmpGroupId));	
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
								gameWorld.getPlaneList().get(tmpGroupId).remove(target);
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
	

	public void performTowerAction(double elapsed) {
		ArrayList<Command> listCommand =new ArrayList<Command>();
		synchronized (gameWorld) {
			for (ArrayList<Building> towerList : gameWorld.getTowerList()) {
					for (Building building : towerList) {
						if(building instanceof Factory){
		    				listCommand.add(new FactoryCommand(((Factory) building), elapsed));
		    			}
						else if(building instanceof Tower){
							listCommand.add(new TowerCommand(((Tower) building),elapsed,gameWorld.getPlaneList(),gameWorld.getBulletList()));
		    			}
						else if(building instanceof Mine){
							listCommand.add(new MineCommand(((Mine) building), elapsed, gameWorld.getCastles()[building.getIdGroup()-1]));
		    			}
					}
			}
			for(Command iterCommand:listCommand ){				
					performBuildingAction.Execute(iterCommand);			
			}
		}

	}
	public void goAttack(double elapsed) {
		Plane tmp;
		int tmpGroupId;
		for (ArrayList<Plane> planesList : gameWorld.getPlaneList()) {
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
					Bullet bullet=(Bullet) plane.fire(elapsed, gameWorld.getTowerList().get(tmpGroupId));				
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
	
	public void deployPlanes(int tanksNumber,int idConnection){		
		for(int i=0;i<tanksNumber;i++){
			Plane plane=new Plane(gameWorld.getTargetLine().get(idConnection-1).get(0).x,gameWorld.getTargetLine().get(idConnection-1).get(0).y,20,20,idConnection,UUID.randomUUID().toString());
			plane.goTo(gameWorld.getTargetLine().get(idConnection-1).get(1));
			temporaryPlaneArrayList.get(idConnection-1).add(plane);
		}
	}
	public void setOnStartPositionPlane(){	
		synchronized (gameWorld) {		
					for (ArrayList<Plane> tmpPlaneList : temporaryPlaneArrayList) {			
						for (Plane newPlane : tmpPlaneList) {									
									if(!newPlane.collides(gameWorld.getPlaneList().get(newPlane.getIdGroup()-1),-20,-20,40,40)){
										gameWorld.getPlaneList().get(newPlane.getIdGroup()-1).add(newPlane);
										tmpPlaneList.remove(newPlane);
										break;
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

	public double getTime() {
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
		temporaryPlaneArrayList.get(0).clear();
		temporaryPlaneArrayList.get(1).clear();
		gameWorld= new GameWorld(midPointY);	
	}

}
