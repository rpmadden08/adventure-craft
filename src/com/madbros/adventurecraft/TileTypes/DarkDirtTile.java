package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class DarkDirtTile extends Tile{

	public DarkDirtTile() {
		maxHp = 20;
		currentHp = maxHp;
		layer = DARK_DIRT_LAYER;
		z = Z_DARK_DIRT;
		isCollidable = false;
		isMiddleTile = true;
		currentSpriteId= 0;
		sprites = new StaticSprite[]{(StaticSprite)Sprites.sprites.get(Sprites.TILLED_SOIL)};
		id = DARK_DIRT;
		isDiggable = true;
		autoTile = 0;
	}
	
	public Tile createNew() {
		return new DarkDirtTile();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z, TILE_SIZE, TILE_SIZE);
	}
}
