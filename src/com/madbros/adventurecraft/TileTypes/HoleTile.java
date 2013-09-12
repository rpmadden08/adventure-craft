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

		sprites = Sprites.holeSprites;

		margin = new Margin(14, 14, 14, 14);
		id = HOLE;
		isDiggable = false;
	}
	
	public Tile createNew() {
		return new HoleTile();
	}
}
