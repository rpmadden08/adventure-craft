package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Time;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Items.Clothing.Clothing;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.StatusEffects.AppliedStatusEffect;
import com.madbros.tileminer.StatusEffects.TimedStatusEffect;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class Actor extends GameObject {
	public CompoundAnimatedSprite sprite;
	public CompoundAnimatedSprite swimmingSprite;
	public Rect absRect;
	public Rect borderAbsRect;
	
	public Rect sRect = new Rect(Game.getCenterScreenX() - CHARACTER_SIZE/2, Game.getCenterScreenY() - CHARACTER_SIZE/2, CHARACTER_SIZE, CHARACTER_SIZE);
	public Margin margin = new Margin();
	
	public boolean isKnockingBack = false;
	public float currentSpeed;
	public float knockBackSpeed;
	public float knockBackResistance=1.0f;
	//public float coolDownTime=0;
	public float moveSpeed;
	public float runningSpeed;
	public float slownessSpeed;
	public float speedSpeed;
	public float hungerSpeed = 0;
	public float swimSpeed = 0;
	public Block[] collisionDetectionBlocks;
	public long invincibleTime = 0;
	
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
	
	public long knockBackTime = 0;
	public boolean hasAttacked = true;
	public Item attackItem;
	
	public int[][] weaponXArray;
	public int[][] weaponYArray;
	public float[][] weaponRArray;
	public int weaponX = 0;
	public int weaponY = 0;
	public float weaponR = 0;
	public int dir = 0;
	public int dir360 = 0;
	public boolean startWeaponAnimation = false;
	public String hitSound;
	public String deathSound;
	public ArrayList<Mob> mobsHitByCurrentSwing = new ArrayList<Mob>();
	
	public Actor() {
		for(int i = 0; i < appliedStatusEffects.length; i++) {
			appliedStatusEffects[i] = new AppliedStatusEffect();
		}
		
		for(int i = 0; i < timedStatusEffects.length; i++) {
			timedStatusEffects[i] = new TimedStatusEffect();
		}
	}
	
	/************************** Movement **************************/
	
	public boolean checkKnockBack() {
		if(Time.getTime() -knockBackTime > 150 ) {
			return false;
		} else  {
			return true;
		}
	}
	
	
	public boolean hitByThisSwing(Mob mobBeingHit) {
		for(int i = 0; i < Game.hero.mobsHitByCurrentSwing.size(); i++) {
			Mob mob = Game.hero.mobsHitByCurrentSwing.get(i);
			if(mobBeingHit == mob) {
				return true;
			}
		}
		return false;
	}
	public boolean isMoving() {
		return (isMovingDown || isMovingUp || isMovingLeft || isMovingRight);
	}
	
	public void checkSpeed() {
		float baseSpeed;
		if(!isKnockingBack) {	
			baseSpeed = moveSpeed + runningSpeed - swimSpeed;
			currentSpeed = baseSpeed - (baseSpeed * slownessSpeed) + (baseSpeed * speedSpeed)-(baseSpeed*hungerSpeed);
		} else {
			Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
			currentSpeed = getKnockBackSpeed(item);
		}
	}
	
	public float getKnockBackSpeed(Item item) {
		return knockBackSpeed;
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
		RectInt absRect2 = absRect.getRectInt();
		RectInt lvBlAbsRect = Game.level.activeBlocks[0][0].absRect.getRectInt();
		int x = (absRect2.x - lvBlAbsRect.x-(TILE_SIZE*2)+(absRect2.w/2)) / TILE_SIZE;
		int y = (absRect2.y - lvBlAbsRect.y-(TILE_SIZE*2)+(absRect2.h/2)) / TILE_SIZE;
		int j = 0;
		for(int i = 0; i < collisionDetectionBlocks.length; i++) {
			if(x+i/5 >= 0 && x+i/5 < Game.level.activeBlocks.length && y+j >= 0 && y+j < Game.level.activeBlocks[0].length) {
				collisionDetectionBlocks[i] = Game.level.activeBlocks[x+i/5][y+j]; //3x3 grid of blocks around the character
			}
			j++;
			if(j > 4) j = 0;
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
	
	public boolean getCollision(boolean isVerticalMovement, double move) {
		boolean returnTrue = false;
		if(Game.debugMenu.collisionDetectionIsOn) {
			for(int i = 0; i < collisionDetectionBlocks.length; i++) {
				Rect charCRect = new Rect(absRect, this.margin);
				if(collisionDetectionBlocks[i] != null) {
					if(collisionDetectionBlocks[i].isCollidable()) {		
						int dir = 0;
						boolean didDetectCollision = false;
						if(isVerticalMovement) {
							
							if(charCRect.detectCollision(collisionDetectionBlocks[i].collisionTile.cRect)) {
								/*need this for Knockback Collision*/ 
								if(!isKnockingBack) {
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
								/*need this for Knockback Collision*/ 
								if(!isKnockingBack) {	
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
						if(didDetectCollision) returnTrue = true;
					}
				}
			}
			
		}
		if(returnTrue == true) {
			return true;
		}
		return false;
	}
	
//	public void xMove(int moveX) {
//		absRect.x += moveX;
//
//	}
//	
//	public void yMove(int moveY) {
//		absRect.y += moveY;
//	}
	public void xMove(double moveX) {
		//super.xMove(moveX);
		absRect.x += moveX;
	}
	
	public void yMove(double moveY) {
		//super.yMove(moveY);
		absRect.y += moveY;
	}
	
//	public void moveHorizontal(float f, float speed) {
//		int moveX = Math.round(speed * f);	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
//		xMove(moveX);
//		getCollision(HORIZONTAL, moveX);
//	}
//	
//	public void moveVertical(float f, float speed) {
//		int moveY = Math.round(speed * f);
//		yMove(moveY);
//		getCollision(VERTICAL, moveY);
//	}
	
	public void moveHorizontal(float f, double speed) {
		double moveX = speed * f;	// if there is severe lag, the delta value may cause the character to jump significantly ahead...
		xMove(moveX);
		getCollision(HORIZONTAL, moveX);
	}
	
	public void moveVertical(float f, double speed) {
		double moveY = speed * f;
		yMove(moveY);
		getCollision(VERTICAL, moveY);
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
		
		double moveX = 0;
		double moveY = 0;


		double newX = (absRect.x) + 100 * Math.cos(Math.toRadians(dir360));
		double newY = (absRect.y) + 100 * Math.sin(Math.toRadians(dir360));

		double vX = newX - absRect.x;
		double vY = newY - absRect.y;

		double length = Math.sqrt((vX*vX)+(vY*vY));

		vX = vX/length;
		vY = vY/length;

		moveX = (vX*currentSpeed);
		moveY = (vY*currentSpeed);



//		moveX = getCollisionX();
//		moveY = getCollisionY();
		if(isKnockingLeft) {
			moveHorizontal(f, (float)moveX);
		} else if(isKnockingRight) {
			moveHorizontal(f, (float)moveX);
		}
		
		if(isKnockingUp) {
			moveVertical(f, (float)moveY);
		} else if(isKnockingDown) {
			moveVertical(f, (float)moveY);
		}
	}
	
	public void update() {
		if(appliedStatusEffects[1].id == 1) {
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
		if(!Game.hero.sprite.hasClothingItemAlready(clothingItem.animatedSprite)) {
			sprite.addSprite(clothingItem.animatedSprite);
			sprite.sort();
			sprite.changeAnimationTo(STAND_DOWN);
			if(clothingItem.slotType == HELMET_SLOT) {
				swimmingSprite.addSprite(clothingItem.animatedSprite);
				swimmingSprite.sort();
			}
		}
	}
	
	
	public void removeClothingItem(Clothing clothingItem) {
		sprite.removeSprite(clothingItem.animatedSprite);
		sprite.changeAnimationTo(STAND_DOWN);
		if(clothingItem.slotType == HELMET_SLOT) {
			swimmingSprite.removeSprite(clothingItem.animatedSprite);
			
		}
//		sprite.resetFrames();
//		swimmingSprite.resetFrames();
//		sprite.changeFrameTimes(80);
//		swimmingSprite.changeFrameTimes(80);
	}
	
	
}
