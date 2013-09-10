package com.madbros.adventurecraft.Systems;

import com.madbros.adventurecraft.Hero;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Sprites.Sprites;

public class AnimationSystem {
	private void updateTiles() {
		//FIXME: Sprites class should have a list of all the animated tiles to snag for this purpose...
		for(int i = 0; i < Sprites.waterSprites.length; i++) {
			Sprites.waterSprites[i].updateCurrentAnimation();
		}
	}
	
	public void updateMain(Hero hero) {
		updateTiles();
		if(hero.isMoving()) hero.sprite.updateCurrentAnimation();
	}
	
	public void updateInventory(Hero hero, Inventory inventory) {
		updateTiles();
		hero.sprite.updateCurrentAnimation();
	}
}
