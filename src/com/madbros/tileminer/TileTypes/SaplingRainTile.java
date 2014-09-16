package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class SaplingRainTile extends CollisionTile {
	
	public SaplingRainTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.SAPLING_COLLECTION);
		margin = new Margin(4, 5, 5, 14);
		id = SAPLING_RAIN;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new SaplingRainTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getGameTime() - timeCreated > 10000) {// 3000
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new NoTile();
			b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
			b.setCollisionTile(new TreeLeafRainTile());
			int[] xs = {x-1, x-1, x-1, x, x, x, x, x+1,x+1, x+1};
			int[] ys = {y-3, y-2, y-1, y-3, y-2, y-1, y, y-3, y-2, y-1};
			int[] tileLayer = {TREE_LEFT_3, TREE_LEFT_2, TREE_LEFT_1, TREE_CENTER_3,TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
			
			for(int i = 0; i < 10; i++) {
				b = Game.level.activeBlocks[xs[i]][ys[i]];
				b.layers[tileLayer[i]] = new TreeLeafRainTile(); 
				b.layers[tileLayer[i]].currentSpriteId = i;
				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
			}
		}
	}
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(SAPLING_ITEM).createNew();
		Game.collectibleController.add(SAPLING_ITEM, Sprites.sprites.get(Sprites.SAPLING), collectibleRect, 1,item.maxUses);
	}
}
