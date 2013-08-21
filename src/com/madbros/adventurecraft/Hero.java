package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.TILE_SIZE;

import org.lwjgl.input.Keyboard;

import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

import static com.madbros.adventurecraft.Constants.*;

public class Hero {
	public AnimatedSprite sprite;

	Margin margin = new Margin(17, 17, 29, 1); //new Margin(17, 17, 45, 1); //left, right, top, bottom
	
	//absolute position of upper left corner
	public Rect aRect = new Rect(TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2,
						  TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2,
						  CHARACTER_SIZE, CHARACTER_SIZE);
	public Rect sRect = new Rect(Game.getCenterScreenX() - CHARACTER_SIZE/2, Game.getCenterScreenY() - CHARACTER_SIZE/2, CHARACTER_SIZE, CHARACTER_SIZE);
	
	boolean isMovingLeft = false, isMovingRight = false, isMovingUp = false, isMovingDown = false;
	boolean isAttacking = false;
	
	public static float currentSpeed = 0.19f;
	
	Block[] collisionDetectionBlocks = new Block[9];
		
	public Hero() {
		sprite = Textures.animatedSprites.get("heroTemp");
	}

//	public void startAttacking() {
//		timeSinceLastAnimation = 0;	//getTime()
//		isAttacking = true;
//	}
//	
//	public void stopAttacking() {
//		isAttacking = false;
//	}
	
	/************************** Movement **************************/
	public boolean isMoving() {
		return (isMovingDown || isMovingUp || isMovingLeft || isMovingRight);
	}
	
	public void moveUp() {
		if(!isMoving() || isMovingDown) sprite.changeAnimationTo("walkUp");
		isMovingUp = true;
		isMovingDown = false;
	}
	
	public void moveDown() {
		if(!isMoving() || isMovingUp) sprite.changeAnimationTo("walkDown");
		isMovingUp = false;
		isMovingDown = true;
	}
	
	public void moveLeft() {
		if(!isMoving() || isMovingRight) sprite.changeAnimationTo("walkLeft");
		isMovingLeft = true;
		isMovingRight = false;
	}
	
	public void moveRight() {
		if(!isMoving() || isMovingLeft) sprite.changeAnimationTo("walkRight");
		isMovingLeft = false;
		isMovingRight = true;
	}
	
	public void stop() {
		if(isMovingDown) sprite.changeAnimationTo("standDown");
		if(isMovingUp) sprite.changeAnimationTo("standUp");
		if(isMovingLeft) sprite.changeAnimationTo("standLeft");
		if(isMovingRight) sprite.changeAnimationTo("standRight");
		isMovingDown = false; isMovingUp = false; isMovingLeft = false; isMovingRight = false;
	}
	
