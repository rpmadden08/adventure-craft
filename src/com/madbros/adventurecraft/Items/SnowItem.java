package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class SnowItem extends BlockItem {
	public SnowItem() {
		id = SNOW_ITEM;
		tileId = SNOW;
		name = "Snow";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, DARK_GRASS, GRASS, WATER};
		sprite = Sprites.sprites.get(Sprites.SNOW_ITEM);
	}
	
	@Override
	public SnowItem createNew() {
		return new SnowItem();
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
