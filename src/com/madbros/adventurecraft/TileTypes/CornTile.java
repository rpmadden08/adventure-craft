package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class CornTile extends CollisionTile {
	private int growthTime;
	
	public CornTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CORN_TILE);
		margin = new Margin(9, 9, 12, 11);
		id = CORN_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		growthTime = setGrowthTime();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new CornTile();
	}
	
	private int setGrowthTime() {
		
		//return MathUtils.random(180000, 300000); //Every 1000 is 1 second.  180000 is 3 minutes.
		return MathUtils.random(3000, 7000); //Every 1000 is 1 second.  180000 is 3 minutes.
		
	}
	
	@Override
	public void update(int x, int y) {
		if(currentSpriteId != 6) {
			int growthDivided = growthTime /3;
			if (Time.getGameTime() - timeCreated > growthDivided*3 && currentSpriteId < 6) {
				currentSpriteId = 6;
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new CornTopTile();
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentSpriteId = 5;
			} else if (Time.getGameTime() - timeCreated > growthDivided*2 && currentSpriteId < 4) {
				currentSpriteId = 4;
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new CornTopTile();
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentSpriteId = 3;
			} else if (Time.getGameTime() - timeCreated > growthDivided && currentSpriteId < 2) {
				currentSpriteId = 2;
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new CornTopTile();
				Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentSpriteId = 1;
			} else {
				
			}
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new NoTile();
		b.collisionTile = null;
		Random rnd = new Random();
		int dropAmount1 = rnd.nextInt(2)+1;
		int dropAmount2 = rnd.nextInt(1)+1;
		if(currentSpriteId == 3) {
			for(int i = dropAmount1; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Game.collectibleController.add(CORN_SPROUT, Sprites.sprites.get(Sprites.CORN_SPROUT), collectibleRect, 1);
			}
			for(int i = dropAmount2; i >-1 ; i--) {
				Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				Game.collectibleController.add(CORN, Sprites.sprites.get(Sprites.CORN), collectibleRect, 1);
			}
		} else {
			Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
			Game.collectibleController.add(CORN_SPROUT, Sprites.sprites.get(Sprites.CORN_SPROUT), collectibleRect, 1);
		}
	}
}
