package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Rect;

public class GrassTile extends Tile {
	public GrassTile() {
		maxHp = 20;
		currentHp = 20;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.GRASS);
		id = GRASS;
		autoTileID = id;
		isTillable = true;
		is32 = false;
	}

	public Tile createNew() {
		return new GrassTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Game.level.highlightedBlock.layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(GRASS_SEED, Sprites.sprites.get(Sprites.GRASS_ITEM), collectibleRect, 1);
		
	}
}
