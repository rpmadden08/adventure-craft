package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class DarkDirtTile extends Tile{

	public DarkDirtTile() {
		layer = DARK_DIRT_LAYER;
		isCollidable = false;
		currentTexture = 0;
		textures = Textures.darkDirtTextures;
		id = DARK_DIRT;
	}
	
	public Tile createNew() {
		return new DarkDirtTile();
	}
}
