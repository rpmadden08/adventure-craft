package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import static com.madbros.adventurecraft.Constants.*;

public class SnowTile extends Tile {
	public SnowTile() {
		maxHp = 10;
		currentHp = 10;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.SNOW);
		id = SNOW;
		autoTileID = id;
	}
	
	public Tile createNew() {
		return new SnowTile();
	}
}
