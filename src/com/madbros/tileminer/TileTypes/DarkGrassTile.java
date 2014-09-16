package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

public class DarkGrassTile extends Tile {
	public DarkGrassTile() {
		maxHp = 20;
		currentHp = 20;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		isTillable = true;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DARK_GRASS);
		id = DARK_GRASS;
		autoTileID = id;
		isDiggable = true;
		is32 = false;
	}

	public Tile createNew() {
		return new DarkGrassTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = Game.level.highlightedBlock;
		b.layers[GRASS_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
	
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(DARK_GRASS_ITEM).createNew();
		Game.collectibleController.add(DARK_GRASS_ITEM, Sprites.sprites.get(Sprites.DARK_GRASS_ITEM), collectibleRect, 1, item.maxUses);
		
	}
}
