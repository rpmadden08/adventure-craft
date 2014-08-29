package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Helpers;

public class Stone extends CraftItem {
	public Stone() {
		id = STONE;
		name = "Stone";
		sprite = Sprites.sprites.get(Sprites.STONE);
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		itemsPossiblyCraftable = new int[]{FURNACE, ROOF,STAIRS_DOWN, STAIRS_UP, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD};
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
	public Stone createNew() {
		return new Stone();
	}
}
