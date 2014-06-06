package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.GameObjects.QueenBee;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class BeehiveTile extends CollisionTile {
	public int growthTime;
	public BeehiveTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.BEEHIVE_TILE);
		margin = new Margin(9, 9, 12, 11);
		id = BEEHIVE_TILE;
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
		return new BeehiveTile();
	}
	
	private int setGrowthTime() {
		return 3000; //Every 1000 is 1 second.  180000 is 3 minutes.
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getGameTime() - timeCreated > growthTime) {// growthTime
//			int x = Game.hero.absRect.x / TILE_SIZE - (Game.level.chunkRect.x * CHUNK_SIZE);
//			int y = Game.hero.absRect.y / TILE_SIZE - (Game.level.chunkRect.y * CHUNK_SIZE);
			Game.mobController.mobs.add(new QueenBee(Game.mobController, x, y));
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new NoTile();
			b.collisionTile = null;
			
//			
//			int[] xs = {x-1, x-1, x-1, x, x, x, x+1,x+1, x+1};
//			int[] ys = {y-2, y-1, y, y-2, y-1, y, y-2, y-1, y};
//			int[] tileLayer = {TREE_LEFT_2, TREE_LEFT_1, TREE_LEFT_0, TREE_CENTER_2, TREE_CENTER_1, TREE_CENTER_0, TREE_RIGHT_2, ABOVE_LAYER_4, TREE_RIGHT_0};
//			
//			for(int i = 0; i < 9; i++) {
//				b = Game.level.activeBlocks[xs[i]][ys[i]];
//				b.layers[tileLayer[i]] = new TreeLeafTile(); 
//				b.layers[tileLayer[i]].currentSpriteId = i;
//				b.layers[tileLayer[i]].z = Z_ABOVE_LAYER;
//			}
		}
	}
}
