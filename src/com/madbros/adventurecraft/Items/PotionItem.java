package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;

public abstract class PotionItem extends StackableItem{

	
	@Override
	public abstract PotionItem createNew();
	
	@Override
	public void useRight() {
		if(Game.level.hasPlacedItemOnClick == false && canUsePotion()) {
			Game.level.hasPlacedItemOnClick = true;
			applyPotionEffect();
		}
	}
	
	public boolean canUsePotion() {
		return true;
	}
	
	public void applyPotionEffect() {
		
	}
}
