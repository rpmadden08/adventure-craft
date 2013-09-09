package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public class GrassTile extends Tile {
	public GrassTile() {
		maxHp = 20;
		currentHp = 20;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.grassSprites;
		id = GRASS;
	}

	public Tile createNew() {
		return new GrassTile();
	}
}
