package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;

import static com.madbros.adventurecraft.Constants.*;

public class DirtTile extends Tile {
	public DirtTile() {
		isBaseTile = true;
		isCollidable = false;
		currentTexture = 0;
		textures = Textures.dirtTextures;
		id = DIRT;
	}
	
	public Tile createNew() {
		return new DirtTile();
	}
}
