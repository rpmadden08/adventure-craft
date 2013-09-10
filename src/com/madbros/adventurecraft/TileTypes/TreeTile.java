package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;


public class TreeTile extends CollisionTile {
	
	public TreeTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.treeSprites;
		margin = new Margin(9, 9, 12, 11);	//3, 3, 0, 12

		id = TREE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new TreeTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		int layer;
		Block b;
		
		if(x<1 || y<1 || x > CHUNK_SIZE*CHUNKS_IN_A_ROW-2 || y > CHUNK_SIZE*CHUNKS_IN_A_ROW-2) {
			activeBlocks[x][y].isUnfinished = true;
		} else {
			activeBlocks[x][y].isUnfinished = false;
			int[] xs = {x, x-1, x-1, x, x+1, x+1};
			int[] ys = {y, y, y-1, y-1, y-1, y};
			
			for(int i = 0; i < 6; i++) {
				//System.out.println(xs[i]+" "+ys[i]);
				b = activeBlocks[xs[i]][ys[i]];
				if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR && b.layers[ABOVE_LAYER_5].id != AIR) layer = ABOVE_LAYER_6;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR) layer = ABOVE_LAYER_5;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR) layer = ABOVE_LAYER_4;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR) layer = ABOVE_LAYER_3;
				else if(b.layers[ABOVE_LAYER_1].id != AIR) layer = ABOVE_LAYER_2;
				else layer = ABOVE_LAYER_1;
				b.layers[layer] = new TreeLeafTile(); 
				b.layers[layer].currentSpriteId = i;
				b.layers[layer].z = Z_ABOVE_LAYER;
			}
			
		}
		
	}
}
