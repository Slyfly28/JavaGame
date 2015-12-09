package com.spaceshooter.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ShotManager {

	private static final int SHOT_SPEED = 300;
	private static final int SHOT_Y_OFFSET = 90;
	private static final float MINIMUM_TIME_BETWEEN_SHOTS = 0.5f;
	private Texture shotTexture;
	private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
	private float timeSinceLastShot = 0;
	private Sound laser = Gdx.audio.newSound(Gdx.files.internal("laser.wav")); // load up the sound to be used when firing shots.

	public ShotManager(Texture shotTexture) {
		
		this.shotTexture = shotTexture;
		
	}

	public void firePlayerShot(int shipCenterXLocation) 
	{
		
		if (canFireShot())
		{
			Sprite newShot = new Sprite(shotTexture);
			AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
			newShotAnimated.setPosition(shipCenterXLocation, SHOT_Y_OFFSET); // positioning of the shot
			newShotAnimated.setVelocity(new Vector2(0,SHOT_SPEED)); // setting the speed of the shot.
			shots.add(newShotAnimated); // add every shot to list for easy deallocation of memory.
			timeSinceLastShot = 0f;
			laser.play(); // plays every time a shot is fired.
		}
	}

	private boolean canFireShot() 
	{
		return timeSinceLastShot > MINIMUM_TIME_BETWEEN_SHOTS;
	}

	public void update() 
	{
		Iterator<AnimatedSprite> i = shots.iterator();
		while(i.hasNext())
		{
			AnimatedSprite shot = i.next();
			shot.move(); // use the move method of the Animated Sprite
			
			if(shot.getY() > ShooterGame.SCREEN_HEIGHT)
				i.remove(); // deallocate all the shots stored in the list.
		}
		
		timeSinceLastShot += Gdx.graphics.getDeltaTime();
	}

	public void draw(SpriteBatch batch) {
		for(AnimatedSprite shot : shots)
		{
			shot.draw(batch);
		}
		
	}

}
