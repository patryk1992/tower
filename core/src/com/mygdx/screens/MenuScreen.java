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

		// Load our UI skin from file. Once again, I used the files included in
		// the tests.
		// Make sure default.fnt, default.png, uiskin.[atlas/json/png] are all
		// added to your assets
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		// Wire the stage to receive input, as we are using Scene2d in this
		// example
		Gdx.input.setInputProcessor(stage);
		

		// Now setupt our scene UI

		// Vertical group groups contents vertically. I suppose that was
		// probably pretty obvious
		VerticalGroup vg = new VerticalGroup().space(5).pad(5).fill();// .space(2).pad(5).fill();//.space(3).reverse().fill();
		// Set the bounds of the group to the entire virtual display
		vg.setBounds(100, 0, width - 200, height );

		// Create our controls
		labelMessage = new Label(" ", skin);
		connectButton = new TextButton("Connect to server", skin);
		startServerButton = new TextButton("Start server", skin);
		
		
		// Add them to scene
		vg.addActor(labelMessage);
		vg.addActor(connectButton);
		vg.addActor(startServerButton);
	

		// Add scene to stage
		stage.addActor(vg);

		// Setup a viewport to map screen to a 480x640 virtual resolution
		// As otherwise this is way too tiny on my 1080p android phone.
		// stage.setViewport(new ExtendViewport(VIRTUAL_SCREEN_WIDTH,
		// VIRTUAL_SCREEN_HEIGHT));
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
					game.setScreen(new GameScreen());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Wire up a click listener to our button
		connectButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try{
				game.setScreen(new GameScreen());
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
		// TODO Auto-generated method stub

	}

}
