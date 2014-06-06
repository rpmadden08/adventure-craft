package com.madbros.tileminer.GameObjects;


import com.madbros.tileminer.MobController;


public class BeeEvening extends Bee {
	//Rect detectRect;
	
	public BeeEvening(MobController mobController, int x, int y) {
		super(mobController, x, y);
		detectRange = 25;
	}
	
	public void updateAI() {
		checkForChasing();
			super.updateAI();
		
	}
}
