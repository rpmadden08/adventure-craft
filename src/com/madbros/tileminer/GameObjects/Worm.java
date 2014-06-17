package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Worm extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Worm(MobController mobController, int x, int y) {
		super(mobController);
		attack = 20;
		hP = 15;
		maxHP = 15;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 100;
		chaseRange = 200;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.WORM));
		margin = new Margin(7, 6, 0, 0);
		moveSpeed = 0.03f;
		deathParticles = "wormDeath.p";
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
		Item item = ITEM_HASH.get(WORM_GUTS).createNew();
		Game.collectibleController.add(WORM_GUTS, Sprites.sprites.get(Sprites.WORM_GUTS), collectibleRect, 1, item.maxUses);
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
		checkForChasing();
		checkForFleeingCampfire();
		if(isInRangeOfCampfire) {
			fleeRect(campFireRect, this.absRect);
		} else if(isChasing) {
			checkForChasing();
			chaseHero(Game.hero.absRect, this.absRect);
		}else{
			moveInRandomDirection(300);
		}	
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
