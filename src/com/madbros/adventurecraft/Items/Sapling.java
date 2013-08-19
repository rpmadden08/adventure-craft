package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Textures;
//import com.madbros.adventurecraft.TileTypes.NoTile;
import com.madbros.adventurecraft.TileTypes.Tile;

public class Sapling extends BlockItem {
	public Sapling() {
		id = SAPLING_ITEM;
		tileId = SAPLING;
		
		placeableTileIds = new int[]{GRASS};
		texture = Textures.saplingItemTexture;
	}
	
	@Override
	public Sapling createNew() {
		return new Sapling();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
//		if(hB.layers[WATER_LAYER].id == HOLE) {
//			hB.layers[GRASS_LAYER] = tile;
//			hB.layers[WATER_LAYER] = new NoTile();
//			hB.layers[LIGHT_DIRT_LAYER] = new NoTile();
//			hB.collisionTile = null;
//			hB.cRect = null;
//		} else {
//			hB.layers[GRASS_LAYER] = tile;
//		}
	}
}
