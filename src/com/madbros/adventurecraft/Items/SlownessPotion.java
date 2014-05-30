package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.StatusEffects.SlownessApplied;
import com.madbros.adventurecraft.Utils.Helpers;

public class SlownessPotion extends PotionItem {
	public SlownessPotion() {
		id = SLOWNESS_POTION;
		name = "Slowness Potion";
		sprite = Sprites.sprites.get(Sprites.SLOWNESS_POTION);
		craftCost = new int[]{SLIME_BALL, ARTICHOKE, GLASS_BOTTLE};
		
	}
	@Override
	public PotionItem createNew() {
		return new SlownessPotion();
	}
	
	public boolean canUsePotion() {
		if(Game.hero.appliedStatusEffects[SLOWNESS_APPLIED].usesLeft <5) { 
			return true;
			} else {
			return false;
			}
	}
	
	public void applyPotionEffect() {
		Game.hero.appliedStatusEffects[SLOWNESS_APPLIED] = new SlownessApplied();
		stackSize -= 1;
		Game.inventory.deleteItemIfNecessary();
	}
	
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, SLIME_BALL, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, ARTICHOKE, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots);
	}
}
