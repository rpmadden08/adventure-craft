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
	
	public Cow(MobController mobController, int x, int y) {
		super(mobController);
		attack = 1;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE)-20,
				(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE)-40,
				  128, 128); //128,128
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.COW));
		margin = new Margin(36, 36, 40, 18);
		moveSpeed = 0.03f; //0.03
		sprite.changeFrameTimes(150);
		deathParticles = "cowDeath.p";
		hP = 20;
		maxHP = 20; //10
		isChasing = false;
		knockBackResistance = .80f;
	}

	@Override
	public void deathDrop() {
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

	public void updateAI() {
		super.updateAI();
		if(isKnockingBack) {
			stop();
		} else {
			runningSpeed = 0f;
			checkSpeed();
			moveInRandomDirection4(100);
		}
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
