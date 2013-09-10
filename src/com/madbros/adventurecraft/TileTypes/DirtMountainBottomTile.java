package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class DirtMountainBottomTile extends CollisionTile {
	public DirtMountainBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.dirtMountainBottomSprites;
		margin = new Margin(3, 3, 0, 12);
		id = DIRT_MOUNTAIN_BOTTOM;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
	}
	
	public void render(int x, int y, int layer) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new DirtMountainBottomTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		int layer;
		Block b;
		
		if(x<1 || y<1 || x > CHUNK_SIZE*CHUNKS_IN_A_ROW-2 || y > CHUNK_SIZE*CHUNKS_IN_A_ROW-2) {
			activeBlocks[x][y].isUnfinished = true;
		} else {
			activeBlocks[x][y].isUnfinished = false;
			
			b = activeBlocks[x][y-1];
			if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR && b.layers[ABOVE_LAYER_5].id != AIR) layer = ABOVE_LAYER_6;
			else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR) layer = ABOVE_LAYER_5;
			else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR) layer = ABOVE_LAYER_4;
			else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR) layer = ABOVE_LAYER_3;
			else if(b.layers[ABOVE_LAYER_1].id != AIR) layer = ABOVE_LAYER_2;
			else layer = ABOVE_LAYER_1;
			b.layers[layer] = new DirtMountainTopTile(); 
			b.layers[layer].currentSpriteId = 0;
			b.layers[layer].z = Z_ABOVE_LAYER;
			
			
		}
		
	}
}
