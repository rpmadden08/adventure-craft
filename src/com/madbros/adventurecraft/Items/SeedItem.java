package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.Tile;

public abstract class SeedItem extends StackableItem {
	int tileID = GRASS;
	@Override
	public abstract SeedItem createNew();
	
	public void useRight() {
		Tile tile = TILE_HASH.get(tileID).createNew();
		if(Game.level.highlightedBlock.layers[GRASS_LAYER].id == DIRT) {
			Game.level.highlightedBlock.layers[GRASS_LAYER] = tile;
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
}
