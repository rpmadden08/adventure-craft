package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.math.MathUtils;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class SaplingTile extends CollisionTile {
	public int growthTime;
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
		growthTime = setGrowthTime();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new SaplingTile();
	}
	
	private int setGrowthTime() {
//		return 2;
		return MathUtils.random(180000, 300000); //Every 1000 is 1 second.  180000 is 3 minutes.
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getGameTime() - timeCreated > growthTime) {// growthTime
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new TreeTile();
			b.setCollisionTile(new TreeTile());
			int[] spriteId = {0,   1,   2,   3,   4,   6,   7,   8};
			int[] xs = 		 {x-1, x-1, x-1, x,   x,   x+1, x+1, x+1};
			int[] ys = {y-2, y-1, y,   y-2, y-1, y-2, y-1, y};
			int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_RIGHT_2, TREE_RIGHT_1, TREE_RIGHT_0};
			
			for(int i = 0; i < 8; i++) {
				b = Game.level.activeBlocks[xs[i]][ys[i]];
				b.layers[tileLayer[i]] = new TreeLeafTile(); 
				b.layers[tileLayer[i]].currentSpriteId = spriteId[i];
				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
			}
		}
	}
}
