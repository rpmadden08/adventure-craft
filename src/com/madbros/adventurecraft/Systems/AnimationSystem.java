package com.madbros.adventurecraft.Systems;

import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.GameObjects.Hero;
import com.madbros.adventurecraft.GameObjects.Mob;
import com.madbros.adventurecraft.Sprites.Sprites;

public class AnimationSystem {
	private void updateTiles() {
		//FIXME: Sprites class should have a list of all the animated tiles to snag for this purpose...
		for(int i = 0; i < Sprites.waterSprites.length; i++) {
			Sprites.waterSprites[i].updateCurrentAnimation();
		}
	}
	
	public void updateMain(Hero hero, MobController mobController) {
		updateTiles();
		if(hero.isMoving()) hero.sprite.updateCurrentAnimation();
		for(Mob mob : mobController.mobs) {
			if(mob.isMoving()) mob.sprite.updateCurrentAnimation();
		}
	}
	
	public void updateInventory(Hero hero, Inventory inventory) {
		updateTiles();
		hero.sprite.updateCurrentAnimation();
	}
}
