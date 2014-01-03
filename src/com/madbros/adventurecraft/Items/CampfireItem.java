package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;

public class CampfireItem extends BlockItem32 {
	public CampfireItem() {
		id = CAMPFIRE_ITEM;
		tileId = CAMPFIRE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS};
		sprite = Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE);
	}
	
	@Override
	public BlockItem32 createNew() {
		return new CampfireItem();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

}
