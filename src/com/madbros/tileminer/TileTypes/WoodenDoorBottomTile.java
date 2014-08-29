package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;


import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class WoodenDoorBottomTile extends DoorBottomTile {
	Boolean isOpen = false;
	public WoodenDoorBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_BOTTOM_TILE);
		margin = new Margin(0, 0, 0, 0);
		id = WOOD_DOOR_BOTTOM_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = false;
		isChoppable = true;
		currentHp = 15;
		maxHp = 15;
		isUseable = true;
		autoTileID = WOOD_WALL_BOTTOM_TILE;
		
	}
	
	public Tile createNew() {
		return new WoodenDoorBottomTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		super.deleteMe(x, y, activeBlocks);
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(WOODEN_DOOR).createNew();
		Game.collectibleController.add(WOODEN_DOOR, Sprites.sprites.get(Sprites.WOODEN_DOOR), collectibleRect, 1, item.maxUses);
	}
}
