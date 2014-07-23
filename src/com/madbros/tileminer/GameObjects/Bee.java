package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Bee extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Bee(MobController mobController, int x, int y) {
		super(mobController);
		attack = 2; //2
		hP = 6;
		maxHP = 6; //6
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 50; //50
		chaseRange = 200; //50
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.BEE_MINI));
		margin = new Margin(11, 11, 10, 12);
		moveSpeed = 0.03f; //0.03
		currentSpeed = 0f;
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
				if(knockBackTime > 0) {
					stop();
				} else if(isInRangeOfCampfire) {
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
					moveInRandomDirection360(100);
//					isChasing = true;
//					chaseHero(Game.hero.absRect, this.absRect);
				}	
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
