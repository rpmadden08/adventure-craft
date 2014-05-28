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

public class Slime extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Slime(MobController mobController, int x, int y) {
		super(mobController);
		attack = 5;
		hP = 10;
		maxHP = 10;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.SLIME));
		margin = new Margin(0, 0, 0, 0);
		currentSpeed = 0.03f;
		moveSpeed = 0.03f;
		deathParticles = "slimeDeath.p";
		sprite.changeFrameTimes(200);
	}
	
	@Override
	public void deathDrop() {
		Rect collectibleRect = new Rect(absRect.x, absRect.y, 16, 16);
		Item item = ITEM_HASH.get(SLIME_BALL).createNew();
		Game.collectibleController.add(SLIME_BALL, Sprites.sprites.get(Sprites.SLIME_BALL), collectibleRect, 1, item.maxUses);
	}
	
	@Override
	public void didCollide() {
		//mobController.remove(this);
		if(!Game.hero.isDead) {
			Game.hero.takeDamage(attack);
			Game.hero.knockBack(this);
		}
	}
	
	public void updateAI() {
		
		super.updateAI();	
		checkForChasing();
		checkForFleeingCampfire();
		if(isInRangeOfCampfire) {
			fleeRect(campFireRect, this.absRect);
		} else if(isChasing) {
			moveSpeed = 0.06f;
			currentSpeed = 0.06f;
			checkForChasing();
			chaseHero(Game.hero.absRect, this.absRect);
		}else{
			moveSpeed = 0.03f;
			currentSpeed = 0.03f;
			moveInRandomDirection(100);
		}
		//System.out.println(moveSpeed);
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
