package com.madbros.tileminer.TileTypes;
//NOTES
//With stairs up and stairs down I needed to do a hack.  The object itself is NOT collidable but I'm using collision tile because it DOES need
//the cRect attribute to calculate collisions. It's kind of a hack, but it works as long as the update function sets the collision tile to null...
import static com.madbros.tileminer.Constants.*;

import java.io.File;

import com.madbros.tileminer.*;
import com.madbros.tileminer.GameStates.LoadingState;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class StairsUpTile extends CollisionTile {
	public Boolean hasReset = false;
	public StairsUpTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.STAIRS_UP_TILE);
		margin = new Margin(9, 9, 9, 9);
		id = STAIRS_UP_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = false;
		currentHp = 1;
		maxHp = 1;
		sound = "sounds/pickSound.wav";
		//isCollidable = false;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new StairsUpTile();
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
				//Game.musicController.music.stop();
				Game.hero.isKnockingBack = false; //Makes your knockback stop preventing a game crash...
				Game.hero.stop();
				//Game.saveGame.saveGame();
//				Game.level.saveCurrentChunks();
				Game.saveGame.saveCurrentLevel();
				Game.switchLevel(true);

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
		RectInt b2AbsRect = b.absRect.getRectInt();
		int chunkX = Game.level.getAbsChunkX(b2AbsRect.x);
		int chunkY = Game.level.getAbsChunkY(b2AbsRect.y);
		File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + OVERWORLD_FOLDER);
		if(f.exists()) {
			Game.currentLevel = OVERWORLD_FOLDER;
			Block[][] chunk = Game.saveGame.loadChunk(chunkX, chunkY);
			int tempX = x % CHUNK_SIZE;
			int tempY = y % CHUNK_SIZE;
			if(chunk[tempX][tempY].layers[OBJECT_LAYER].id == STAIRS_DOWN_TILE) {	
			   chunk[tempX][tempY].layers[OBJECT_LAYER].deleteMeStairs(tempX, tempY, chunk);
			   Game.saveGame.saveChunk(chunk, chunkX, chunkY);
			}
			Game.currentLevel = UNDERGROUND_1_FOLDER;
		}
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(STAIRS_UP).createNew();
		Game.collectibleController.add(STAIRS_UP, Sprites.sprites.get(Sprites.STAIRS_UP), collectibleRect, 1, item.maxUses);
		
	}
	
	public void deleteMeStairs(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		b.collisionTile = null;
	}
}
