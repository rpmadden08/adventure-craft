package com.madbros.adventurecraft;

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
	
	public void updateInventory(Inventory inventory) {
		updateTiles();
		inventory.heroSprite.updateCurrentAnimation();
	}
}
