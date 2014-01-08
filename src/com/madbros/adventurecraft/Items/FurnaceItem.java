package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.FurnaceTop;
import com.madbros.adventurecraft.TileTypes.Tile;

public class FurnaceItem extends BlockItem32 {
	public FurnaceItem() {
		id = FURNACE_ITEM;
		name = "Furnace";
		tileId = FURNACE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.FURNACE_SINGLE);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new FurnaceItem();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return false;
		//return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
		int x = hB.getX(Game.level.activeBlocks);
		int y = hB.getY(Game.level.activeBlocks);
		Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new FurnaceTop();
		
	}

}
