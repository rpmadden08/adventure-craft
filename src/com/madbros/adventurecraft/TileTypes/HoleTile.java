package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class HoleTile extends CollisionTile {
	public HoleTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;

		sprites = Sprites.spriteCollections.get(Sprites.HOLE);

		margin = new Margin(14, 14, 14, 14);
		id = HOLE;
		autoTileID = id;
		isDiggable = false;
		is32 = false;
	}
	
	public Tile createNew() {
		return new HoleTile();
	}
}
