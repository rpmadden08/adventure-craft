package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.*;

public class CookedSteak extends FoodItem {
	public CookedSteak() {
		id = COOKED_STEAK;
		name = "Cooked Steak";
		sprite = Sprites.sprites.get(Sprites.COOKED_STEAK);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public CookedSteak createNew() {
		return new CookedSteak();
	}
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == STEAK) {
			return true;
		} else {
			return false;
		}
	}
}
