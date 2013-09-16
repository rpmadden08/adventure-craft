package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import static com.madbros.adventurecraft.Constants.*;

public class SandTile extends Tile {
	public SandTile() {
		maxHp = 10;
		currentHp = 10;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DESERT);
		id = SAND;
	}
	
	public Tile createNew() {
		return new SandTile();
	}
}
