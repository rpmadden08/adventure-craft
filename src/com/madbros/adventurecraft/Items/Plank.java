package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Utils.Helpers;

public class Plank extends CraftItem {
	public Plank() {
		id = PLANK;
		texture = Textures.plankTexture;
		numberProducedByCrafting = 4;
		craftCost = new int[]{LOG};
		//set recipes
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(1, LOG, craftingSlots) &&
			   Helpers.containsXNumberOfItemsInSlots(3, EMPTY, craftingSlots);
	}
	
	@Override
	public Plank createNew() {
		return new Plank();
	}
}
