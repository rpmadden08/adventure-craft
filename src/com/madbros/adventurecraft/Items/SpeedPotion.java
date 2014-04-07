package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.StatusEffects.Slowness;
import com.madbros.adventurecraft.StatusEffects.Speed;
import com.madbros.adventurecraft.Utils.Helpers;

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
		Game.hero.moveSpeed = Game.hero.moveSpeed + Speed.speedAmount;
		Game.hero.currentSpeed = Game.hero.moveSpeed;
		stackSize -= 1;
		Game.inventory.deleteItemIfNecessary();
	}
	
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, BAT_WING, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, CACTUS_SEED, craftingSlots) &&
				   Helpers.containsXNumberOfItemsInSlots(1, GLASS_BOTTLE, craftingSlots);
	}
}
