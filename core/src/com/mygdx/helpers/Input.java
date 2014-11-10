package com.mygdx.helpers;

import com.Client.packets.Packet3CreateFactoryRequest;
import com.Client.packets.Packet4CreateMineRequest;
import com.Client.packets.Packet5CreateTowerRequest;
import com.badlogic.gdx.InputProcessor;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.gameworld.GameRenderer;
import com.mygdx.gameworld.GameWorld;
import com.mygdx.gameworld.HUD;

public class Input implements InputProcessor {
	private Client client;
	private GameWorld world;
	HUD hud;

	public Input(Client client, GameWorld world, HUD hud) {
		this.client = client;
		this.world = world;
		this.hud = hud;
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

		if ((hud.getiFactory().getPosition().x+hud.getiFactory().getWidth()) > screenX && client.getID() == 1
				|| hud.getiFactory().getPosition().x < screenX
				&& client.getID() == 2) {
			if (hud.getiFactory().collides(screenX, screenY)) {
				if (hud.getiFactory().getPressed() == true) {
					hud.getiFactory().setPressed(false);
				} else {
					hud.getiFactory().setPressed(true);
				}
				hud.getiMine().setPressed(false);
				hud.getiTower().setPressed(false);
			} else if (hud.getiMine().collides(screenX, screenY)) {
				if (hud.getiMine().getPressed() == true) {
					hud.getiMine().setPressed(false);
				} else {
					hud.getiMine().setPressed(true);
				}
				hud.getiFactory().setPressed(false);
				hud.getiTower().setPressed(false);
			} else if (hud.getiTower().collides(screenX, screenY)) {
				if (hud.getiTower().getPressed() == true) {
					hud.getiTower().setPressed(false);
				} else {
					hud.getiTower().setPressed(true);
				}
				hud.getiFactory().setPressed(false);
				hud.getiMine().setPressed(false);
			}
		} else {			
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

}
