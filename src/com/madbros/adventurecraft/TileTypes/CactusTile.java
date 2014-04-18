package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;


public class CactusTile extends CollisionTile {
	
	public CactusTile() {
		super();
		currentSpriteId = 3;
		maxHp = 20;
		currentHp = 20;
		sprites = Sprites.spriteCollections.get(Sprites.CACTUS);
		margin = new Margin(9, 9, 12, 11);	//3, 3, 0, 12

		id = CACTUS;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isChoppable = true;
		isAutoTileable = false;
		autoTile = 0;
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
			//b.layers[tileLayer[i]].currentSpriteId = i;
			//b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
		}
		activeBlocks[x][y].collisionTile = null;
		//b.collisionTile = null;
		//new Rect(Game.level.activeBlocks[x][y].absRect.x,Game.level.activeBlocks[x][y].absRect.y,CHARACTER_SIZE, CHARACTER_SIZE);
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CACTUS_SEED).createNew();
		Game.collectibleController.add(CACTUS_SEED, Sprites.sprites.get(Sprites.CACTUS_ITEM), collectibleRect, 1, item.maxUses );
		Game.collectibleController.add(CACTUS_SEED, Sprites.sprites.get(Sprites.CACTUS_ITEM), collectibleRect2, 1, item.maxUses );

	}
	
	public void highlightEntireObject(int x, int y, int drawX, int drawY) {
//		Block b = Game.level.activeBlocks[x][y];
//		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].currentSpriteId].setColor(HIGHLIGHT_COLOR);
//		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].currentSpriteId].draw(drawX, drawY, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
//		b.layers[OBJECT_LAYER].sprites[b.layers[OBJECT_LAYER].currentSpriteId].setColor(1f,1f,1f,1f);
//		
//		
//		int[] xs = {x-1, x-1, x-1, x, x, x+1,x+1, x+1};
//		int[] ys = {y-2, y-1, y, y-2, y-1, y-2, y-1, y};
//		int[] drawXs = {drawX-TILE_SIZE,drawX-TILE_SIZE, drawX-TILE_SIZE, drawX, drawX, drawX + TILE_SIZE, drawX + TILE_SIZE, drawX + TILE_SIZE};
//		int[] drawYs = {drawY-(TILE_SIZE*2), drawY-TILE_SIZE, drawY, drawY-(TILE_SIZE*2),drawY-TILE_SIZE,drawY-(TILE_SIZE*2), drawY-TILE_SIZE, drawY};
//		int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
//		for(int i = 0; i < 8; i++) {
//			b = Game.level.activeBlocks[xs[i]][ys[i]];
//			int spriteId = b.layers[tileLayer[i]].currentSpriteId;
//			b.layers[tileLayer[i]].sprites[spriteId].setColor(HIGHLIGHT_COLOR);
//			b.layers[tileLayer[i]].sprites[spriteId].draw(drawXs[i], drawYs[i], z);
//			b.layers[tileLayer[i]].sprites[spriteId].setColor(1f,1f,1f,1f);
//		}
	}
}
