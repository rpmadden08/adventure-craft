package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;

import static com.madbros.adventurecraft.Constants.*;

public class DirtTile extends Tile {
	public DirtTile() {
		maxHp = 20;
		currentHp = 20;
		layer = LIGHT_DIRT_LAYER;
		z = Z_LIGHT_DIRT;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.dirtSprites;
		id = DIRT;
	}
	
	public Tile createNew() {
		return new DirtTile();
	}
}
