package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class PepperTile extends CollisionTile {
	private int growthTime;
	
	public PepperTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.PEPPER_TILE);
		margin = new Margin(9, 9, 12, 11);
		id = PEPPER_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		growthTime = setGrowthTime();
		maxHp = 1;
		currentHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new PepperTile();
	}
	
	private int setGrowthTime() {
		
		//return MathUtils.random(180000, 300000); //Every 1000 is 1 second.  180000 is 3 minutes.
		return MathUtils.random(1000, 2000); //Every 1000 is 1 second.  180000 is 3 minutes.
		
	}
	
	@Override
	public void update(int x, int y) {
		if(currentSpriteId != 5) {
			int growthDivided = growthTime /3;
			if (Time.getGameTime() - timeCreated > growthDivided*3 && currentSpriteId < 5) {
				currentSpriteId = 5;
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new PepperTopTile();
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentSpriteId = 4;
			} else if (Time.getGameTime() - timeCreated > growthDivided*2 && currentSpriteId < 3) {
				currentSpriteId = 3;
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new PepperTopTile();
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentSpriteId = 2;
			} else if (Time.getGameTime() - timeCreated > growthDivided && currentSpriteId < 1) {
				currentSpriteId = 1;
			} else {
				
			}
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new NoTile();
		b.collisionTile = null;
		Random rnd = new Random();
		int dropAmount1 = rnd.nextInt(2)+1;
		int dropAmount2 = rnd.nextInt(1)+1;
		if(currentSpriteId == 5) {
			for(int i = dropAmount1; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Item item = ITEM_HASH.get(PEPPER_SPROUT).createNew();
				Game.collectibleController.add(PEPPER_SPROUT, Sprites.sprites.get(Sprites.PEPPER_SPROUT), collectibleRect, 1, item.maxUses);
			}
			for(int i = dropAmount2; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Item item = ITEM_HASH.get(PEPPER).createNew();
				Game.collectibleController.add(PEPPER, Sprites.sprites.get(Sprites.PEPPER), collectibleRect, 1, item.maxUses);
			}
		} else {
			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Item item = ITEM_HASH.get(PEPPER_SPROUT).createNew();
			Game.collectibleController.add(PEPPER_SPROUT, Sprites.sprites.get(Sprites.PEPPER_SPROUT), collectibleRect, 1, item.maxUses);
		}
	}
}
