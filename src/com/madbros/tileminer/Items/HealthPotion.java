package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Helpers;

public class HealthPotion extends PotionItem {
	public HealthPotion() {
		id = HEALTH_POTION;
		name = "Health Potion";
		sprite = Sprites.sprites.get(Sprites.HEALTH_POTION);
		craftCost = new int[]{RED_FLOWERS, HONEY, GLASS_BOTTLE};
		
	}
	@Override
	public PotionItem createNew() {
		return new HealthPotion();
	}
	
	public boolean canUsePotion() {
		if(Game.hero.hP == Game.hero.maxHP) {
			return false;
		} else {
			return true;
		}
	}
	
	public void applyPotionEffect() {
		int hPIncreaseAmount = 10;
		if(Game.hero.hP +hPIncreaseAmount >= Game.hero.maxHP) {
			Game.hero.hP = Game.hero.maxHP;
		} else {
			Game.hero.hP = Game.hero.hP + hPIncreaseAmount;
		}
		stackSize -= 1;
		Game.inventory.deleteItemIfNecessary();
	}
	
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, HONEY, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, RED_FLOWERS, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots);
	}
}
