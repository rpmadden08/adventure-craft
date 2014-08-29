package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class WoodWallBottomTile extends WallBottomTile {
	public WoodWallBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WOOD_WALL_BOTTOM);
		margin = new Margin(4, 5, 0, 5);
		id = WOOD_WALL_BOTTOM_TILE;
		autoTileID = id;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isChoppable = true;
		is32 = true;
		maxHp = 15;
		currentHp = 15;
		particleEffect = "mountainChunks.p";
	}
	
	
	public Tile createNew() {
		return new WoodWallBottomTile();
	}

	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		super.deleteMe(x, y, activeBlocks);
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(WOOD_WALL).createNew();
		Game.collectibleController.add(WOOD_WALL, Sprites.sprites.get(Sprites.WOOD_WALL), collectibleRect, 1, item.maxUses);
	}
}
