package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Items.Clothing;
import com.madbros.adventurecraft.Items.WeaponItem;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.StatusEffects.AppliedStatusEffect;
import com.madbros.adventurecraft.StatusEffects.StatusEffect;
import com.madbros.adventurecraft.StatusEffects.TimedStatusEffect;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Actor extends GameObject {
	public CompoundAnimatedSprite sprite;
	public Rect absRect;
	public Rect borderAbsRect;
	
	public Rect sRect = new Rect(Game.getCenterScreenX() - CHARACTER_SIZE/2, Game.getCenterScreenY() - CHARACTER_SIZE/2, CHARACTER_SIZE, CHARACTER_SIZE);
	public Margin margin = new Margin();
	
	public float currentSpeed;
	public float knockBackSpeed;
	public float moveSpeed;
	public float runningSpeed;
	public float slownessSpeed;
	public float speedSpeed;
	public float hungerSpeed = 0;
	public Block[] collisionDetectionBlocks;
	
	boolean isMovingLeft = false, isMovingRight = false, isMovingUp = false, isMovingDown = false;
	boolean isKnockingLeft = false, isKnockingRight = false, isKnockingUp = false, isKnockingDown = false;
	public boolean isAttacking = false;
	
	public TimedStatusEffect[] timedStatusEffects = new TimedStatusEffect[4]; 
	public AppliedStatusEffect[] appliedStatusEffects = new AppliedStatusEffect[3]; 
	
	public int hP = 10;
	public int maxHP = 10;
	public int mP = 10;
	public int maxMP = 10;
	public double eP = 10;
	public double maxEP = 10;
	public int armor = 0;
	
	//public boolean test = false;
	
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
	public String hitSound;
	public String deathSound;
	
	public Actor() {
		for(int i = 0; i < appliedStatusEffects.length; i++) {
			appliedStatusEffects[i] = new AppliedStatusEffect();
		}
		
		for(int i = 0; i < timedStatusEffects.length; i++) {
			timedStatusEffects[i] = new TimedStatusEffect();
		}
	}
	
	/************************** Movement **************************/
	public boolean isMoving() {
		return (isMovingDown || isMovingUp || isMovingLeft || isMovingRight);
	}
	
	public void checkSpeed() {
		float baseSpeed;
		if(knockBackTime <=0) {
			baseSpeed = moveSpeed + runningSpeed;
			currentSpeed = baseSpeed - (baseSpeed * slownessSpeed) + (baseSpeed * speedSpeed)-(baseSpeed*hungerSpeed);
		} else {
			currentSpeed = knockBackSpeed;
		}
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
	
	public boolean isKnockingBack() {
		if(!isKnockingLeft && !isKnockingRight && !isKnockingDown && !isKnockingUp) {
			return false;
		} else {
			return true;
		}
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
//		int y = Game.level.renderRect.y2() - (sRect.y2() - margin.bottom) / TILE_SIZE - 2+1;//(aRect.y) / TILE_SIZE;
		
		int j = 0;
		for(int i = 0; i < collisionDetectionBlocks.length; i++) {
			if(x+i/3 >= 0 && x+i/3 < Game.level.activeBlocks.length && y+j >= 0 && y+j < Game.level.activeBlocks[0].length) {
				collisionDetectionBlocks[i] = Game.level.activeBlocks[x+i/3][y+j]; //3x3 grid of blocks around the character
//				if(collisionDetectionBlocks[i].isCollidable()) {
//					collisionDetectionBlocks[i].collisionTile.setArrayPos(x+i/3, y+j);
//				}
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
	
	public boolean getCollision(boolean isVerticalMovement, int move, Rect r) {
		
		if(Game.debugMenu.collisionDetectionIsOn) {
			
//			Rect charCRect = new Rect(Game.level.renderRect.x * TILE_SIZE + sRect.x + margin.left, 
//									  Game.level.renderRect.y * TILE_SIZE + sRect.y + margin.top, 
//									  CHARACTER_SIZE - margin.left - margin.right,
//									  CHARACTER_SIZE - margin.top - margin.bottom);
			
			Rect charCRect = new Rect(r, this.margin);
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
						if(didDetectCollision) return true;
					}
				}
			}
		}
		return false;
	}
	
	public void xMove(int moveX) {
		absRect.x += moveX;

	}
	
	public void yMove(int moveY) {
		absRect.y += moveY;
	}
	
	private void moveHorizontal(float f, float speed) {
		int moveX = Math.round(speed * f);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
		xMove(moveX);
		getCollision(HORIZONTAL, moveX, absRect);
	}
	
	private void moveVertical(float f, float speed) {
		int moveY = Math.round(speed * f);
		yMove(moveY);
		getCollision(VERTICAL, moveY, absRect);
	}
	
	public void move(float f) {
		getCollisionBlocks();
		if(isMovingLeft) {
			moveHorizontal(f, -currentSpeed);
		} else if(isMovingRight) {
			moveHorizontal(f, currentSpeed);
		}
		
		if(isMovingUp) {
			moveVertical(f, -currentSpeed);
		} else if(isMovingDown) {
			moveVertical(f, currentSpeed);
		}
	}
	
	public void moveKnockBack(float f) {
		getCollisionBlocks();
		if(isKnockingLeft) {
			moveHorizontal(f, -currentSpeed);
		} else if(isKnockingRight) {
			moveHorizontal(f, currentSpeed);
		}
		
		if(isKnockingUp) {
			moveVertical(f, -currentSpeed);
		} else if(isKnockingDown) {
			moveVertical(f, currentSpeed);
		}
	}
	
	public void update() {
		if(appliedStatusEffects[1].id == 1) {
			//System.out.println(appliedStatusEffects[1].usesLeft);
		}
//		if(isMoving() && !isAttacking) {
//			move(Time.getDelta());
//		} else if(isAttacking) {
//			
//		}
	}
	
	public void eraseAppliedStatusEffect(int ID) {
		this.appliedStatusEffects[ID] = new AppliedStatusEffect();
	}
	
	public void eraseTimedStatusEffect(int ID) {
		this.timedStatusEffects[ID] = new TimedStatusEffect();
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
	
	public void addClothingItem(Clothing clothingItem) {
		sprite.addSprite(clothingItem.animatedSprite);
		sprite.sort();
		sprite.changeAnimationTo(STAND_DOWN);
		//sprite.changeAnimationTo(WALK_DOWN);
		//increase armor rating and add special effects
	}
	
	public void removeClothingItem(Clothing clothingItem) {
		sprite.removeSprite(clothingItem.animatedSprite);
		sprite.changeAnimationTo(STAND_DOWN);
	}
}
