package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;

import static com.madbros.adventurecraft.Constants.*;

public class SandTile extends Tile {
	public SandTile() {
		maxHp = 10;
		currentHp = 10;
		layer = GRASS_LAYER;
		isCollidable = false;
		currentTexture = 0;
		textures = Textures.sandTextures;
		id = SAND;
	}
	
	public Tile createNew() {
		return new SandTile();
	}
}
