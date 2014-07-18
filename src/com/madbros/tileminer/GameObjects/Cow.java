package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Cow extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Cow(MobController mobController, int x, int y) {
		super(mobController);
		attack = 1;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE)-20,
				(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE)-40,
				  128, 128);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.COW));
		margin = new Margin(36, 36, 40, 18);
		currentSpeed = 0.03f; 
		moveSpeed = 0.03f; //0.03
		sprite.changeFrameTimes(150);
		deathParticles = "cowDeath.p";
	}

	@Override
	public void deathDrop() {

		//Rect collectibleRect = new Rect(absRect.x+(absRect.w/2), absRect.y+(absRect.h/2), 16, 16);
//		Item item = ITEM_HASH.get(LEATHER).createNew();
//		Game.collectibleController.add(LEATHER, Sprites.sprites.get(Sprites.LEATHER), collectibleRect, 1, item.maxUses);
		Random rand = new Random();
		int num = rand.nextInt(3);
		for(int a = 0; a< num; a++) {
			Rect collectibleRect = new Rect(absRect.x+(absRect.w/2), absRect.y+(absRect.h/2), 16, 16);
			Item item = ITEM_HASH.get(STEAK).createNew();
			Game.collectibleController.add(STEAK, Sprites.sprites.get(Sprites.STEAK), collectibleRect, 1, item.maxUses);
		}
		num = rand.nextInt(3);
		for(int a = 0; a< num; a++) {
			Rect collectibleRect = new Rect(absRect.x+(absRect.w/2), absRect.y+(absRect.h/2), 16, 16);
			Item item = ITEM_HASH.get(LEATHER).createNew();
			Game.collectibleController.add(LEATHER, Sprites.sprites.get(Sprites.LEATHER), collectibleRect, 1, item.maxUses);
		}
	}
	
	@Override
	public void didCollide() {
//		if(!Game.hero.isDead) {
//			Game.hero.takeDamage(attack);
//			Game.hero.knockBack(this);
//		}
	}

	public void updateAI() {
//			super.updateAI();
//			moveInRandomDirection4(300);
		super.updateAI();
		checkForFleeingCampfire();
		if(isInRangeOfCampfire) {
			fleeRect(campFireRect, this.absRect);
		} else if(isChasing) {
			runningSpeed = 0.06f;
			checkSpeed();
			checkForChasing();
			chaseHero(Game.hero.absRect, this.absRect);
		}else{
			runningSpeed = 0f;
			checkSpeed();
			//moveInRandomDirection(30);
			//moveInRandomDirection360(100);
			isChasing = true;
			chaseHero(Game.hero.absRect, this.absRect);
		}	
			
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
