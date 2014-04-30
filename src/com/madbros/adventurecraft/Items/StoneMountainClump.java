package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class StoneMountainClump extends CraftItem {
	public StoneMountainClump() {
		id = STONE_MOUNTAIN_CLUMP;
		name = "Stone Mountain Chunk";
		sprite = Sprites.sprites.get(Sprites.STONE_MOUNTAIN_ITEM);
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		itemsPossiblyCraftable = new int[]{STONE_MOUNTAIN_ITEM};
	}
	
	@Override
	public StoneMountainClump createNew() {
		return new StoneMountainClump();
	}
}