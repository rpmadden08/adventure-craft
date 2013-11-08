package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;


public class TreeTile extends CollisionTile {
	
	public TreeTile() {
		super();
		currentSpriteId = 5;
		maxHp = 20;
		currentHp = 20;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_TWO);
		margin = new Margin(9, 9, 12, 11);	//3, 3, 0, 12

		id = TREE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isChoppable = true;
		isAutoTileable = false;
		autoTile = 0;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new TreeTile();
	}
	
	@Override
//	public void bloom(int x, int y, Block[][] activeBlocks) {
//		Block b = activeBlocks[x][y];
//		
//		int[] xs = {x-1, x-1, x-1, x, x, x, x+1,x+1, x+1};
//		int[] ys = {y-2, y-1, y, y-2, y-1, y, y-2, y-1, y};
//		int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
//		
//		for(int i = 0; i < 9; i++) {
//			b = activeBlocks[xs[i]][ys[i]];
//			b.layers[tileLayer[i]] = new TreeLeafTile(); 
//			b.layers[tileLayer[i]].currentSpriteId = i;
//			b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
//		}
////		b.layers[TREE_CENTER_0] = new TreeTile();
////		b.setCollisionTile(new TreeTile());
//	}
	
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
		Rect collectibleRect3 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Rect collectibleRect4 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Rect collectibleRect5 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(LOG, Sprites.sprites.get(Sprites.LOG_ITEM), collectibleRect );
		Game.collectibleController.add(LOG, Sprites.sprites.get(Sprites.LOG_ITEM), collectibleRect2 );
		Game.collectibleController.add(LOG, Sprites.sprites.get(Sprites.LOG_ITEM), collectibleRect3 );
		Game.collectibleController.add(LOG, Sprites.sprites.get(Sprites.LOG_ITEM), collectibleRect4 );
		Game.collectibleController.add(LOG, Sprites.sprites.get(Sprites.LOG_ITEM), collectibleRect5 );
	}
}
