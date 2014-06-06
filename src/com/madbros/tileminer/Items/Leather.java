package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Helpers;

public class Leather extends CraftItem {
	public Leather() {
		id = LEATHER;
		name = "Leather";
		sprite = Sprites.sprites.get(Sprites.LEATHER);
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
		craftCostAmount = new int[]{1};
		itemsPossiblyCraftable = new int[]{LEATHER_ARMOR, LEATHER_BOOTS, LEATHER_HAT, LEATHER_LEGGINGS};
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
	public Leather createNew() {
		return new Leather();
	}
}
