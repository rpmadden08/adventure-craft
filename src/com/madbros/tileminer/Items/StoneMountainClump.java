package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

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
