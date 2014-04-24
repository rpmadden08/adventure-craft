package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Stone extends CraftItem {
	public Stone() {
		id = STONE;
		name = "Stone";
		sprite = Sprites.sprites.get(Sprites.STONE);
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		itemsPossiblyCraftable = new int[]{FURNACE, STAIRS_DOWN, STAIRS_UP, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD};
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
