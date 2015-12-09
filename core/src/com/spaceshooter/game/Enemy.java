package com.spaceshooter.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.SpawnEllipseSide;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

public class Enemy {

	private final Texture enemyTexture;
	private AnimatedSprite animatedSprite;

	public Enemy(Texture enemyTexture) {
		this.enemyTexture = enemyTexture;
		
		spawn();
	}

	private void spawn() {
		Sprite enemySprite = new Sprite(enemyTexture); // set the sprite with the specified texture.
		animatedSprite = new AnimatedSprite(enemySprite); // animate the sprite.
		int xPosition = createRandomPosition(); // set the position of spawn.
		animatedSprite.setPosition(xPosition, ShooterGame.SCREEN_HEIGHT - animatedSprite.getHeight());
	}

	private int createRandomPosition() {
		java.util.Random random = new java.util.Random(); // Random generator
		int rnum = random.nextInt(ShooterGame.SCREEN_WIDTH - animatedSprite.getWidth() + 1); // bound of where to spawn the enemy.
		return (int) (rnum + animatedSprite.getSpriteWidth() / 2);
	}

	public void draw(SpriteBatch batch) {	
		animatedSprite.draw(batch);
	}

}
