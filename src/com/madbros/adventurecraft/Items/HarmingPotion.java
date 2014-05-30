package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.StatusEffects.HarmingApplied;
import com.madbros.adventurecraft.Utils.Helpers;

public class HarmingPotion extends PotionItem {
	public HarmingPotion() {
		id = HARMING_POTION;
		name = "Harming Potion";
		sprite = Sprites.sprites.get(Sprites.HARMING_POTION);
		craftCost = new int[]{WORM_GUTS, PEPPER, GLASS_BOTTLE};
		
	}
	@Override
	public PotionItem createNew() {
		return new HarmingPotion();
	}
	
	public boolean canUsePotion() {
		if(Game.hero.appliedStatusEffects[HARMING_APPLIED].usesLeft <5) { 
		return true;
		} else {
		return false;
		}
	}
	
	public void applyPotionEffect() {
		Game.hero.appliedStatusEffects[HARMING_APPLIED] = new HarmingApplied();
		stackSize -= 1;
		Game.inventory.deleteItemIfNecessary();
	}
	
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, PEPPER, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, WORM_GUTS, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots);
	}
}
