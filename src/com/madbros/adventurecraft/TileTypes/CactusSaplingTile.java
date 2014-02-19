package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class CactusSaplingTile extends CollisionTile {
	
	public CactusSaplingTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CACTUS_SAPLING);
		margin = new Margin(9, 9, 12, 11);
		id = CACTUS_SAPLING;
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
		return new CactusSaplingTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getGameTime() - timeCreated > 300) {// 10000
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new Cactus();
			b.setCollisionTile(new Cactus());
			int[] xs = {x-1, x-1, x, x, x+1, x+1};
			int[] ys = {y-1, y, y-1, y, y-1, y};
			int[] tileLayer = {TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_1, TREE_RIGHT_0};
			
			for(int i = 0; i < 6; i++) {
				b = Game.level.activeBlocks[xs[i]][ys[i]];
				b.layers[tileLayer[i]] = new CactusLeafTile(); 
				b.layers[tileLayer[i]].currentSpriteId = i;
				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
			}
		}
	}
}
