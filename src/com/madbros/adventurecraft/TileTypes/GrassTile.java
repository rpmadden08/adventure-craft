package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public class GrassTile extends Tile {
	public GrassTile() {
		isBaseTile = true;
		isCollidable = false;
		currentTexture = 0;
		textures = Textures.grassTextures;
		id = GRASS;
	}
	
	public void render(int x, int y) {
		textures[currentTexture].draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new GrassTile();
	}
}
