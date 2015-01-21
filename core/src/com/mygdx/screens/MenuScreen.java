package com.mygdx.screens;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import myServer.LogicGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

public class MenuScreen implements Screen {

	private SpriteBatch batcher;
	private MyGdxGame game;
	private Skin skin;
	private Stage stage;
	private Label labelMessage;
	private TextButton connectButton;
	private TextButton startServerButton;
	public TextArea textIPAddress;	

	public MenuScreen(MyGdxGame game) {
		this.game = game;		
	}

	@Override
	public void show() {

		float width = 960;
		float height = 540;

		batcher = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();		
		Gdx.input.setInputProcessor(stage);		
		VerticalGroup vg = new VerticalGroup().space(5).pad(5).fill();		
		vg.setBounds(100, 0, width - 200, height );

		labelMessage = new Label(" ", skin);
		connectButton = new TextButton("Connect to server", skin);
		startServerButton = new TextButton("Start server", skin);		
	
		vg.addActor(labelMessage);
		vg.addActor(connectButton);
		vg.addActor(startServerButton);	
		
		stage.addActor(vg);

		stage.getCamera().viewportWidth = width;
		stage.getCamera().viewportHeight = Gdx.graphics.getHeight();
		stage.getCamera().position.set(width / 2, height / 2, 0);

		startServerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new Thread(
						new Runnable() {
					@Override
					public void run() {						
						LogicGame.start();
						// serwer start
					}
				}).start();
				
				try {
					
					GameScreen gameScreen=new GameScreen(game);					
					game.setScreen(gameScreen);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		connectButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try{				
				GameScreen gameScreen=new GameScreen(game);				
				game.setScreen(gameScreen);
				}
				catch(Exception e){
					labelMessage.setText("Cant connect to server");
				}
			}
		});

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batcher.begin();
		stage.draw();
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		

	}

}
