package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.StatusEffects.SlownessApplied;
import com.madbros.tileminer.Utils.Helpers;

public class SlownessPotion extends PotionItem {
	public SlownessPotion() {
		id = SLOWNESS_POTION;
		name = "Slowness Potion";
		sprite = Sprites.sprites.get(Sprites.SLOWNESS_POTION);
		craftCost = new int[]{SLIME_BALL, ARTICHOKE, GLASS_BOTTLE};
		craftCostAmount = new int[]{1,1,1}; 
		workSpaceNeeded = new int[]{CAULDRON_WORKSPACE};
		
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
