package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprite;

public abstract class Tile {
	public Sprite[] textures;
	public int currentTexture = 0;
	public int layer = 0;
	public boolean isCollidable = false;
	
	public int id = AIR;
	public boolean isMiddleTile = true;
	public int topLeftAutoTile = MIDDLE_TILE;
	public int topRightAutoTile = MIDDLE_TILE;
	public int bottomRightAutoTile = MIDDLE_TILE;
	public int bottomLeftAutoTile = MIDDLE_TILE;
	
	public void render(int x, int y) {
		int size = TILE_SIZE/2;		
		textures[topLeftAutoTile].draw(x, y, size, size);
		textures[topRightAutoTile].draw(x+size, y, size, size);
		textures[bottomLeftAutoTile].draw(x, y+size, size, size);
		textures[bottomRightAutoTile].draw(x+size, y+size, size, size);
	}
	
	public abstract Tile createNew();
}
