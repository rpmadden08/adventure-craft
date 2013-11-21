package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;


public class TreeRainTile extends CollisionTile {
	
	public TreeRainTile() {
		super();
		currentSpriteId = 6;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_THREE);
		margin = new Margin(9, 9, 12, 11);	//3, 3, 0, 12

		id = TREE;
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
		return new TreeRainTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.setCollisionTile(new TreeLeafRainTile());
		int[] xs = {x-1, x-1, x-1, x, x, x, x, x+1,x+1, x+1};
		int[] ys = {y-3, y-2, y-1, y-3, y-2, y-1, y, y-3, y-2, y-1};
		int[] tileLayer = {TREE_LEFT_3, TREE_LEFT_2, TREE_LEFT_1, TREE_CENTER_3,TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
		
		for(int i = 0; i < 10; i++) {
			b = activeBlocks[xs[i]][ys[i]];
			b.layers[tileLayer[i]] = new TreeLeafRainTile(); 
			b.layers[tileLayer[i]].currentSpriteId = i;
			b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
		}
//		Block b;
//		
//		if(x<1 || y<1 || x > CHUNK_SIZE*CHUNKS_IN_A_ROW-2 || y > CHUNK_SIZE*CHUNKS_IN_A_ROW-2) {
//			activeBlocks[x][y].isUnfinished = true;
//		} else {
//			activeBlocks[x][y].isUnfinished = false;
//			int[] xs = {x-1, x, x+1,x-1, x, x+1,x-1, x, x+1,x-1, x, x+1};
//			int[] ys = {y,   y, y  , y-1, y-1, y-1, y-2,y-2,y-2,y-3,y-3,y-3};
//			int[] tileLayer = {OBJECT_LAYER, OBJECT_LAYER, OBJECT_LAYER, TREE_LEFT_1, ABOVE_LAYER_1, TREE_RIGHT_1, TREE_LEFT_2, ABOVE_LAYER_2, TREE_RIGHT_2, TREE_LEFT_3, ABOVE_LAYER_3, TREE_RIGHT_3};
//			
//			for(int i = 11; i > 3; i--) {
//				b = activeBlocks[xs[i]][ys[i]];
//				b.layers[tileLayer[i]] = new TreeLeafTile(); 
//				b.layers[tileLayer[i]].currentSpriteId = i;
//				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
//			}
//		}
	}
}
