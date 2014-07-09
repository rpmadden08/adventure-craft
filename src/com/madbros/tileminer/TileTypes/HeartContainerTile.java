package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class HeartContainerTile extends CollisionTile {
	
	public HeartContainerTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.HEART_CONTAINER_TILE);
		margin = new Margin(0, 0, 0, 0);
		id = HEART_CONTAINER_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = false;
		currentHp = 1;
		maxHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
//	@Override
//	public void rightClicked() {
//		
//		if(Game.currentState.type == State.MAIN && Game.level.hasPlacedItemOnClick == false) {
//			Game.inventory.currentWorkSpace = TABLE_WORKSPACE;
//			Game.toggleInventoryState();
//		}
//	}
	
	public Tile createNew() {
		return new HeartContainerTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		//Block b2 = activeBlocks[x][y-1];
		b.collisionTile = null;
		b.layers[OBJECT_LAYER] = new NoTile();
		//b2.layers[ABOVE_LAYER_1] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(HEART_CONTAINER).createNew();
		Game.collectibleController.add(HEART_CONTAINER, Sprites.sprites.get(Sprites.HEART_CONTAINER), collectibleRect, 1, item.maxUses);
		
	}
}
