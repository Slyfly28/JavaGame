package com.spaceshooter.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ShotManager {

	private static final int SHOT_SPEED = 300;
	private static final int SHOT_Y_OFFSET = 90;
	private Texture shotTexture;
	private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();

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
		}
	}

	private boolean canFireShot() 
	{
		return true;
	}

	public void update() 
	{
		for(AnimatedSprite shot : shots)
		{
			shot.move(); // use the move method of the Animated Sprite
		}
	}

	public void draw(SpriteBatch batch) {
		for(AnimatedSprite shot : shots)
		{
			shot.draw(batch);
		}
		
	}

}
