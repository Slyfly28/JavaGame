package com.spaceshooter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShooterGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;
	private OrthographicCamera camera;
	//private Sprite spaceshipSprite;
	private AnimatedSprite spaceshipAnimated;
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480); // force rescaling so that the background covers the entire screen.
		
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("space-background.png")); // load the background.
		
		Texture spaceshiptTexture = new Texture(Gdx.files.internal("spaceship-spritesheet.png")); // load the spaceship asset.
		Sprite spaceshipSprite = new Sprite(spaceshiptTexture);
		// spaceshipSprite.setPosition((background.getWidth() / 2) - (spaceshipSprite.getWidth() / 2), 0);
		
		spaceshipAnimated = new AnimatedSprite(spaceshipSprite);
		spaceshipAnimated.setPosition(background.getWidth() / 2, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
	    batch.begin();
		batch.draw(background,0,0);
		spaceshipAnimated.draw(batch);
		batch.end();
	}
	
}
