package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.*;

public class CookedSteak extends FoodItem {
	public CookedSteak() {
		id = COOKED_STEAK;
		name = "Cooked Steak";
		sprite = Sprites.sprites.get(Sprites.COOKED_STEAK);
		itemsPossiblyCraftable = new int[]{};
		energyPercentage = 0.4;
		craftCost = new int[]{STEAK};
		craftCostAmount = new int[]{1}; 
		workSpaceNeeded = new int[]{FURNACE_WORKSPACE};
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
