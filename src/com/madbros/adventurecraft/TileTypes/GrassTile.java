package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Rect;

public class GrassTile extends Tile {
	public GrassTile() {
		maxHp = 20;
		currentHp = 20;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.GRASS_NEW);
		id = GRASS;
		autoTileID = id;
		isTillable = true;
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
		return new GrassTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Game.level.highlightedBlock.layers[GRASS_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(GRASS_SEED, Sprites.sprites.get(Sprites.GRASS_ITEM), collectibleRect, 1);
		
	}
}
