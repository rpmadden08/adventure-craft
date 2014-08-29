package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class WoodenWall extends Wall {
	public WoodenWall() {
		id = WOOD_WALL;
		name = "Wooden Wall";
		tileId = WOOD_WALL_BOTTOM_TILE;
		tile2Id = WOOD_WALL_MIDDLE_TILE;
		tile3Id = WOOD_WALL_TOP_TILE;
		
		//placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.WOOD_WALL);
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{8};
		maxStackSize = 99;
	}
	
	@Override
	public WoodenWall createNew() {
		return new WoodenWall();
	}
	
}