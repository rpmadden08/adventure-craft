package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

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