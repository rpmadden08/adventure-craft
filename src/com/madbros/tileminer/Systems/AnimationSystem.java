package com.madbros.tileminer.Systems;

import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.MobController;
import com.madbros.tileminer.GameObjects.Hero;
import com.madbros.tileminer.GameObjects.Mob;
import com.madbros.tileminer.Sprites.Sprites;

public class AnimationSystem {
	private void updateTiles() {
		//FIXME: Sprites class should have a list of all the animated tiles to snag for this purpose...
		for(int i = 0; i < Sprites.waterSprites.length; i++) {
			Sprites.waterSprites[i].updateCurrentAnimation();
		}
		for(int i = 0; i < Sprites.waterSwimmingAnimation.length; i++) {
			Sprites.waterSwimmingAnimation[i].updateCurrentAnimation();
		}
		for(int i = 0; i < Sprites.campfireAnimation.length; i++) {
			Sprites.campfireAnimation[i].updateCurrentAnimation();
		}
		for(int i = 0; i < Sprites.torchAnimation.length; i++) {
			Sprites.torchAnimation[i].updateCurrentAnimation();
		}
		
		for(int i = 0; i < Sprites.furnaceAnimation.length; i++) {
			Sprites.furnaceAnimation[i].updateCurrentAnimation();
		}
		for(int i = 0; i < Sprites.cauldronAnimation.length; i++) {
			Sprites.cauldronAnimation[i].updateCurrentAnimation();
		}
	}
	
	public void updateMain(Hero hero, MobController mobController) {
		updateTiles();
		if(hero.isSwimming) {
			hero.swimmingSprite.updateCurrentAnimation();
		} else {
			hero.sprite.updateCurrentAnimation();
		}
		for(Mob mob : mobController.mobs) {
			//if(mob.isMoving()) mob.sprite.updateCurrentAnimation();
			mob.sprite.updateCurrentAnimation();
			
		}
	}
	
	public void updateInventory(Hero hero, Inventory inventory, MobController mobController) {
		//updateTiles();
		hero.sprite.updateCurrentAnimation();
//		for(Mob mob : mobController.mobs) {
//			if(mob.isMoving()) mob.sprite.updateCurrentAnimation();
//		}
	}
}
