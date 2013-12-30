package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import static com.madbros.adventurecraft.Constants.*;

public class DirtTile extends Tile {
	public DirtTile() {
		maxHp = 20;
		currentHp = 20;
		layer = LIGHT_DIRT_LAYER;
		z = Z_LIGHT_DIRT;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.LIGHT_DIRT);
		id = DIRT;
		isTillable = true;
	}
	
	public Tile createNew() {
		return new DirtTile();
	}
}
