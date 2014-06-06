package com.madbros.tileminer.TileTypes;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

import static com.madbros.tileminer.Constants.*;

public class DirtTile extends Tile {
	public DirtTile() {
		maxHp = 20;
		currentHp = 20;
		layer = LIGHT_DIRT_LAYER;
		z = Z_LIGHT_DIRT;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DIRT_NEW);
		id = DIRT;
		autoTileID = id;
		isTillable = true;
		is32 = true;
		particleEffect = "dirtChunks.p";
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
		return new DirtTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(EARTH_CLUMP).createNew();
		Game.collectibleController.add(EARTH_CLUMP, Sprites.sprites.get(Sprites.DIRT_ITEM), collectibleRect, 1, item.maxUses);
		
	}
}
