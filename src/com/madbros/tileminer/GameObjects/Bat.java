package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Bat extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Bat(MobController mobController, int x, int y) {
		super(mobController);
		attack = 15;
		hP = 20;
		maxHP = 20;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		//detectRect = new Rect(absRect.x - 100, absRect.y - 100, absRect.w +200, absRect.h +200);
		detectRange = 25;
		chaseRange = 50;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.BAT));
		margin = new Margin(0, 0, 4, 6);
		currentSpeed = 0.1f;
		moveSpeed = 0.05f; //0.05 f
		
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
		Item item = ITEM_HASH.get(BAT_WING).createNew();
		Game.collectibleController.add(BAT_WING, Sprites.sprites.get(Sprites.BAT_WING), collectibleRect, 1,item.maxUses);
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
		checkForChasing();
		checkForFleeingCampfire();
		if(isInRangeOfCampfire) {
			fleeRect(campFireRect, this.absRect);
		} else if(isChasing){
			runningSpeed = 0.09f;
			checkSpeed();
			checkForChasing();
			chaseHero(Game.hero.absRect, this.absRect);
		} else {
			moveInRandomDirection(100);
		}
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
