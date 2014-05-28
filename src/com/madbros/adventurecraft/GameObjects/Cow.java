package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Cow extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Cow(MobController mobController, int x, int y) {
		super(mobController);
		attack = 1;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  128, 128);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.COW));
		margin = new Margin(20, 36, 40, 18);
		currentSpeed = 0.03f; //0.03
		moveSpeed = 0.03f;
		sprite.changeFrameTimes(150);
		deathParticles = "cowDeath.p";
	}

	@Override
	public void deathDrop() {

		Rect collectibleRect = new Rect(absRect.x+(absRect.w/2), absRect.y+(absRect.h/2), 16, 16);
		Item item = ITEM_HASH.get(LEATHER).createNew();
		Game.collectibleController.add(LEATHER, Sprites.sprites.get(Sprites.LEATHER), collectibleRect, 1, item.maxUses);
		Random rand = new Random();
		int num = rand.nextInt(3)+1;
		for(int a = 0; a< num; a++) {
			collectibleRect = new Rect(absRect.x+(absRect.w/2), absRect.y+(absRect.h/2), 16, 16);
			item = ITEM_HASH.get(STEAK).createNew();
			Game.collectibleController.add(STEAK, Sprites.sprites.get(Sprites.STEAK), collectibleRect, 1, item.maxUses);
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
			super.updateAI();
			moveInRandomDirection(300);
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
