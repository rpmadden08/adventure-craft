package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Mob extends Actor {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	
	
	public Mob(MobController mobController) {
		this.mobController = mobController;
		absRect = new Rect(TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2 - 64,
				  TILES_PER_ROW*TILE_SIZE/2 - CHARACTER_SIZE/2 - 64,
				  CHARACTER_SIZE, CHARACTER_SIZE);
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.HUMAN_BASE));
		margin = new Margin(17, 17, 29, 1);
		currentSpeed = 0.05f;
		collisionDetectionBlocks = new Block[9];
		
		hitSound = "sounds/bat.wav";
		deathSound = "sounds/splat.wav";
		
		moveSpeed = 0.05f;//0.05
		currentSpeed = 0.05f; //0.05
		knockBackSpeed = 0.3f; //0.3
	}

//	public void startAttacking() {
//		timeSinceLastAnimation = 0;	//getTime()
//		isAttacking = true;
//	}
//	
//	public void stopAttacking() {
//		isAttacking = false;
//	}
	public void deathDrop() {
		
	}
	
	public void takeDamage(int damage) {
		if(knockBackTime <= 0) {
			hP = hP - damage;
			Game.soundController.create(hitSound);
			knockBackTime = 10; //30
			if(hP <= 0) {
				deathDrop();
				Game.soundController.create(deathSound);
				Game.p.x = absRect.x;
				Game.p.y = absRect.y;
				//Game.p.update(Gdx.graphics.getRawDeltaTime());
				Game.p.start();
				mobController.remove(this);
			}
		}
		//System.out.println("KnockBackTime:  "+ knockBackTime);
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
		//Check if the mob has stepped out of bounds
		
		if(knockBackTime > 0) {
			knockBackTime = knockBackTime - 1;
			currentSpeed = knockBackSpeed;
			moveKnockBack(Time.getDelta());
		} else if(isMoving() && !isAttacking) {
			currentSpeed = moveSpeed;
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
	};
	
	@Override
	public void didGetHit() {
		if(knockBackTime <= 0) {
			takeDamage(5);
			knockBack(Game.hero);
		}
	}
	
	public void updateAI() {
		if(framesNum > length) {
			framesNum = 0;
			Random rand2 = new Random();
			length = rand2.nextInt(100);
			
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
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
