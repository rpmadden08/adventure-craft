package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class TableItem extends BlockItem32 {
	public TableItem() {
		id = TABLE_ITEM;
		tileId = TABLE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.TABLE_ITEM);
		maxStackSize = 3;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK, PLANK, PLANK, PLANK};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new TableItem();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

}