package com.spaceshooter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class AnimatedSprite {

	private static final int SHIP_SPEED = 300;
	private static final int FRAMES_COL = 2;
	private static final int FRAMES_ROW = 2;
	
	private Sprite sprite;
	private Animation animation;
	private TextureRegion[] frames;
	private TextureRegion currentFrame;
	private Vector2 velocity = new Vector2();
	
	private float stateTime;
	
	public AnimatedSprite(Sprite sprite)
	{
		this.sprite = sprite;
		Texture texture = sprite.getTexture(); // get texture from the sprite.
		TextureRegion[][] temp = TextureRegion.split(texture, (int) getSpriteWidth(), texture.getHeight() / FRAMES_ROW); // setup a 2D array as a temp container of our sprite.
		
		frames = new TextureRegion[FRAMES_COL * FRAMES_ROW]; // texture container after splitting the images. Contains a max of 4 elements.
		int index = 0;
		for(int i = 0; i < FRAMES_ROW; i++)
		{
			for(int j = 0; j < FRAMES_COL;j++)
			{
				frames[index++] = temp[i][j]; // fills the array with images. Top left = 0, Top Right = 1, Bottom left = 2, Bottom Right = 3.
			}
		}
		
		animation = new Animation(0.1f, frames);
		stateTime = 0f;
	}
	
	public void draw(SpriteBatch spriteBatch)
	{
		stateTime += Gdx.graphics.getDeltaTime(); // keep track of the time each frame changes.
		currentFrame = animation.getKeyFrame(stateTime, true);
		
		spriteBatch.draw(currentFrame, sprite.getX(), sprite.getY());
	}
	
	public void setPosition(float x, float y)
	{
		sprite.setPosition(x - getSpriteCenterOffset(), y);
	}
	
	public float getSpriteCenterOffset()
	{
		return getSpriteWidth() / 2;
	}
	
	public float getSpriteWidth()
	{
		return sprite.getWidth() / FRAMES_COL;
	}
	
	public void moveRight()
	{
		velocity = new Vector2(SHIP_SPEED,0); // move the ship the set amount of pixels to the right.
	}
	
	public void moveLeft()
	{
		velocity = new Vector2(-SHIP_SPEED,0);// move the ship the set amount of pixels to the left.
	}

	public int getX() 
	{
		return (int) (sprite.getX() + getSpriteCenterOffset());
	}

	public void move() 
	{
		int xMovement = (int) (velocity.x * Gdx.graphics.getDeltaTime());
		int yMovement = (int) (velocity.y * Gdx.graphics.getDeltaTime());
		sprite.setPosition(sprite.getX() + xMovement, sprite.getY() + yMovement);
		
		if(sprite.getX() < 0) // check for the left screen boundaries
		{
			sprite.setX(0);
			velocity.x = 0;
		}
		
		if(sprite.getX() + getSpriteWidth() > ShooterGame.SCREEN_WIDTH)
		{
			sprite.setX(ShooterGame.SCREEN_WIDTH - getSpriteWidth());
			velocity.x = 0;
		}
	}
	
	public void setVelocity(Vector2 velocity)
	{
		this.velocity = velocity;
	}
}
