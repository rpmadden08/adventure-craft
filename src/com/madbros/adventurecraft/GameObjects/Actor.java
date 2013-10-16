package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;


import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.backends.openal.Wav.Sound;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Items.ClothingItem;
import com.madbros.adventurecraft.Items.WeaponItem;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Actor extends GameObject {
	public CompoundAnimatedSprite sprite;
	public Rect absRect;
	public Rect sRect = new Rect(Game.getCenterScreenX() - CHARACTER_SIZE/2, Game.getCenterScreenY() - CHARACTER_SIZE/2, CHARACTER_SIZE, CHARACTER_SIZE);
	public Margin margin = new Margin();
	
	public float currentSpeed;
	public float knockBackSpeed;
	public float moveSpeed;
	public Block[] collisionDetectionBlocks;
	
	boolean isMovingLeft = false, isMovingRight = false, isMovingUp = false, isMovingDown = false;
	boolean isKnockingLeft = false, isKnockingRight = false, isKnockingUp = false, isKnockingDown = false;
	public boolean isAttacking = false;
	
	public int hP = 10;
	public int maxHP = 10;
	public int mP = 10;
	public int maxMP = 10;
	public int eP = 10;
	public int maxEP = 10;
	
	public int knockBackTime = 0;
	public boolean hasAttacked = true;
	public WeaponItem attackItem;
	
	public int[][] weaponXArray;
	public int[][] weaponYArray;
	public float[][] weaponRArray;
	public int weaponX = 0;
	public int weaponY = 0;
	public float weaponR = 0;
	public int dir = 0;
	public boolean startWeaponAnimation = false;
	public Sound hitSound;
	
	/************************** Movement **************************/
	public boolean isMoving() {
		return (isMovingDown || isMovingUp || isMovingLeft || isMovingRight);
	}
	
	public void moveUp() {
		if(!isMoving() || isMovingDown) sprite.changeAnimationTo(WALK_UP);
		isMovingUp = true;
		isMovingDown = false;
	}
	
	public void moveDown() {
		if(!isMoving() || isMovingUp) sprite.changeAnimationTo(WALK_DOWN);
		isMovingUp = false;
		isMovingDown = true;
	}
	
	public void moveLeft() {
		if(!isMoving() || isMovingRight) sprite.changeAnimationTo(WALK_LEFT);
		isMovingLeft = true;
		isMovingRight = false;
	}
	
	public void moveRight() {
		if(!isMoving() || isMovingLeft) sprite.changeAnimationTo(WALK_RIGHT);
		isMovingLeft = false;
		isMovingRight = true;
	}
	
	public void stop() {
		if(isMovingDown) sprite.changeAnimationTo(STAND_DOWN);
		if(isMovingUp) sprite.changeAnimationTo(STAND_UP);
		if(isMovingLeft) sprite.changeAnimationTo(STAND_LEFT);
		if(isMovingRight) sprite.changeAnimationTo(STAND_RIGHT);
		isMovingDown = false; isMovingUp = false; isMovingLeft = false; isMovingRight = false;
	}
	
	public void stopUp() {
		if(isMovingRight) sprite.changeAnimationTo(WALK_RIGHT);
		else if(isMovingLeft) sprite.changeAnimationTo(WALK_LEFT);
		else if(!isMovingDown) sprite.changeAnimationTo(STAND_UP);
		isMovingUp = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) moveDown();
	}
	
	public void stopDown() {
		if(isMovingRight) sprite.changeAnimationTo(WALK_RIGHT);
		else if(isMovingLeft) sprite.changeAnimationTo(WALK_LEFT);
		else if(!isMovingUp) sprite.changeAnimationTo(STAND_DOWN);
		isMovingDown = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) moveUp();
	}
	
	public void stopLeft() {
		if(isMovingUp) sprite.changeAnimationTo(WALK_UP);
		else if(isMovingDown) sprite.changeAnimationTo(WALK_DOWN);
		else if(!isMovingRight) sprite.changeAnimationTo(STAND_LEFT);
		isMovingLeft = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) moveRight();
	}
	
	public void stopRight() {
		if(isMovingUp) sprite.changeAnimationTo(WALK_UP);
		else if(isMovingDown) sprite.changeAnimationTo(WALK_DOWN);
		else if(!isMovingLeft) sprite.changeAnimationTo(STAND_RIGHT);
		isMovingRight = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) moveLeft();
	}
	
	/************************** Collision Detection **************************/
	public void getCollisionBlocks() {
		//get position in activeBlocks array
		int x = (absRect.x - Game.level.activeBlocks[0][0].absRect.x) / TILE_SIZE;
		int y = (absRect.y - Game.level.activeBlocks[0][0].absRect.y) / TILE_SIZE;
//		int x = Game.level.renderRect.x2() - (sRect.x2() - margin.right) / TILE_SIZE - 2;//(aRect.x + margin.left) / TILE_SIZE;
//		int y = Game.level.renderRect.y2() - (sRect.y2() - margin.bottom) / TILE_SIZE - 2;//(aRect.y) / TILE_SIZE;
		
		int j = 0;
		for(int i = 0; i < collisionDetectionBlocks.length; i++) {
			if(x+i/3 >= 0 && x+i/3 < Game.level.activeBlocks.length && y+j >= 0 && y+j < Game.level.activeBlocks[0].length) {
				collisionDetectionBlocks[i] = Game.level.activeBlocks[x+i/3][y+j]; //3x3 grid of blocks around the character
			}
			j++;
			if(j > 2) j = 0;
		}
	}
	
	public void didCollide() {};
	public void didGetHit() {};
	
	public void checkCollisions() {
		Rect charCRect = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect cRect = new Rect(absRect, margin);
		
		if(cRect.detectCollision(charCRect)) {
			didCollide();
		}
		if(Game.hero.isAttacking) {
			Rect wRect = new Rect(Game.hero.absRect.x+ Game.hero.attackItem.cRectFinal.x, Game.hero.absRect.y + Game.hero.attackItem.cRectFinal.y, Game.hero.attackItem.cRectFinal.w,Game.hero.attackItem.cRectFinal.h);

			if(cRect.detectCollision(wRect)) {
				didGetHit();
			}
		}
	}
	
	public void getCollision(boolean isVerticalMovement, int move) {
		if(Game.debugMenu.collisionDetectionIsOn) {
			Rect charCRect = new Rect(absRect, this.margin);
			
			for(int i = 0; i < collisionDetectionBlocks.length; i++) {
				if(collisionDetectionBlocks[i] != null) {
					if(collisionDetectionBlocks[i].isCollidable()) {		
						int dir = 0;
						boolean didDetectCollision = false;
						if(isVerticalMovement) {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].collisionTile.cRect)) {
								if(knockBackTime <= 0) { /*need this for Knockback Collision*/ 
									if(isMovingDown) {
										dir = DOWN;
									} else if(isMovingUp) {
										dir = UP;
									}
									didDetectCollision = true;
								} else {
									if(isKnockingDown) {
										dir = DOWN;
									} else if(isKnockingUp) {
										dir = UP;
									}
									didDetectCollision = true;
								}
							}
						} else {
							if(charCRect.detectCollision(collisionDetectionBlocks[i].collisionTile.cRect)) {
								if(knockBackTime <= 0) { /*need this for Knockback Collision*/ 
									if(isMovingLeft) {
										dir = LEFT;
									} else if(isMovingRight) {
										dir = RIGHT;
									}
									didDetectCollision = true;
								} else {
									if(isKnockingLeft) {
										dir = LEFT;
									} else if(isKnockingRight) {
										dir = RIGHT;
									}
									didDetectCollision = true;
								}
							}
						}
						CollisionTile t = collisionDetectionBlocks[i].collisionTile;
						t.heroDidCollide(this, dir, move, charCRect, collisionDetectionBlocks[i].collisionTile.cRect);
						collisionDetectionBlocks[i].sRect = new Rect(collisionDetectionBlocks[i].collisionTile.cRect, this);	//yellow block in debug mode
						if(didDetectCollision) break;
					}
				}
			}
		}
	}
	
	public void xMove(int moveX) {
		absRect.x += moveX;
	}
	
	public void yMove(int moveY) {
		absRect.y += moveY;
	}
	
	public void move(float f) {
		int moveX = 0, moveY = 0;
		
		getCollisionBlocks();
		if(isMovingLeft) {
			moveX = Math.round(-currentSpeed * f);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		} else if(isMovingRight) {
			moveX = Math.round(currentSpeed * f);
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		}
		
		if(isMovingUp) {
			moveY = Math.round(-currentSpeed * f);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		} else if(isMovingDown) {
			moveY = Math.round(currentSpeed * f);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		}
	}
	
	public void moveKnockBack(float f) {
		int moveX = 0, moveY = 0;
		
		getCollisionBlocks();
		if(isKnockingLeft) {
			moveX = Math.round(-currentSpeed * f);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		} else if(isKnockingRight) {
			moveX = Math.round(currentSpeed * f);
			xMove(moveX);
			getCollision(HORIZONTAL, moveX);
		}
		
		if(isKnockingUp) {
			moveY = Math.round(-currentSpeed * f);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		} else if(isKnockingDown) {
			moveY = Math.round(currentSpeed * f);
			yMove(moveY);
			getCollision(VERTICAL, moveY);
		}
	}
	
	public void update() {
		if(isMoving() && !isAttacking) {
			move(Time.getDelta());
		} else if(isAttacking) {
			
		}
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}
	
	public void setCurrentSpeed(float speed) {
		currentSpeed = speed;
	}
	
	public void increaseSpeed() {
		if(currentSpeed > 0.09f) currentSpeed -= 0.1f;
	}
	
	public void decreaseSpeed() {
		if(currentSpeed < 0.9f) currentSpeed += 0.1f;
	}
	
	public void addClothingItem(ClothingItem clothingItem) {
		sprite.addSprite(clothingItem.animatedSprite);
		sprite.changeAnimationTo(WALK_DOWN);
		//increase armor rating and add special effects
	}
	
	public void removeClothingItem(ClothingItem clothingItem) {
		sprite.removeSprite(clothingItem.animatedSprite);
		sprite.changeAnimationTo(WALK_DOWN);
	}
}
