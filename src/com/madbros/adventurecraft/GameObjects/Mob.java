package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.MobController;
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
	public void didCollide() {
		
		mobController.remove(this);
	}
	
	@Override
	public void didGetHit() {
		//This is when the weapon hits the bat
		mobController.remove(this);
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
