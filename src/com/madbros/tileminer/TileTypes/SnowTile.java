package com.madbros.tileminer.TileTypes;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

import static com.madbros.tileminer.Constants.*;

public class SnowTile extends Tile {
	public SnowTile() {
		maxHp = 10;
		currentHp = 10;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.GRASS_NEW);
		id = SNOW;
		autoTileID = id;
		is32 = false;
	}
	
	public Tile createNew() {
		return new SnowTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Game.level.highlightedBlock.layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(SNOW_ITEM).createNew();
		Game.collectibleController.add(SNOW_ITEM, Sprites.sprites.get(Sprites.SNOW_ITEM), collectibleRect, 1,item.maxUses);
		
	}
}
