package com.madbros.adventurecraft.GameObjects;


import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MobController;

public class QueenBeeMinion extends Bee {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public QueenBeeMinion(MobController mobController, int x, int y) {
		super(mobController, x, y);
		moveSpeed = 0.06f;
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
