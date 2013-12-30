package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;

public class BakedPotatoes extends FoodItem {
	public BakedPotatoes() {
		id = BAKED_POTATOES;
		sprite = Sprites.sprites.get(Sprites.BAKED_POTATOES);
		numberProducedByCrafting = 1;
		craftCost = new int[]{POTATOES};
		
	}
	@Override
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == POTATOES) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public boolean isValidCauldronRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == POTATOES || craftingSlots[1].item.id == POTATOES || craftingSlots[2].item.id == POTATOES) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public FoodItem createNew() {
		return new BakedPotatoes();
	}
}
