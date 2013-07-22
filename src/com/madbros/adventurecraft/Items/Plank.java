package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public class Plank extends CraftItem {
	public Plank() {
		id = PLANK;
		texture = Textures.plankTexture;
		numberProducedByCrafting = 4;
		craftCost = new int[]{LOG};
		//set recipes
	}
	
	@Override
	public boolean isValidRecipe(Cell[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInCells(1, LOG, craftingSlots) &&
			   Helpers.containsXNumberOfItemsInCells(3, EMPTY, craftingSlots);
	}
	
	@Override
	public Plank createNew() {
		return new Plank();
	}
}
