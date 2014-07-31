package com.madbros.tileminer.GameObjects;


import com.madbros.tileminer.Game;
import com.madbros.tileminer.MobController;

public class QueenBeeMinion extends Bee {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	Mob minionQueen;
	//Rect detectRect;
	
	public QueenBeeMinion(MobController mobController, int x, int y, Mob minionQueenTemp) {
		super(mobController, x, y);
		minionQueen = minionQueenTemp;
		moveSpeed = 0.06f;
	}
	
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		minionQueen.isChasing = true;
	}
	
	public void updateAI() {
			if(framesNum > 200) {
				super.updateAI();
				framesNum = 201;
			} else if(framesNum > 50) {
				chaseHero(Game.hero.absRect, this.absRect);
				
			} else {
				isChasing = false;
				moveInRandomDirection(50);
			}
			framesNum++;
			
		
	}
}
