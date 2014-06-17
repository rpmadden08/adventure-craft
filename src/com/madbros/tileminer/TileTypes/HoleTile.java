package com.madbros.tileminer.TileTypes;

import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Margin;

import static com.madbros.tileminer.Constants.*;

public class HoleTile extends CollisionTile {
	public HoleTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;

		sprites = Sprites.spriteCollections.get(Sprites.HOLE_NEW);

		margin = new Margin(1, 0, 0, 1);
		id = HOLE;
		autoTileID = WATER;
		isDiggable = false;
		is32 = false;
	}
	
	public void render(int x, int y) {
		int size = TILE_SIZE/2;

		//This one if statement often increases render time by about 300-400 ms.
		if(topLeftAutoTile == MIDDLE_TILE && topRightAutoTile == MIDDLE_TILE &&
		   bottomLeftAutoTile == MIDDLE_TILE && bottomRightAutoTile == MIDDLE_TILE) {
			sprites[MIDDLE_TILE].draw(x, y, z);
		} else {
			//sprites[topLeftAutoTile].draw(x, y, z, size, size);
			sprites[topLeftAutoTile].draw(x, y, z);
			sprites[topRightAutoTile].draw(x+size, y, z);
			sprites[bottomLeftAutoTile].draw(x, y+size, z);
			sprites[bottomRightAutoTile].draw(x+size, y+size, z);
		}
	}
	
	public Tile createNew() {
		return new HoleTile();
	}
}