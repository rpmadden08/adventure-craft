package com.madbros.tileminer.Items;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;


public abstract class StackableItem extends ToolItem {
	public StackableItem() {
		maxStackSize = 99;
		swingSprite = Sprites.sprites.get(Sprites.SWORD);
		swingSprite.setColor(0, 0, 0, 0);
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		hitSound = "sounds/batmanPunch.wav";
		attackPower = 1;
//		itemPower = 1;
		isInUse = false;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
	}
	
	public void calculateUsage() {
		Game.hero.eP = Game.hero.eP - 0.1; //0.1
		//Game.hero.eP = Game.hero.eP - 1;
	}
	
	@Override

	
	public void swing() {
		if(isInUse == false) {
			swingRemaining = 0;
			isInUse = true;	
		} else {
			swingRemaining = swingRemaining -1;
			if (swingRemaining <= 0) {
				swingRemaining = swingSpeed;
				impact();

			}
		}
	}
	
	
	public void playHitSound() {
		Game.soundController.create(hitSound, 0.5f);
	}
	
	@Override
	public abstract StackableItem createNew();
}
