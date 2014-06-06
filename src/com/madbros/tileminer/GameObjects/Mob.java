package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Time;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Mob extends Actor {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	int attack;
	Rect detectRect;
	int detectRange = 50;
	int chaseRange = 100; //Detect and Chase make a distinction between how close you need to be in order to start chasing and how far away you need to be in order to stop
	boolean isChasing = true;
	float damping = 1;
	public boolean isInRangeOfCampfire = false;
	public Rect campFireRect = new Rect(0,0,0,0);
	public int mobState = 1;
	//public float maxSpeed;
	public boolean isFleeing = false;
	public String deathParticles = "death.p";
	
	
	
	public Mob(MobController mobController) {
		this.mobController = mobController;
		absRect = new Rect(TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2 - 64,
				  TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2 - 64,
				  CHARACTER_SIZE, CHARACTER_SIZE);
//		sRect = new Rect(absRect.x, absRect.y, absRect.w, absRect.h);
		
		detectRect = new Rect(absRect.x - detectRange, absRect.y - detectRange, absRect.w +(detectRange*2), absRect.h +(detectRange*2));
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.HUMAN_BASE));
		margin = new Margin(17, 17, 29, 1);
		collisionDetectionBlocks = new Block[25];
		
		hitSound = "sounds/bat.wav";
		deathSound = "sounds/splat.wav";
		
		moveSpeed = 0f;//0.05
		currentSpeed = 0f; //0.05
		knockBackSpeed = 0.3f; //0.3
		speedSpeed = 0f;
		slownessSpeed = 0f;
	}

	
	
	
	
	public void deathDrop() {
		
	}
	
	public void takeDamage(int damage) {
		//Get Harming Potion increase
		//System.out.println("DAMAGE: "+damage);
		damage = Game.hero.appliedStatusEffects[1].getHarmingDamageIncrease(damage);
		if(Game.hero.appliedStatusEffects[2].canApplyEffect(this)) {
			Game.hero.appliedStatusEffects[2].applySlownessEffect(this);
		}
		
//		Game.hero.eP = Game.hero.eP - 0.1;
		Item equippedWeapon = Game.inventory.invBar[Game.inventory.itemSelected].item;
		equippedWeapon.calculateUsage();
		if(knockBackTime <= 0) {
			hP = hP - damage;
			Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
			
			knockBackTime = 10; //30
			if(hP <= 0) {
				deathDrop();
				Game.soundController.create(deathSound, 0.2f);
				//Create a new particle effect...
				deathParticle();
				//Game.particleEffectController.add(deathParticles, absRect.x+ (absRect.w/2), absRect.y+ (absRect.h/2));

				mobController.remove(this);
			} else {
				//System.out.println(item.hitSound);
				item.playHitSound();
			}
		}
	}
	
	public void deathParticle() {
		Game.particleEffectController.add(deathParticles, absRect.x+ (absRect.w/2), absRect.y+ (absRect.h/2));

	}
	
	
	public void knockBack(Hero hero) {
		double p1x = (double) absRect.x + (absRect.w/2);
		double p1y = (double) absRect.y + (absRect.h/2);
		double p2x = (double) hero.absRect.x + (hero.absRect.w /2);
		double p2y = (double) hero.absRect.y + (hero.absRect.h /2);
		double xDiff = p2x - p1x;
		double yDiff = p2y - p1y;
		double degrees = Math.atan2(yDiff,  xDiff);
		degrees = degrees * 180 /(int) Math.PI;
		if(degrees < 0) {
			degrees += 360;
		}
		
		isKnockingUp = false;
		isKnockingDown = false;
		isKnockingLeft = false;
		isKnockingRight = false;
		
		if(degrees < 112.5 && degrees >= 67.5) {
			isKnockingUp = true;
		} else if(degrees < 22.5 || degrees >= 337.5) {
			isKnockingLeft = true;
		} else if(degrees < 202.5 && degrees >= 157.5) {
			isKnockingRight = true;
		} else if(degrees < 292.5 && degrees >= 247.5) {
			isKnockingDown = true;
		} else if(degrees < 67.5 && degrees >= 22.5) {
			isKnockingUp = true;
			isKnockingLeft = true;
		} else if(degrees < 157.5 && degrees >= 112.5) {
			isKnockingUp = true;
			isKnockingRight = true;
		} else if(degrees < 247.5 && degrees >= 202.5) {
			isKnockingDown = true;
			isKnockingRight = true;
		} else if(degrees < 337.5 && degrees >= 292.5) {
			isKnockingDown = true;
			isKnockingLeft = true;
		} 
		//0 = left, 90 = up etc....	
	}
	
	@Override
	public void didCollide() {
		//mobController.remove(this);
	}
	public void update() {
		for(int i =0; i < timedStatusEffects.length; i++) {
			timedStatusEffects[i].update(this);
		}
		//Check if the mob has stepped out of bounds
		
		if(knockBackTime > 0) {
			knockBackTime = knockBackTime - 1;
			checkSpeed();
			moveKnockBack(Time.getDelta());
			if(knockBackTime == 1) {
				isKnockingLeft = false;
				isKnockingDown = false;
				isKnockingRight = false;
				isKnockingUp = false;
			}
		} else if(isMoving() && !isAttacking) {
			//currentSpeed = moveSpeed;
			checkSpeed();
			move(Time.getDelta());
		} else if(isAttacking) {
			
		}
	}
	
	@Override
	public void checkCollisions() {
		if(this.absRect.x < Game.level.activeBlocks[0][0].absRect.x 
				|| this.absRect.y < Game.level.activeBlocks[0][0].absRect.y
				|| this.absRect.x > Game.level.activeBlocks[CHUNK_SIZE*CHUNKS_IN_A_ROW-1][CHUNK_SIZE*CHUNKS_IN_A_ROW-1].absRect.x+TILE_SIZE-1
				|| this.absRect.y > Game.level.activeBlocks[CHUNK_SIZE*CHUNKS_IN_A_ROW-1][CHUNK_SIZE*CHUNKS_IN_A_ROW-1].absRect.y+TILE_SIZE-1) {
					mobController.remove(this);
				} else {
		
			Rect charCRect = new Rect(Game.hero.absRect, Game.hero.margin);
			Rect cRect = new Rect(absRect, margin);
			
			if(cRect.detectCollision(charCRect) && Game.hero.knockBackTime <=0) {
				didCollide();
			}
			if(Game.hero.isAttacking ) {
				Rect wRect = new Rect(Game.hero.absRect.x+ Game.hero.attackItem.cRectFinal.x, Game.hero.absRect.y + Game.hero.attackItem.cRectFinal.y, Game.hero.attackItem.cRectFinal.w,Game.hero.attackItem.cRectFinal.h);
	
				if(cRect.detectCollision(wRect)&& knockBackTime <= 0) {
					didGetHit();
				}
			}
		}
	};

	
	@Override
	public void didGetHit() {
		if(knockBackTime <= 0 && !isKnockingBack()) {
			takeDamage(Game.hero.attackItem.attackPower);
			knockBack(Game.hero);
		}
	}
	
	public void chaseHero(Rect hero, Rect mob) {
		float speedX = (hero.x+(hero.w/2)) - (mob.x+(mob.w/2));
		float speedY = (hero.y+(hero.h/2)) - (mob.y +(mob.h/2));
		
		//float maxSpeed = moveSpeed;
		float maxSpeed = currentSpeed;
		//System.out.println(currentSpeed);
		if(speedX > maxSpeed && speedY > maxSpeed) {
			moveRight();
			moveDown();
		} else if(speedX > maxSpeed && speedY < -maxSpeed) {
			moveRight();
			moveUp();
		} else if(speedX < -maxSpeed && speedY < -maxSpeed) {
			moveLeft();
			moveUp();
		} else if(speedX < -maxSpeed && speedY > maxSpeed) {
			moveLeft();
			moveDown();
		} else if(speedX > maxSpeed) {
			moveRight();
			if(isMovingUp || isMovingDown) {
				stopUp();
				stopDown();
			}
		} else if(speedX < -maxSpeed) {
			moveLeft();
			if(isMovingUp || isMovingDown) {
				stopUp();
				stopDown();
			}
		} else if(speedY > maxSpeed) {
			moveDown();
			if(isMovingLeft || isMovingRight) {
				stopLeft();
				stopRight();
			}
		} else if(speedY < -maxSpeed) {
			moveUp();
			if(isMovingLeft || isMovingRight) {
				stopLeft();
				stopRight();
			}
		}
	}
	
	public void fleeRect(Rect rect, Rect mob) {
		float speedX = (rect.x+(rect.w/2)) - (mob.x+(mob.w/2));
		float speedY = (rect.y+(rect.h/2)) - (mob.y +(mob.h/2));
		
		float maxSpeed = moveSpeed;
		
		if(speedX > maxSpeed && speedY > maxSpeed) {
			moveLeft();
			moveUp();
		} else if(speedX > maxSpeed && speedY < -maxSpeed) {
			moveLeft();
			moveDown();
		} else if(speedX < -maxSpeed && speedY < -maxSpeed) {
			moveRight();
			moveDown();
		} else if(speedX < -maxSpeed && speedY > maxSpeed) {
			moveRight();
			moveUp();
		} else if(speedX > maxSpeed) {
			moveLeft();
			if(isMovingUp || isMovingDown) {
				stopUp();
				stopDown();
			}
		} else if(speedX < -maxSpeed) {
			moveRight();
			if(isMovingUp || isMovingDown) {
				stopUp();
				stopDown();
			}
		} else if(speedY > maxSpeed) {
			moveUp();
			if(isMovingLeft || isMovingRight) {
				stopLeft();
				stopRight();
			}
		} else if(speedY < -maxSpeed) {
			moveDown();
			if(isMovingLeft || isMovingRight) {
				stopLeft();
				stopRight();
			}
		}
	}
	
	public void checkForChasing() {
		if(isChasing == false) {
			detectRect = new Rect(absRect.x - detectRange, absRect.y - detectRange, absRect.w +(detectRange*2), absRect.h +(detectRange*2));
		} else {
			detectRect = new Rect(absRect.x - chaseRange, absRect.y - chaseRange, absRect.w +(chaseRange*2), absRect.h +(chaseRange*2));
		}
		if(detectRect.detectCollision(Game.hero.absRect) && !Game.hero.isDead) {
			isChasing = true;
			//stop();
			
		} else {
			isChasing = false;
		}
	}
	
	public void checkForFleeing() {
		detectRect = new Rect(absRect.x - detectRange, absRect.y - detectRange, absRect.w +(detectRange*2), absRect.h +(detectRange*2));
		if(detectRect.detectCollision(Game.hero.absRect) && !Game.hero.isDead) {
			isFleeing = true;
			//stop();
			
		} else {
			isFleeing = false;
		}
	}
	
	public void checkForFleeingCampfire() {
		Rect detectRect2 = new Rect(campFireRect.x - (500 /2), campFireRect.y - (500/2), campFireRect.w +(500), campFireRect.h +(500));
		if(detectRect2.detectCollision(absRect)) {
			isInRangeOfCampfire = true;
			//stop();
		} else {
			isInRangeOfCampfire = false;
		}
	}
	
	public void moveInRandomDirection4(int possibleLength) {
		if(framesNum > length) {
			framesNum = 0;
			Random rand2 = new Random();
			length = rand2.nextInt(possibleLength);
			
			Random rand = new Random();
			int number = rand.nextInt(5);
			stop();
			if(number == 0) {
				moveUp();
			} else if(number == 1) {
				moveLeft();
			} else if(number == 2) {
				moveRight();
			} else if(number == 3) {
				moveDown();
			} else if(number == 4) {}
		}
		framesNum++;
	}
	
	public void moveInRandomDirection(int possibleLength) {
		if(framesNum > length) {
			framesNum = 0;
			Random rand2 = new Random();
			length = rand2.nextInt(possibleLength);
			
			Random rand = new Random();
			int number = rand.nextInt(9);
			stop();
			if(number == 0) {
				moveUp();
			} else if(number == 1) {
				moveLeft();
			} else if(number == 2) {
				moveRight();
			} else if(number == 3) {
				moveDown();
			} else if(number == 4) {
				moveUp();
				moveLeft();
			} else if(number == 5) {
				moveUp();
				moveRight();
			} else if(number == 6) {
				moveDown();
				moveLeft();
			} else if(number == 7) {
				moveDown();
				moveRight();
			} else if(number == 8) {}
		}
		framesNum++;
	}
	
	public void updateAI() {				
		runningSpeed = 0;
		currentSpeed = 0;
		checkSpeed();
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
