package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;


public class TreePineTile extends CollisionTile {
	
	public TreePineTile() {
		super();
		currentSpriteId = 5;
		sprites = Sprites.spriteCollections.get(Sprites.TREE_FOUR);
		margin = new Margin(9, 9, 12, 11);	//3, 3, 0, 12

		id = TREE_PINE;
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
		return new TreePineTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		//b.layers[OBJECT_LAYER] = new TreeTile();
		//b.setCollisionTile(new TreeTile());
		int[] xs = {x-1, x-1, x-1, x, x, x, x+1,x+1, x+1};
		int[] ys = {y-2, y-1, y, y-2, y-1, y, y-2, y-1, y};
		int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
		
		for(int i = 0; i < 9; i++) {
			b = activeBlocks[xs[i]][ys[i]];
			b.layers[tileLayer[i]] = new TreeLeafPineTile(); 
			b.layers[tileLayer[i]].currentSpriteId = i;
			b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
		}

	}
}
