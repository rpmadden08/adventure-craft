package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class DirtMountainClump extends CraftItem {
	public DirtMountainClump() {
		id = DIRT_MOUNTAIN_CLUMP;
		name = "Dirt Mountain Chunk";
		sprite = Sprites.sprites.get(Sprites.DIRT_MOUNTAIN_CLUMP);
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		itemsPossiblyCraftable = new int[]{DIRT_MOUNTAIN_ITEM};
		//set recipes
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, LOG, craftingSlots) &&
			   Helpers.containsXNumberOfItemsInSlots(3, EMPTY, craftingSlots);
	}
	
	@Override
	public boolean isValidTableRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, LOG, craftingSlots) &&
			   Helpers.containsXNumberOfItemsInSlots(8, EMPTY, craftingSlots);
	}
	
	@Override
	public DirtMountainClump createNew() {
		return new DirtMountainClump();
	}
}
