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
import com.madbros.tileminer.Utils.RectInt;

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
		knockBackSpeed = 0.19f; //0.3
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
		if(knockBackTime <=0) {
			hP = hP - damage;
			Item item = ITEM_HASH.get(Game.inventory.invBar[Game.inventory.itemSelected].item.id).createNew();
			
			//knockBackTime = item.knockBack; //30
			knockBackTime = 60;

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
		RectInt absRect2 = absRect.getRectInt();
		Game.particleEffectController.add(deathParticles, absRect2.x+ (absRect2.w/2), absRect2.y+ (absRect2.h/2));

	}
	
	
	public void knockBack(Hero hero) {
		double p1x = absRect.x + (absRect.w/2);
		double p1y = absRect.y + (absRect.h/2);
		double p2x = hero.absRect.x + (hero.absRect.w /2);
		double p2y = hero.absRect.y + (hero.absRect.h /2);
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
		//System.out.println(dir360);
		
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
			//System.out.println("up");
		}
		if(dir360 > 0 && dir360 < 180) {
			isKnockingDown = true;
			isKnockingUp = false;
			//System.out.println("down");
		}
		if(dir360 > 90 && dir360 < 270) {
			isKnockingLeft = true;
			isKnockingRight = false;
			//System.out.println("left");
		}
		if(dir360 > 270 || dir360 < 90) {
			isKnockingRight = true;
			isKnockingLeft = false;
			//System.out.println("right");
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
			//System.out.println("IS KNOCKING BACK");
//			coolDownTime = coolDownTime -1;
			checkSpeed();
			moveKnockBack(Time.getDelta());
			knockBackTime = knockBackTime - 1;
			
			
			if(knockBackTime < 1) {
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
		RectInt hero2 = hero.getRectInt();
		RectInt mob2 = mob.getRectInt();
		float speedX = (hero2.x+(hero2.w/2)) - (mob2.x+(mob2.w/2));
		float speedY = (hero2.y+(hero2.h/2)) - (mob2.y +(mob2.h/2));
		
		dir360 = (int) (Math.atan2(speedY, speedX) * 180 / Math.PI);
		
		if(dir360< 0) {
			dir360+=360;
		}
		
		//float maxSpeed = moveSpeed;
		//float maxSpeed = currentSpeed;
		//System.out.println(currentSpeed);
		stop();
		
		if(dir360 > 180 && dir360 < 360) {
			moveUp();
		}
		if(dir360 > 0 && dir360 < 180) {
			moveDown();
		}
		if(dir360 > 90 && dir360 < 270) {
			moveLeft();
		}
		if(dir360 > 270 || dir360 < 90) {
			moveRight();
		}
		
//		if(speedX > maxSpeed && speedY > maxSpeed) {
//			moveRight();
//			moveDown();
//		} else if(speedX > maxSpeed && speedY < -maxSpeed) {
//			moveRight();
//			moveUp();
//		} else if(speedX < -maxSpeed && speedY < -maxSpeed) {
//			moveLeft();
//			moveUp();
//		} else if(speedX < -maxSpeed && speedY > maxSpeed) {
//			moveLeft();
//			moveDown();
//		} else if(speedX > maxSpeed) {
//			moveRight();
//			if(isMovingUp || isMovingDown) {
//				stopUp();
//				stopDown();
//			}
//		} else if(speedX < -maxSpeed) {
//			moveLeft();
//			if(isMovingUp || isMovingDown) {
//				stopUp();
//				stopDown();
//			}
//		} else if(speedY > maxSpeed) {
//			moveDown();
//			if(isMovingLeft || isMovingRight) {
//				stopLeft();
//				stopRight();
//			}
//		} else if(speedY < -maxSpeed) {
//			moveUp();
//			if(isMovingLeft || isMovingRight) {
//				stopLeft();
//				stopRight();
//			}
//		}
	}
	
	public void fleeRect(Rect rect, Rect mob) {
		RectInt rect2 = rect.getRectInt();
		RectInt mob2 = mob.getRectInt();
		float speedX = (rect2.x+(rect2.w/2)) - (mob2.x+(mob2.w/2));
		float speedY = (rect2.y+(rect2.h/2)) - (mob2.y +(mob2.h/2));
		
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
	
	public void moveInRandomDirection360(int possibleLength) {
		if(framesNum > length) {
			framesNum = 0;
			Random rand2 = new Random();
			length = rand2.nextInt(possibleLength);
			
			Random rand = new Random();
			dir360 = rand.nextInt(360);
			//dir360 = 35;
			//System.out.println(dir360);
			stop();
			
			if(dir360 > 180 && dir360 < 360) {
				moveUp();
				System.out.println("up");
			}
			if(dir360 > 0 && dir360 < 180) {
				moveDown();
				System.out.println("down");
			}
			if(dir360 > 90 && dir360 < 270) {
				moveLeft();
				System.out.println("left");
			}
			if(dir360 > 270 || dir360 < 90) {
				moveRight();
				System.out.println("right");
			}
			
		}
		framesNum++;
	}
	
	public void move(float f) {
		getCollisionBlocks();
		
		double moveX = 0;
		double moveY = 0;


		double newX = ((int) absRect.x) + 100 * Math.cos(Math.toRadians(dir360));
		double newY = ((int) absRect.y) + 100 * Math.sin(Math.toRadians(dir360));

		double vX = newX - absRect.x;
		double vY = newY - absRect.y;

		double length = Math.sqrt((vX*vX)+(vY*vY));

		vX = vX/length;
		vY = vY/length;

		moveX = (vX*currentSpeed);
		moveY = (vY*currentSpeed);
		
		if(isMovingLeft) {
			moveHorizontal(f, (float)moveX);
		} else if(isMovingRight) {
			moveHorizontal(f, (float)moveX);
		}
		
		if(isMovingUp) {
			moveVertical(f, (float)moveY);
		} else if(isMovingDown) {
			moveVertical(f, (float)moveY);
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
	
	public void updateAI() {				
		runningSpeed = 0;
		currentSpeed = 0;
		checkSpeed();
	}
	
//	public void xMove(float moveX) {
//		//super.xMove(moveX);
//		absRect.x += moveX;
//	}
//	
//	public void yMove(float moveY) {
//		//super.yMove(moveY);
//		absRect.y += moveY;
//	}
}
