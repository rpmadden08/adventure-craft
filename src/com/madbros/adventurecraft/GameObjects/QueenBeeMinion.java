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

public class QueenBeeMinion extends Bee {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public QueenBeeMinion(MobController mobController, int x, int y) {
		super(mobController, x, y);
		attack = 2;
		hP = 6;
		maxHP = 6;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 100;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.BEE_MINI));
		margin = new Margin(0, 0, 0, 0);
		moveSpeed = 0.06f;
		currentSpeed = 0.06f;
		collisionDetectionBlocks = new Block[9];
		isChasing = true;
		deathParticles = "beeDeath.p";
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
		Game.collectibleController.add(HONEY, Sprites.sprites.get(Sprites.HONEY), collectibleRect, 1, item.maxUses);
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
//	@Override
//	public void didGetHit() {
//		
//		//This is when the weapon hits the bat
//		//mobController.remove(this);
//	}
	
	public void updateAI() {

			super.updateAI();
			if(framesNum > 50) {
				chaseHero(Game.hero.absRect, this.absRect);
				framesNum = 51;
			} else {
				isChasing = false;
				moveInRandomDirection(50);
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
