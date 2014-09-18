package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class WoodenFloor extends Floor {
	public WoodenFloor() {
		id = WOODEN_FLOOR;
		tileId = WOOD_FLOOR_TILE;
		name = "Wood Flooring";
		//placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.WOODEN_FLOOR);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{2};
	}
	
	@Override
	public TileItem createNew() {
		return new WoodenFloor();
	}
}
