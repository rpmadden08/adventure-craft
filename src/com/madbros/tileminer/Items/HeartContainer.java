package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;

public class HeartContainer extends CraftItem {
	public HeartContainer() {
		id = HEART_CONTAINER;
		name = "Heart Container";
		sprite = Sprites.sprites.get(Sprites.HEART_CONTAINER);
		numberProducedByCrafting = 1;
		craftCost = new int[]{1};
		craftCostAmount = new int[]{1};
		itemsPossiblyCraftable = new int[]{LIFE_VIAL};
		//set recipes
	}
	
	@Override
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == SAND_CLUMP) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public HeartContainer createNew() {
		return new HeartContainer();
	}
}
