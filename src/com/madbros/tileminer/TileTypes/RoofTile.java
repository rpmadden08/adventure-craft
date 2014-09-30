package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.*;
import com.madbros.tileminer.Utils.Rect;

public class RoofTile extends RoofBaseTile {
	public RoofTile() {
		maxHp = 1;
		currentHp = 1;
		layer = ABOVE_LAYER_4;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.ROOF_TILE);
		id = ROOF_TILE;
		autoTileID = id;
		isBreakable = true;
		is32 = false;
		particleEffect = "grassChunks.p";
		

	}
	
	public Tile createNew() {
		return new RoofTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_4] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(ROOF).createNew();
		Game.collectibleController.add(ROOF, Sprites.sprites.get(Sprites.ROOF), collectibleRect, 1, item.maxUses);
	}
}
