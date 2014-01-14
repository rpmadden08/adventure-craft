package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class DarkGrassItem extends BlockItem {
	public DarkGrassItem() {
		id = DARK_GRASS_ITEM;
		name = "Swamp Grass Seed";
		tileId = DARK_GRASS;
		placeableTileIds = new int[]{DIRT, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.DARK_GRASS_ITEM);
	}
	
	@Override
	public DarkGrassItem createNew() {
		return new DarkGrassItem();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = tile;
	}
}
