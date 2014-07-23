package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import org.lwjgl.input.Keyboard;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Time;
import com.madbros.tileminer.GameStates.LoadingState;
import com.madbros.tileminer.Items.Clothing;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Items.WeaponItem;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Hero extends Actor {
	public boolean attackButtonReleased = true;
	public boolean isDead = false;
	public int deathWait = 0;
	public boolean isSwimming = false;

	public Hero() {
		super();
		//STATS
		maxHP = 25;
		hP = maxHP;
		maxMP = 0;
		mP = maxMP;
		maxEP = 25;
		eP = maxEP;
		
		
		absRect = new Rect(Game.level.spawnX, Game.level.spawnY,
				  CHARACTER_SIZE, CHARACTER_SIZE);
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.HUMAN_BASE));
		swimmingSprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.HUMAN_SWIMMING));
		
		margin = new Margin(17, 17, 33, 1);
		moveSpeed = 0.19f; //0.095
		currentSpeed = 0f; 
		knockBackSpeed = 0.3f;
		hitSound = "sounds/pain.wav";
		collisionDetectionBlocks = new Block[25];
		weaponX = 0;
		weaponY= 0;
		weaponR = 0;
		weaponXArray = new int[4][];
		
		weaponYArray = new int[4][];
		weaponRArray = new float[4][];
		//UP
		weaponXArray[0] = new int[]{34,30,34,50,53};
		weaponYArray[0] = new int[]{4,2,0,2,4};
		weaponRArray[0] = new float[]{-45,-90,-45,-20,0};
		//DOWN
		weaponXArray[1] = new int[]{34,30,36,50,53};
		weaponYArray[1] = new int[]{11,11,11,9,7};
		weaponRArray[1] = new float[]{135,180,135,110,90};
		//LEFT
		weaponXArray[2] = new int[]{22,24,18,13,15};
		weaponYArray[2] = new int[]{15,13,12,2,0};
		weaponRArray[2] = new float[]{225,180,225,250,270};
		//RIGHT
		weaponXArray[3] = new int[]{41,39,45,50,48};
		weaponYArray[3] = new int[]{17,15,14,4,2};
		weaponRArray[3] = new float[]{45,90,45,20,0};
		stopDown();
		
		
	}

