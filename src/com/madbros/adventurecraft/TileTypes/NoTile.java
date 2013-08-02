package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.AIR;
import static com.madbros.adventurecraft.Constants.MIDDLE_TILE;

public class NoTile extends Tile {
	public NoTile() {
		currentTexture = 0;
		layer = 0;
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
