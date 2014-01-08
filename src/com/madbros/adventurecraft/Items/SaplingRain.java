package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class SaplingRain extends BlockItem32 {
	public SaplingRain() {
		id = SAPLING_ITEM;
		tileId = SAPLING_RAIN;
		name = "Rain Forest Tree Sapling";
		
		placeableTileIds = new int[]{GRASS, DIRT, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SAPLING);
		
	}
	
	@Override
	public SaplingRain createNew() {
		return new SaplingRain();
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
