package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class GrassSeed extends BlockItem {
	public GrassSeed() {
		id = GRASS_SEED;
		tileId = GRASS;
		placeableTileIds = new int[]{DIRT, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.GRASS_ITEM);
	}
	
	@Override
	public GrassSeed createNew() {
		return new GrassSeed();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = tile;
	}
}