//	public void startAttacking() {
//		timeSinceLastAnimation = 0;	//getTime()
//		isAttacking = true;
//	}
//	
//	public void stopAttacking() {
//		isAttacking = false;
//	}
	
	@Override
	public void checkCollisions() {};
	
	public void takeDamage(int damage) {
		damage = damage - armor;
		
		if(damage<1) {damage = 1;}
		if(knockBackTime <= 0 && isKnockingBack()) {
			if(hP - damage < 0) {
				hP = 0;
				
				armorUsage();
			} else {
				eP = eP - 0.1;
				hP = hP - damage;
				Game.soundController.create(hitSound, 1);
				armorUsage();
				knockBackTime = 10;
			}
			
		}
	}
	
	public void armorUsage() {
		for(int x = 0; x< Game.inventory.invClothing.length; x++) {
			if(Game.inventory.invClothing[x].item.id != 0) {
				Game.inventory.invClothing[x].item.uses = Game.inventory.invClothing[x].item.uses - 1;
				if(Game.inventory.invClothing[x].item.uses< 1) {
					Game.soundController.create("sounds/shatterItem.wav", 0.5f);
					Game.hero.removeClothingItem((Clothing) Game.inventory.invClothing[x].item);
					Game.inventory.invClothing[x].item = new NoItem();
				}
			}
		}
	}
	
	public void calcArmor() {
		int tempArmor = 0;
		for(int a = 0; a < Game.inventory.invClothing.length; a++) {
			if(Game.inventory.invClothing[a].item.id != 0) {
				Clothing tempClothingItem = (Clothing) Game.inventory.invClothing[a].item;
				tempArmor = tempArmor + tempClothingItem.defensePower;
			}
		}
		armor = tempArmor;
	}
	
	public void knockBack(Mob mob) {
//		double p1x = (double) absRect.x+ (absRect.w /2);
//		double p1y = (double) absRect.y+ (absRect.h/2);
//		double p2x = (double) mob.absRect.x +(mob.absRect.w /2);
//		double p2y = (double) mob.absRect.y +(mob.absRect.h /2);
//		double xDiff = p2x - p1x;
//		double yDiff = p2y - p1y;
//		double degrees = Math.atan2(yDiff,  xDiff);
//		degrees = degrees * 180 /(int) Math.PI;
//		if(degrees < 0) {
//			degrees += 360;
//		}
//		
//		isKnockingUp = false;
//		isKnockingDown = false;
//		isKnockingLeft = false;
//		isKnockingRight = false;
//		
//		if(degrees < 112.5 && degrees >= 67.5) {
//			isKnockingUp = true;
//		} else if(degrees < 22.5 || degrees >= 337.5) {
//			isKnockingLeft = true;
//		} else if(degrees < 202.5 && degrees >= 157.5) {
//			isKnockingRight = true;
//		} else if(degrees < 292.5 && degrees >= 247.5) {
//			isKnockingDown = true;
//		} else if(degrees < 67.5 && degrees >= 22.5) {
//			isKnockingUp = true;
//			isKnockingLeft = true;
//		} else if(degrees < 157.5 && degrees >= 112.5) {
//			isKnockingUp = true;
//			isKnockingRight = true;
//		} else if(degrees < 247.5 && degrees >= 202.5) {
//			isKnockingDown = true;
//			isKnockingRight = true;
//		} else if(degrees < 337.5 && degrees >= 292.5) {
//			isKnockingDown = true;
//			isKnockingLeft = true;
//		} 
		//0 = left, 90 = up etc....
		double p1x = absRect.x + (absRect.w/2);
		double p1y = absRect.y + (absRect.h/2);
		double p2x = mob.absRect.x + (mob.absRect.w /2);
		double p2y = mob.absRect.y + (mob.absRect.h /2);
		double xDiff = p2x - p1x;
		double yDiff = p2y - p1y;
		double degrees = Math.atan2(yDiff,  xDiff);
		degrees = degrees * 180 /(int) Math.PI;
		if(degrees < 0) {
			degrees += 360;
		}
		dir360 = (int)degrees;
		if(dir360 >= 180) {
			dir360 = dir360-180;
		} else {
			dir360 = dir360+180;
		}
		
		isKnockingUp = false;
		isKnockingDown = false;
		isKnockingLeft = false;
		isKnockingRight = false;
//		if(degrees < 112.5 && degrees >= 67.5) {
//			isKnockingUp = true;
//		} else if(degrees < 22.5 || degrees >= 337.5) {
//			isKnockingLeft = true;
//		} else if(degrees < 202.5 && degrees >= 157.5) {
//			isKnockingRight = true;
//		} else if(degrees < 292.5 && degrees >= 247.5) {
//			isKnockingDown = true;
//		} else if(degrees < 67.5 && degrees >= 22.5) {
//			isKnockingUp = true;
//			isKnockingLeft = true;
//		} else if(degrees < 157.5 && degrees >= 112.5) {
//			isKnockingUp = true;
//			isKnockingRight = true;
//		} else if(degrees < 247.5 && degrees >= 202.5) {
//			isKnockingDown = true;
//			isKnockingRight = true;
//		} else if(degrees < 337.5 && degrees >= 292.5) {
//			isKnockingDown = true;
//			isKnockingLeft = true;
//		} 
		if(dir360 > 180 && dir360 < 360) {
			isKnockingUp = true;
			isKnockingDown = false;
		}
		if(dir360 > 0 && dir360 < 180) {
			isKnockingDown = true;
			isKnockingUp = false;
		}
		if(dir360 > 90 && dir360 < 270) {
			isKnockingLeft = true;
			isKnockingRight = false;
		}
		if(dir360 > 270 || dir360 < 90) {
			isKnockingRight = true;
			isKnockingLeft = false;
		}
		//0 = left, 90 = up etc....	
		
		
	}
	public void didCollide() {}
	
	private int counter;
	public void checkEnergy() {
		//get hunger percentage
		double percentage = eP / maxEP;
		hungerSpeed = 0;
		if(percentage < 0.25) {
			hungerSpeed = 0.25f;
			checkSpeed();
			
//			if(Game.hero.timedStatusEffects[SLOWNESS].id != SLOWNESS) {
//				Game.hero.timedStatusEffects[SLOWNESS] = new Slowness();
//				
//			}
		} else {
//			if(Game.hero.timedStatusEffects[SLOWNESS].id == SLOWNESS) {
//				moveSpeed = moveSpeed + Slowness.speedAmount;
//				currentSpeed = moveSpeed ;
//				
//				eraseTimedStatusEffect(SLOWNESS);
//			}
		}
		if(percentage < 0.1) {
			counter++;
			if(counter >= 60) {
				//TODO  Add sound effect for when you get hit to indicate something is happening
				counter = 0;
				hP = hP - 1;
				Game.soundController.create(hitSound, 1);
				
			}
			
		}
	}
	
	public boolean checkSwimming() {
		Rect charCRect = new Rect(absRect, this.margin);
		for(int a = 0; a < collisionDetectionBlocks.length; a++) {
			if(collisionDetectionBlocks[a] != null) {
				if(collisionDetectionBlocks[a].isCollidable()) {
					
					if(charCRect.detectCollision(collisionDetectionBlocks[a].collisionTile.cRect)) {
						if(collisionDetectionBlocks[a].layers[WATER_LAYER].id == WATER) {
							if(!isSwimming) {
								Game.soundController.create("sounds/splash.wav", 0.5f);
							}
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	public void update() {
		super.update();
		//getCollisionBlocks();
		isSwimming = checkSwimming();
		if(isSwimming) {
			if(eP > 0) {
				eP = eP - 0.0010;
			}
			swimSpeed = moveSpeed /2;
			int currentAnimation = sprite.getCurrentAnimation();
//			int currentAnimationFrame =sprite.getCurrentAnimationFrame();
			swimmingSprite.changeAnimationTo(currentAnimation);
//			swimmingSprite.setCurrentFrame(currentAnimationFrame);

		} else {
			swimSpeed = 0;
		}

		checkEnergy();
		for(int i =0; i < timedStatusEffects.length; i++) {
			timedStatusEffects[i].update(this);
		}
		if(hP<=0) {
			Game.inventory.dropAll();
			isDead = true;
			if(deathWait > 60) {
				//Game.saveGame.saveGame();
				//Game.level.saveCurrentChunks(); 
				//Game.saveGame.saveCurrentLevel();
				Game.currentState = new LoadingState(Game.batch);
				Game.hero = new Hero();
				Game.level.respawn(Game.level.masterSpawnX/TILE_SIZE+1, Game.level.masterSpawnY/TILE_SIZE+1, Game.level.spawnLevel);
				//Game.currentState = new MainState();
			} else {
				deathWait ++;
			}
		} else if (isDead == true) {
			
		} else if(knockBackTime > 0) {
			knockBackTime = knockBackTime - 1;
			if(knockBackTime > 0) {
				checkSpeed();
				moveKnockBack(Time.getDelta());
			}
			if(knockBackTime == 1) {
				isKnockingLeft = false;
				isKnockingDown = false;
				isKnockingRight = false;
				isKnockingUp = false;
			}
		} else if(isMoving() && !isAttacking) {
			if(eP > 0) {
				eP = eP - 0.0005;
			}
			
			checkSpeed();
			move(Time.getDelta());
		} else if(isAttacking) {

			//ATTACK
			sprite.changeFrameTimes(50);
			startWeaponAnimation = true;
			int currentFrame = sprite.getCurrentAnimationFrame();
			if(currentFrame == 0) {
				if(Game.inventory.invBar[Game.inventory.itemSelected].item.id != attackItem.id) { //If the item is destroyed for some reason it should stop attacking... 
					attackItem = (WeaponItem) Game.inventory.invBar[Game.inventory.itemSelected].item;
				}
				if(attackButtonReleased == true || attackItem.isRepeatable == false) {
					if(hasAttacked == true) {
						startWeaponAnimation = false;
						stop();
						int t = sprite.getCurrentAnimation();
						if(t == SLASH_UP) {
							sprite.changeAnimationTo(STAND_UP);
							
						} else if(t == SLASH_DOWN) {
							sprite.changeAnimationTo(STAND_DOWN);
							
						} else if(t == SLASH_LEFT) {
							sprite.changeAnimationTo(STAND_LEFT);
							
						} else if(t == SLASH_RIGHT) {
							sprite.changeAnimationTo(STAND_RIGHT);
							
						}
						if(Keyboard.isKeyDown(Keyboard.KEY_W)) moveUp();
						if(Keyboard.isKeyDown(Keyboard.KEY_S)) moveDown();
						if(Keyboard.isKeyDown(Keyboard.KEY_A)) moveLeft();
						if(Keyboard.isKeyDown(Keyboard.KEY_D)) moveRight();
						sprite.changeFrameTimes(80);
						isAttacking = false;
						
					}
				}
				weaponX = weaponXArray[dir][currentFrame];
				weaponY = weaponYArray[dir][currentFrame];
				weaponR = weaponRArray[dir][currentFrame];
			} else if(currentFrame == 1) {
				weaponX = weaponXArray[dir][currentFrame];
				weaponY = weaponYArray[dir][currentFrame];
				weaponR = weaponRArray[dir][currentFrame];
			} else if(currentFrame == 2) {
				weaponX = weaponXArray[dir][currentFrame];
				weaponY = weaponYArray[dir][currentFrame];
				weaponR = weaponRArray[dir][currentFrame];
			} else if(currentFrame == 3) {
				weaponX = weaponXArray[dir][currentFrame];
				weaponY = weaponYArray[dir][currentFrame];
				weaponR = weaponRArray[dir][currentFrame];
			} else if(currentFrame == 4) {
				hasAttacked = true;
				weaponX = weaponXArray[dir][currentFrame];
				weaponY = weaponYArray[dir][currentFrame];
				weaponR = weaponRArray[dir][currentFrame];
			} 
			
			
			
			
			//attackTime = attackTime -1;
		}
		
	}
	
	public void attack(WeaponItem item) {
		if(attackButtonReleased == true && !isSwimming) {
			attackButtonReleased = false;
		
			hasAttacked = false;
			isAttacking = true;
			//attackItem = item;
			int iD = item.id;
			attackItem = (WeaponItem) ITEM_HASH.get(iD).createNew();
			//attackTime = 24;
			sprite.changeFrameTimes(attackItem.swingSpeed);
			
			int t = sprite.getCurrentAnimation();
			if(t == STAND_UP || t == WALK_UP) {
				sprite.changeAnimationTo(SLASH_UP);
				dir = 0;
				attackItem.cRectFinal = attackItem.cRectU;
			} else if(t == STAND_DOWN || t == WALK_DOWN) {
				sprite.changeAnimationTo(SLASH_DOWN);
				dir = 1;
				attackItem.cRectFinal = attackItem.cRectD;
			} else if(t == STAND_LEFT || t == WALK_LEFT) {
				sprite.changeAnimationTo(SLASH_LEFT);
				dir = 2;
				attackItem.cRectFinal = attackItem.cRectL;
			} else if(t == STAND_RIGHT || t == WALK_RIGHT) {
				sprite.changeAnimationTo(SLASH_RIGHT);
				dir = 3;
				attackItem.cRectFinal = attackItem.cRectR;
			}
		}
	}
	
//	public void xMove(int moveX) {
//		
//		super.xMove(moveX);
//		Game.level.offsetX += moveX;
//		while(Game.level.offsetX >= TILE_SIZE) {
//			Game.level.offsetX -= TILE_SIZE;
//			Game.level.renderRect.x++;
//		} 
//		while(Game.level.offsetX < 0) {
//			Game.level.offsetX += TILE_SIZE;
//			Game.level.renderRect.x--;
//		}
//	}
//	
//	public void yMove(int moveY) {
//		
//		super.yMove(moveY);
//		
//		Game.level.offsetY += moveY;
//		while(Game.level.offsetY >= TILE_SIZE) {
//			Game.level.offsetY -= TILE_SIZE;
//			Game.level.renderRect.y++;
//		} 
//		while(Game.level.offsetY < 0) {
//			Game.level.offsetY += TILE_SIZE;
//			Game.level.renderRect.y--;
//		}
//	}
}
