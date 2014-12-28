package com.mygdx.helpers;

import java.util.ArrayList;

import com.Client.packets.Packet.PacketRestartGame;
import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.Client.packets.Packet6CreateAttackPointRequest;
import com.Client.packets.Packet7ClickTowerRequest;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.MyGdxGame;
import com.mygdx.gameobjects.Building;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.HUD;
import com.mygdx.patternobserver.Observer;
import com.mygdx.patternobserver.Subject;

public class Input implements InputProcessor,Subject {
	
	private ArrayList<Observer> observers;
	private Client client;
	GameRenderer renderer;
	HUD hud;
	MyGdxGame game;
	
	public Input(Client client, GameRenderer renderer, MyGdxGame game) {
		this.client = client;
		this.renderer = renderer;		
		hud=renderer.getHud();
		this.observers=new ArrayList<Observer>();
		register(hud.getiFactory());
		register(hud.getiMine());
		register(hud.getiTower());
		hud.getiFactory().setSubject(this);
		hud.getiMine().setSubject(this);
		hud.getiTower().setSubject(this);
		this.game=game;
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	if(!hud.isEndGame() ){
		if ((hud.getiFactory().getPosition().x+hud.getiFactory().getWidth()) > screenX && client.getID() == 1
				|| hud.getiFactory().getPosition().x < screenX
				&& client.getID() == 2) {			
			notifyObservers(screenX, screenY);
		} else if((screenX<640&&client.getID()==1 )||(screenX>640&&client.getID()==2 )){			
			if (hud.getiFactory().getPressed()) {
				client.sendTCP(new Packet3CreateFactoryRequest(screenX,screenY));
			}
			else if (hud.getiTower().getPressed()) {
				client.sendTCP(new Packet5CreateTowerRequest(screenX,screenY));
			}
			else if (hud.getiMine().getPressed()) {
				client.sendTCP(new Packet4CreateMineRequest(screenX,screenY));
			}
		}
		if((screenX>640&&client.getID()==1 )||(screenX<640&&client.getID()==2 ) ){
			client.sendTCP(new Packet6CreateAttackPointRequest(screenX,screenY));
		}
		else if(hud.getiFactory().getPressed()==false && hud.getiMine().getPressed()==false && hud.getiTower().getPressed()==false){
			String clickedId=towerClicked(screenX,screenY);
			if(clickedId!=null){
				client.sendTCP(new Packet7ClickTowerRequest(clickedId));
			}
		}
	}else {
		if (hud.getEndGameButton().collides(screenX, screenY)) {	
			game.dispose();
//			System.exit(0);
			Gdx.app.exit() ;
		} else  if(hud.getRestartGameButton().collides(screenX, screenY)&&hud.getIdWinner()!=0){
			hud.setEndGame(false);
			PacketRestartGame packet=new PacketRestartGame();
			client.sendTCP(packet);
		}
		
	}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public String towerClicked(int screenX, int screenY){				
	        	for(Building building :renderer.getMyWorld().getTowerList().get(client.getID()-1) ){
	        		if(building.getDimension().collides(screenX, screenY)){
	        			return building.getId();
	        		}
	        	}
				return null;
				
	}

	@Override
	public void register(Observer obj) {
		 if(obj == null) throw new NullPointerException("Null Observer");
	        synchronized (observers) {
	        if(!observers.contains(obj)) observers.add(obj);
	        }
		
	}

	@Override
	public void unregister(Observer obj) {
		synchronized (observers) {
	        observers.remove(obj);
	        }
	}

	@Override
	public void notifyObservers(int screenX, int screenY) {
		 for (Observer observer : observers) {
	            observer.update(screenX, screenY);
	        }
	}

	@Override
	public Object getUpdate(Observer obj) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Observer> getObservers() {
		return observers;
	}
}
