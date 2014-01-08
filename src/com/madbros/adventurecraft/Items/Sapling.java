package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class Sapling extends BlockItem32 {
	public Sapling() {
		id = SAPLING_ITEM;
		tileId = SAPLING;
		name = "Sapling";
		placeableTileIds = new int[]{GRASS, DIRT, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SAPLING);
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Sapling();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
	}
}
