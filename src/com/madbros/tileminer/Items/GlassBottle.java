package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;

public class GlassBottle extends CraftItem {
	public GlassBottle() {
		id = GLASS_BOTTLE;
		name = "Glass Bottle";
		sprite = Sprites.sprites.get(Sprites.GLASS_BOTTLE);
		numberProducedByCrafting = 1;
		craftCost = new int[]{SAND_CLUMP};
		craftCostAmount = new int[]{1};
		workSpaceNeeded = new int[]{FURNACE_WORKSPACE};
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{SPEED_POTION, HEALTH_POTION, SLOWNESS_POTION, HARMING_POTION};
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
	public GlassBottle createNew() {
		return new GlassBottle();
	}
}
