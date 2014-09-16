package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;


public class CactusTile extends CollisionTile {
	
	public CactusTile() {
		super();
		currentSpriteId = 3;
		maxHp = 20;
		currentHp = 20;
		sprites = Sprites.spriteCollections.get(Sprites.CACTUS);
		margin = new Margin(1, 0, 0, 5);	//3, 3, 0, 12

		id = CACTUS;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isChoppable = true;
		isAutoTileable = false;
		autoTile = 0;
		particleEffect = "cactusChunks.p";
		sound = "sounds/axeChop.wav";
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new CactusTile();
	}
	
	@Override	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		int[] xs = {x-1, x-1, x-1, x, x, x, x+1,x+1, x+1};
		int[] ys = {y-2, y-1, y, y-2, y-1, y, y-2, y-1, y};
		int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
		
		for(int i = 0; i < 9; i++) {
			b = activeBlocks[xs[i]][ys[i]];
			b.layers[tileLayer[i]] = new NoTile(); 
			b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		}
		activeBlocks[x][y].collisionTile = null;
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CACTUS_SEED).createNew();
		Game.collectibleController.add(CACTUS_SEED, Sprites.sprites.get(Sprites.CACTUS_ITEM), collectibleRect, 1, item.maxUses );
		Game.collectibleController.add(CACTUS_SEED, Sprites.sprites.get(Sprites.CACTUS_ITEM), collectibleRect2, 1, item.maxUses );

	}
	
	public void highlightEntireObject(int x, int y, int drawX, int drawY) {
		
	}
}
