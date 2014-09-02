package com.madbros.tileminer.GameObjects;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.CompoundAnimatedSprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class Slime extends Mob {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public Slime(MobController mobController, int x, int y) {
		super(mobController);
		attack = 5;
		hP = 20;
		maxHP = 20;
		this.mobController = mobController;
		absRect = new Rect((x*TILE_SIZE) + (Game.level.chunkRect.x * CHUNK_SIZE*TILE_SIZE),(y*TILE_SIZE)+(Game.level.chunkRect.y *CHUNK_SIZE*TILE_SIZE),
				  32, 32);
		detectRange = 200;
		chaseRange = 200;
		sprite = new CompoundAnimatedSprite(Sprites.animatedSprites.get(Sprites.SLIME));
		margin = new Margin(1, 1, 1, 0);
		moveSpeed = 0.03f;
		deathParticles = "slimeDeath.p";
		sprite.changeFrameTimes(200);
		isChasing = false;
		knockBackResistance = 0.35f;
		
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
		if(Game.hero.isHittable()) {
			Game.hero.takeDamage(attack);
			Game.hero.knockBack(this);
		}
	}
	
	public void updateAI() {
		super.updateAI();	
		checkForChasing();
		checkForFleeingCampfire();
		if(isKnockingBack) {
			stop();
		} else if(isInRangeOfCampfire) {
			fleeRect(campFireRect, this.absRect);
		} else if(isChasing) {
			runningSpeed = 0.06f;
			checkSpeed();
			//checkForChasing();
			chaseHero(Game.hero.absRect, this.absRect);
		}else{
			runningSpeed = 0f;
			checkSpeed();
			moveInRandomDirection360(100);
		}
		
	}
	
	public void xMove(int moveX) {
		super.xMove(moveX);
	}
	
	public void yMove(int moveY) {
		super.yMove(moveY);
	}
}
