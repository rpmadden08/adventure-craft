package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class DarkDirtTile extends Tile{

	public DarkDirtTile() {
		maxHp = 20;
		currentHp = maxHp;
		layer = DARK_DIRT_LAYER;
		z = Z_DARK_DIRT;
		isCollidable = false;
		isMiddleTile = true;
		currentTexture = 0;
		textures = Textures.darkDirtTexture;
		id = DARK_DIRT;
		isDiggable = true;
	}
	
	public Tile createNew() {
		return new DarkDirtTile();
	}
	
	@Override
	public void render(int x, int y) {
		textures[0].draw(x, y, z, TILE_SIZE, TILE_SIZE);
	}
}
