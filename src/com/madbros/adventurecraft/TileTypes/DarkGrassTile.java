package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Sprites.*;

public class DarkGrassTile extends Tile {
	public DarkGrassTile() {
		maxHp = 20;
		currentHp = 20;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		isTillable = true;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DARK_GRASS);
		id = DARK_GRASS;
		autoTileID = id;
	}

	public Tile createNew() {
		return new DarkGrassTile();
	}
}
