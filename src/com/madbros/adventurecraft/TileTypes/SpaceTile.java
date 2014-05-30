package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class SpaceTile extends CollisionTile {
	public SpaceTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;

		sprites = Sprites.spriteCollections.get(Sprites.HOLE_NEW);

		margin = new Margin(1, 0, 0, 1);
		id = SPACE;
		autoTileID = id;
		isDiggable = false;
		is32 = true;
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
		return new SpaceTile();
	}
}