	public void stopUp() {
		if(isMovingRight) sprite.changeAnimationTo("walkRight");
		else if(isMovingLeft) sprite.changeAnimationTo("walkLeft");
		else if(!isMovingDown) sprite.changeAnimationTo("standUp");
		isMovingUp = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) moveDown();
	}
	
	public void stopDown() {
		if(isMovingRight) sprite.changeAnimationTo("walkRight");
		else if(isMovingLeft) sprite.changeAnimationTo("walkLeft");
		else if(!isMovingUp) sprite.changeAnimationTo("standDown");
		isMovingDown = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) moveUp();
	}
	
	public void stopLeft() {
		if(isMovingUp) sprite.changeAnimationTo("walkUp");
		else if(isMovingDown) sprite.changeAnimationTo("walkDown");
		else if(!isMovingRight) sprite.changeAnimationTo("standLeft");
		isMovingLeft = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) moveRight();
	}
	
	public void stopRight() {
		if(isMovingUp) sprite.changeAnimationTo("walkUp");
		else if(isMovingDown) sprite.changeAnimationTo("walkDown");
		else if(!isMovingLeft) sprite.changeAnimationTo("standRight");
		isMovingRight = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) moveLeft();
	}
	
	/************************** Collision Detection **************************/
	public void getCollisionBlocks() {
		//get position in activeBlocks array
		int x = Game.level.renderRect.x2() - (sRect.x2() - margin.right) / TILE_SIZE - 2;//(aRect.x + margin.left) / TILE_SIZE;
		int y = Game.level.renderRect.y2() - (sRect.y2() - margin.bottom) / TILE_SIZE - 2;//(aRect.y) / TILE_SIZE;
		
		int j = 0;
		for(int i = 0; i < collisionDetectionBlocks.length; i++) {
			if(x+i/3 >= 0 && x+i/3 < Game.level.activeBlocks.length && y+j >= 0 && y+j < Game.level.activeBlocks[0].length) {
				collisionDetectionBlocks[i] = Game.level.activeBlocks[x+i/3][y+j]; //3x3 grid of blocks around the character
			}
			j++;
			if(j > 2) j = 0;
		}
	}
	
	public void getCollision(boolean isVerticalMovement, int move) {
		if(Game.debugMenu.collisionDetectionIsOn) {
			Rect charCRect = new Rect(aRect, this.margin);
			
			for(int i = 0; i < collisionDetectionBlocks.length; i++) {
				if(collisionDetectionBlocks[i] != null) {
					if(collisionDetectionBlocks[i].isCollidable()) {		
						int dir = 0;
						boolean didDetectCollision = false;
						if(isVerticalMovement) {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].collisionTile.cRect)) {
								if(isMovingDown) {
									dir = DOWN;
								} else if(isMovingUp) {
									dir = UP;
								}
								didDetectCollision = true;
							}
						} else {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].collisionTile.cRect)) {
								if(isMovingLeft) {
									dir = LEFT;
								} else if(isMovingRight) {
									dir = RIGHT;
								}
								didDetectCollision = true;
							}
						}
						CollisionTile t = collisionDetectionBlocks[i].collisionTile;
						t.heroDidCollide(dir, move, charCRect, collisionDetectionBlocks[i].collisionTile.cRect);
						collisionDetectionBlocks[i].sRect = new Rect(collisionDetectionBlocks[i].collisionTile.cRect, this);	//yellow block in debug mode
						if(didDetectCollision) break;
					}
				}
			}
		}
	}
	
	public void xMove(int moveX) {
		Game.level.offsetX += moveX;
		aRect.x += moveX;
		while(Game.level.offsetX >= TILE_SIZE) {
			Game.level.offsetX -= TILE_SIZE;
			Game.level.renderRect.x++;
		} 
		while(Game.level.offsetX < 0) {
			Game.level.offsetX += TILE_SIZE;
			Game.level.renderRect.x--;
		}
	}
	
	public void yMove(int moveY) {
		Game.level.offsetY += moveY;
		aRect.y += moveY;
		while(Game.level.offsetY >= TILE_SIZE) {
			Game.level.offsetY -= TILE_SIZE;
			Game.level.renderRect.y++;
		} 
		while(Game.level.offsetY < 0) {
			Game.level.offsetY += TILE_SIZE;
			Game.level.renderRect.y--;
		}
	}
	
	public void moveForward(int delta) {
		int moveX = 0, moveY = 0;
		
		getCollisionBlocks();
		if(isMovingLeft) {
			moveX = Math.round(-currentSpeed * delta);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		} else if(isMovingRight) {
			moveX = Math.round(currentSpeed * delta);
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		}
		
		if(isMovingUp) {
			moveY = Math.round(-currentSpeed * delta);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		} else if(isMovingDown) {
			moveY = Math.round(currentSpeed * delta);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		}
	}
	
	public void update(int delta) {
		if(isMoving() && !isAttacking) {
			moveForward(delta);
		} else if(isAttacking) {
			
		}
	}
	
	public static float getCurrentSpeed() {
		return currentSpeed;
	}
	
	public static void setCurrentSpeed(float speed) {
		currentSpeed = speed;
	}
	
	public void increaseSpeed() {
		if(currentSpeed > 0.09f) currentSpeed -= 0.1f;
	}
	
	public void decreaseSpeed() {
		if(currentSpeed < 0.9f) currentSpeed += 0.1f;
	}
}
