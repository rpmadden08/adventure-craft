package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Helpers;

public class Plank extends CraftItem {
	public Plank() {
		id = PLANK;
		name = "Plank";
		sprite = Sprites.sprites.get(Sprites.PLANK_ITEM);
		numberProducedByCrafting = 4;
		craftCost = new int[]{LOG};
		craftCostAmount = new int[]{1};
		itemsPossiblyCraftable = new int[]{CHEST,STICK, TABLE,WOODEN_AXE, WOODEN_HOE, WOODEN_PICK, WOODEN_SHOVEL, WOODEN_SWORD};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
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
	public Plank createNew() {
		return new Plank();
	}
}
