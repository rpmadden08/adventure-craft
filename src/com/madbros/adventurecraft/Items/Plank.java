package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Plank extends CraftItem {
	public Plank() {
		id = PLANK;
		name = "Plank";
		sprite = Sprites.sprites.get(Sprites.PLANK_ITEM);
		numberProducedByCrafting = 4;
		craftCost = new int[]{LOG};
		craftCostAmount = new int[]{1};
		itemsPossiblyCraftable = new int[]{TABLE, STICK};
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
