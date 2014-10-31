package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.math.MathUtils;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class CactusSaplingTile extends CollisionTile {
	public int growthTime;
	public CactusSaplingTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CACTUS_SAPLING);
		margin = new Margin(0, 1, 1, 1);
		id = CACTUS_SAPLING;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isBreakable = true;
		isAutoTileable = false;
		autoTile = 0;
		growthTime = setGrowthTime();
		maxHp = 1;
		currentHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new CactusSaplingTile();
	}
	
	private int setGrowthTime() {
		return MathUtils.random(180000, 300000); //Every 1000 is 1 second.  180000 is 3 minutes.
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getGameTime() - timeCreated > growthTime) {// 10000
			Block b = Game.level.activeBlocks[x][y];
			b.layers[OBJECT_LAYER] = new CactusTile();
			b.setCollisionTile(new CactusTile());
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
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		b.collisionTile = null;
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CACTUS_SEED).createNew();
		Game.collectibleController.add(CACTUS_SEED, Sprites.sprites.get(Sprites.CACTUS_ITEM), collectibleRect, 1,item.maxUses);
	}
}
