package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;

public class DarkDirtTile extends Tile{

	public DarkDirtTile() {
		maxHp = 20;
		currentHp = maxHp;
		layer = DARK_DIRT_LAYER;
		z = Z_DARK_DIRT;
		isCollidable = false;
		isMiddleTile = true;
		currentSpriteId= 0;
		sprites = new StaticSprite[]{(StaticSprite)Sprites.sprites.get(DARK_DIRT_SPRITE)};
		id = DARK_DIRT;
		isDiggable = true;
		topLeftAutoTile = 0;
		topRightAutoTile = 0;
		bottomRightAutoTile = 0;
		bottomLeftAutoTile = 0;
	}
	
	public Tile createNew() {
		return new DarkDirtTile();
	}
}
