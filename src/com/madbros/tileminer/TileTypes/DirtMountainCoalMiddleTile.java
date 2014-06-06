package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class DirtMountainCoalMiddleTile extends CollisionTile {
	public DirtMountainCoalMiddleTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DIRT_MOUNTAIN_COAL_MIDDLE_NEW);
		margin = new Margin(9, 9, 12, 11);
		id = DIRT_MOUNTAIN_COAL_MIDDLE;
		autoTileID = DIRT_MOUNTAIN_MIDDLE;
		layer = ABOVE_LAYER_2;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isPickable = true;
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
		return new DirtMountainCoalMiddleTile();
	}
}
