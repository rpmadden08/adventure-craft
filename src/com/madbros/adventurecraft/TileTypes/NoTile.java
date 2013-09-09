package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

public class NoTile extends Tile {
	public NoTile() {
		currentSpriteId = 0;
		layer = DARK_DIRT_LAYER;
		z = Z_DARK_DIRT;
		isCollidable = false;
		isDiggable = false;
		id = AIR;
		isMiddleTile = true;
		topLeftAutoTile = MIDDLE_TILE;
		topRightAutoTile = MIDDLE_TILE;
		bottomRightAutoTile = MIDDLE_TILE;
		bottomLeftAutoTile = MIDDLE_TILE;
	}
	
	@Override
	public void render(int x, int y) {}
	
	@Override
	public NoTile createNew() {
		return new NoTile();
	}

}
