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

public class Bee extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Bee(MobController mobController, int x, int y) {
		super(mobController);
		attack = 25; //2
		hP = 6;
		maxHP = 6;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.BEE_MINI));
		margin = new Margin(0, 0, 0, 0);
		moveSpeed = 0.03f;
		currentSpeed = 0f;
		collisionDetectionBlocks = new Block[9];
		deathParticles = "beeDeath.p";
		isChasing = false;
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
		Item item = ITEM_HASH.get(HONEY).createNew();
		Game.collectibleController.add(HONEY, Sprites.sprites.get(Sprites.HONEY), collectibleRect, 1,item.maxUses);
	}
	
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		isChasing = true;
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
				checkForFleeingCampfire();
				if(isInRangeOfCampfire) {
					fleeRect(campFireRect, this.absRect);
				} else if(isChasing) {
					//moveSpeed = 0.09f;
					runningSpeed = 0.06f;
					checkSpeed();
					checkForChasing();
					chaseHero(Game.hero.absRect, this.absRect);
				}else{
					runningSpeed = 0f;
					checkSpeed();
					moveInRandomDirection(30);
				}	
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
