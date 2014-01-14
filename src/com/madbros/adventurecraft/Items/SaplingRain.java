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
		
		placeableTileIds = new int[]{GRASS, DIRT,DARK_GRASS, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SAPLING);
		
	}
	
	@Override
	public SaplingRain createNew() {
		return new SaplingRain();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;

	}
	
}
