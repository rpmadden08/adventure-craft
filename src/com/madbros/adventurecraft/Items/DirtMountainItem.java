package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class DirtMountainItem extends BlockItem {
	public DirtMountainItem() {
		id = DIRT_MOUNTAIN_ITEM;
		name = "Dirt Mountain";
		tileId = DIRT_MOUNTAIN_BOTTOM;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS};
		sprite = Sprites.sprites.get(Sprites.DIRT_MOUNTAIN_ITEM);
	}
	
	@Override
	public DirtMountainItem createNew() {
		return new DirtMountainItem();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
		int x = hB.getX(Game.level.activeBlocks);
		int y = hB.getY(Game.level.activeBlocks);
		Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_1] = new DirtMountainTopTile();
		
	}
}
