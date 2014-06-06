package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.StatusEffects.Speed;
import com.madbros.tileminer.Utils.Helpers;

public class SpeedPotion extends PotionItem {
	public SpeedPotion() {
		id = SPEED_POTION;
		name = "Speed Potion";
		sprite = Sprites.sprites.get(Sprites.SPEED_POTION);
		craftCost = new int[]{CACTUS_SEED, BAT_WING, GLASS_BOTTLE};
		
	}
	@Override
	public PotionItem createNew() {
		return new SpeedPotion();
	}
	
	public boolean canUsePotion() {
		//FIXME  Need to implement a method here...
		if(Game.hero.timedStatusEffects[SPEED].id == SPEED) {
			return false;
		} else {
			return true;
		}
	}
	
	public void applyPotionEffect() {
		Game.hero.timedStatusEffects[SPEED] = new Speed();
		Game.hero.speedSpeed = Speed.speedAmount;
		Game.hero.checkSpeed();
		stackSize -= 1;
		Game.inventory.deleteItemIfNecessary();
	}
	
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
//		boolean a = Helpers.containsXNumberOfItemsInSlots(1, BAT_WING, craftingSlots);
//		boolean b = Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots);
//		boolean c = Helpers.containsXNumberOfItemsInSlots(1, CACTUS_SEED, craftingSlots);
//		if(a && b && c) {
//			return true;
//		} else {
//			return false;
//		}
		return Helpers.containsXNumberOfItemsInSlots(1, BAT_WING, craftingSlots) &&
				Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, CACTUS_SEED, craftingSlots);
	}
}
