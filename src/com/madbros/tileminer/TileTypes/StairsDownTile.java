package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import java.io.File;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameStates.LoadingState;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class StairsDownTile extends CollisionTile {
	public boolean hasReset = false;
	public StairsDownTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.STAIRS_DOWN_TILE);
		margin = new Margin(9, 9, 9, 9);
		id = STAIRS_DOWN_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = false;
		currentHp = 30;
		maxHp = 30;
		//isCollidable = false;
		
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new StairsDownTile();
	}
	
	@Override
	public boolean updateStairs(int x, int y) {
		Game.level.activeBlocks[x][y].collisionTile = null;
		Rect finalCRect = new Rect(cRect, margin);
		if(finalCRect.detectCollision(new Rect(Game.hero.absRect, Game.hero.margin))) {
			if(hasReset == true) {
				Game.currentState = new LoadingState(Game.batch);
				
				Game.replaceableX = Game.level.activeBlocks[x][y].getAbsX();
				Game.replaceableY = Game.level.activeBlocks[x][y].getAbsY();
				//System.out.println(Game.level.activeBlocks[x][y].getAbsY());
				Game.musicController.music.stop();
				Game.hero.knockBackTime = 0; //Makes your knockback stop preventing a game crash...
				Game.hero.stop();
				Game.saveGame.saveGame();
				Game.level.saveCurrentChunks();
				Game.switchLevel();
				//Game.hero = null;
				
				Game.level.teleportHero(Game.replaceableX, Game.replaceableY);
				return false;
			}
			return true;
		} else {
			hasReset = true;
			return true;
		}
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		
		int chunkX = Game.level.getAbsChunkX(b.absRect.x);
		int chunkY = Game.level.getAbsChunkY(b.absRect.y);
		
		//SOMEDAY WILL NEED TO FETCH THE LEVEL BELOW BUT FOR NOW IT WILL ALWAYS BE UNDERGROUND_1
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + UNDERGROUND_1_FOLDER);
		if(f.exists()) {
			Game.currentLevel = UNDERGROUND_1_FOLDER;
			Block[][] chunk = Game.saveGame.loadChunk(chunkX, chunkY);
			int tempX = x % CHUNK_SIZE;
			int tempY = y % CHUNK_SIZE;
			if(chunk[tempX][tempY].layers[OBJECT_LAYER].id == STAIRS_UP_TILE) {	
			   chunk[tempX][tempY].layers[OBJECT_LAYER].deleteMeStairs(tempX, tempY, chunk);
			   Game.saveGame.saveChunk(chunk, chunkX, chunkY);
			}
			Game.currentLevel = OVERWORLD_FOLDER;
		}
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(STAIRS_DOWN).createNew();
		Game.collectibleController.add(STAIRS_DOWN, Sprites.sprites.get(Sprites.STAIRS_DOWN), collectibleRect, 1, item.maxUses);
	} 
	
	public void deleteMeStairs(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
	}
}
