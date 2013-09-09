package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.TileTypes.*;

public class SandClump extends BlockItem {
	public SandClump() {
		id = SAND_CLUMP;
		tileId = SAND;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, WATER};
		sprite = Sprites.sandClumpSprite;
	}
	
	@Override
	public SandClump createNew() {
		return new SandClump();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		if(hB.layers[WATER_LAYER].id == WATER) {
			hB.layers[GRASS_LAYER] = tile;
			hB.layers[WATER_LAYER] = new NoTile();
			hB.collisionTile = null;
		} else {
			hB.layers[GRASS_LAYER] = tile;
		}
	}
}
