package com.madbros.tileminer.Items;


import com.madbros.tileminer.Game;

public abstract class PotionItem extends StackableItem{

	
	@Override
	public abstract PotionItem createNew();
	
	@Override
	public void useRight() {
		if(Game.level.tileBeingAttacked.isUseable) {
			checkUsability();
		} else {
			if(Game.level.hasPlacedItemOnClick == false && canUsePotion()) {
				Game.level.hasPlacedItemOnClick = true;
				applyPotionEffect();
				Game.soundController.create("sounds/drinkingPotion.wav", 0.5f);
			}
		}
	}
	
	public boolean canUsePotion() {
		return true;
	}
	
	public void applyPotionEffect() {
		
	}
}
