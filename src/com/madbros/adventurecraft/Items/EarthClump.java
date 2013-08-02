package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.TileTypes.*;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		tileId = DIRT;
		texture = Textures.earthClumpTexture;
		placeableTileIds = new int[]{WATER, SAND, DARK_DIRT, HOLE};
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		if(hB.layers[WATER_LAYER].id == HOLE) {
			hB.layers[GRASS_LAYER] = new NoTile();
			hB.layers[WATER_LAYER] = new NoTile();
			hB.layers[LIGHT_DIRT_LAYER] = new NoTile();
			hB.collisionTile = null;
			hB.cRect = null;
		} else {
			hB.layers[GRASS_LAYER] = new NoTile();
			hB.layers[WATER_LAYER] = new NoTile();
			hB.layers[LIGHT_DIRT_LAYER] = tile;
		}
	}
}
