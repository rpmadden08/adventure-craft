package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprite;

public abstract class Tile {
	public Sprite[] textures;
	public int currentTexture = 0;
	public boolean isCollidable = false;
	public boolean isBaseTile = true;
	public int id;
	public int topLeftAutoTile = 6;
	public int topRightAutoTile = 6;
	public int bottomRightAutoTile = 6;
	public int bottomLeftAutoTile = 6;
	
	public void render(int x, int y) {
		int size = TILE_SIZE/2;
		
		//This one if statement often increases render time by about 300-400 ms.
		if(topLeftAutoTile == MIDDLE_TILE && topRightAutoTile == MIDDLE_TILE &&
		   bottomLeftAutoTile == MIDDLE_TILE && bottomRightAutoTile == MIDDLE_TILE) {
			textures[MIDDLE_TILE].draw(x, y);
		} else {
			textures[topLeftAutoTile].draw(x, y, size, size, 0, 0, size, size);
			textures[topRightAutoTile].draw(x+size, y, size, size, size, 0, size, size);
			textures[bottomLeftAutoTile].draw(x, y+size, size, size, 0, size, size, size);
			textures[bottomRightAutoTile].draw(x+size, y+size, size, size, size, size, size, size);
		}
	}
	
	public abstract Tile createNew();
}
