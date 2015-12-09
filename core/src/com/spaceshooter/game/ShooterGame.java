package com.spaceshooter.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShooterGame extends ApplicationAdapter {
	public static final int SCREEN_HEIGHT = 480;
	public static final int SCREEN_WIDTH = 800;
	
	private SpriteBatch batch;
	private Texture background;
	private OrthographicCamera camera;
	//private Sprite spaceshipSprite;
	
	private AnimatedSprite spaceshipAnimated;
	private ShotManager shotManager;
	private Music gameMusic;
	
	
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT); // force rescaling so that the background covers the entire screen.
		
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("space-background.png")); // load the background.
		
		Texture spaceshiptTexture = new Texture(Gdx.files.internal("spaceship-spritesheet.png")); // load the spaceship asset.
		Sprite spaceshipSprite = new Sprite(spaceshiptTexture);
		// spaceshipSprite.setPosition((background.getWidth() / 2) - (spaceshipSprite.getWidth() / 2), 0);
		
		spaceshipAnimated = new AnimatedSprite(spaceshipSprite);
		spaceshipAnimated.setPosition(background.getWidth() / 2, 0);
		
		Texture shotTexture = new Texture(Gdx.files.internal("projectile-spritesheet.png"));
		shotManager = new ShotManager(shotTexture);
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_background.wav")); // load up the music to be played.
		gameMusic.setVolume(.25f); // set the volume to quarter of the original volume.
		gameMusic.setLooping(true); // true to keep the music looping.
		gameMusic.play(); // play the music.
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
	    batch.begin();
		batch.draw(background,0,0);
		spaceshipAnimated.draw(batch);
		shotManager.draw(batch);
		batch.end();
		
		handleInput(); // handle the user input .
		
		spaceshipAnimated.move(); // now move the animated sprite based on the user input values.
		shotManager.update();
	}

	private void handleInput() {
		if (Gdx.input.isTouched()) 
		{
			Vector3 touchPosition = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0); // translate the touch coordinates to same system as the camera.
			camera.unproject(touchPosition);
			
			
			if(touchPosition.x > spaceshipAnimated.getX())
			{
				spaceshipAnimated.moveRight(); // move right if the user input's x coordinate is greater than spaceship's coordinate.
			}
			else {
				spaceshipAnimated.moveLeft(); // mover left otherwise.
			}
			
			shotManager.firePlayerShot(spaceshipAnimated.getX());
		}
	}
	
}
