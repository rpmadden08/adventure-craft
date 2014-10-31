package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class WoodenFloorTile extends FloorTile {

	public WoodenFloorTile() {
		super();
		//lightSize = 500;
		id = WOOD_FLOOR_TILE;
		sprites = Sprites.spriteCollections.get(Sprites.WOODEN_FLOOR_TILE);
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = false;
		isChoppable = true;
		currentHp = 10;
		maxHp = 10;
		currentSpriteId = 0;
		margin = new Margin(2, 0, 8, 0);
		layer = GRASS_LAYER;
		sound = "sounds/axeChop.wav";
	}
	
	@Override
	public Tile createNew() {
		return new WoodenFloorTile();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		super.deleteMe(x, y, activeBlocks);
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(WOODEN_FLOOR).createNew();
		Game.collectibleController.add(WOODEN_FLOOR, Sprites.sprites.get(Sprites.WOODEN_FLOOR), collectibleRect, 1, item.maxUses);
	}
}
