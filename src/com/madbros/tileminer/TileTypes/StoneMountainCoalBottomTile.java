package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class StoneMountainCoalBottomTile extends CollisionTile {
	public StoneMountainCoalBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.GRASS_NEW);
		margin = new Margin(3, 3, 0, 12);
		id = STONE_MOUNTAIN_COAL_BOTTOM;
		autoTileID = STONE_MOUNTAIN_BOTTOM;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
		is32 = false;
	}
	
	public void render(int x, int y, int layer) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new StoneMountainCoalBottomTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y-2];
		//b = activeBlocks[xs[i]][ys[i]];
		b.layers[ABOVE_LAYER_2] = new StoneMountainTopTile(); 
		b.layers[ABOVE_LAYER_2].currentSpriteId = 0;
		b.layers[ABOVE_LAYER_2].z = Z_ABOVE_LAYER;
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(STONE_MOUNTAIN_ITEM).createNew();
		Game.collectibleController.add(STONE_MOUNTAIN_ITEM, Sprites.sprites.get(Sprites.STONE_MOUNTAIN_ITEM), collectibleRect, 1, item.maxUses);
		Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		item = ITEM_HASH.get(COAL_ITEM).createNew();
		Game.collectibleController.add(COAL_ITEM, Sprites.sprites.get(Sprites.COAL_ITEM), collectibleRect2, 1, item.maxUses);
		
	}
}
