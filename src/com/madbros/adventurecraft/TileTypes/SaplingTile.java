package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class SaplingTile extends CollisionTile {
	
	public SaplingTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.SAPLING_COLLECTION);
		margin = new Margin(9, 9, 12, 11);
		id = SAPLING;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new SaplingTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getTime() - timeCreated > 10000) {// 3000
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new TreeTile();
			b.setCollisionTile(new TreeTile());
			int[] xs = {x-1, x-1, x-1, x, x, x, x+1,x+1, x+1};
			int[] ys = {y-2, y-1, y, y-2, y-1, y, y-2, y-1, y};
			int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
			
			for(int i = 0; i < 9; i++) {
				b = Game.level.activeBlocks[xs[i]][ys[i]];
				b.layers[tileLayer[i]] = new TreeLeafTile(); 
				b.layers[tileLayer[i]].currentSpriteId = i;
				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
			}
		}
	}
}
