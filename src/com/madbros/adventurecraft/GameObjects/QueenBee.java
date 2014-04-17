package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class QueenBee extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public QueenBee(MobController mobController, int x, int y) {
		super(mobController);
		attack = 20;
		hP = 500;
		maxHP = 500;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.BEE));
		margin = new Margin(0, 0, 0, 0);
		currentSpeed = 0.2f;
		moveSpeed = 0.15f;
		maxSpeed = 0.15f;
		collisionDetectionBlocks = new Block[9];
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
	public void deathDrop() {
		Rect collectibleRect = new Rect(absRect.x, absRect.y, 16, 16);
		Game.collectibleController.add(STINGER_SWORD, Sprites.sprites.get(Sprites.STINGER_SWORD), collectibleRect, 1);
	}
	
	@Override
	public void didCollide() {
		//mobController.remove(this);
		if(!Game.hero.isDead) {
			Game.hero.takeDamage(attack);
			Game.hero.knockBack(this);
		}
	}
//	@Override
//	public void didGetHit() {
//		
//		//This is when the weapon hits the bat
//		//mobController.remove(this);
//	}
	
	public void updateAI() {
		super.updateAI();
		checkForFleeing();
		if(isFleeing && mobState != 3) {
			moveSpeed = maxSpeed;
			fleeRect(Game.hero.absRect, this.absRect);
		} else if(mobState == 1) {
			summonBees();
			length = 100;
			framesNum = 0;
			mobState = 2;
			
		} else if(mobState == 2) {
			moveSpeed = 0;
			if(framesNum > length) {
				framesNum = 0;
				Random rand2 = new Random();
				length = rand2.nextInt(100+100);
				mobState = 3;
			}
			framesNum++;
		} else if(mobState == 3) {
			moveSpeed = maxSpeed;
			chaseHero(Game.hero.absRect, this.absRect);
			if(framesNum > length) {
				framesNum = 0;
				Random rand2 = new Random();
				//length = rand2.nextInt(100);
				mobState = 1;
			}
			framesNum++;
		}
		
	}
	
	public void summonBees() {
		stop();
		
		moveDown();
		//currentSpeed = 0f;
		moveSpeed = 0f;
		Random rand2 = new Random();
		int randomAmount = rand2.nextInt(3+1);
		for(int a = 0; a < 20; a++) {
			
			mobController.mobs.add(new QueenBeeMinion(Game.mobController, (absRect.x - Game.level.activeBlocks[0][0].absRect.x)/TILE_SIZE, (absRect.y- Game.level.activeBlocks[0][0].absRect.y)/TILE_SIZE));
			mobController.mobs.get(mobController.mobs.size()-1).isChasing = true;
		}
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
