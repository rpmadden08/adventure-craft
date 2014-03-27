package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Rect;

import static com.madbros.adventurecraft.Constants.*;

public class SandTile extends Tile {
	public SandTile() {
		maxHp = 10;
		currentHp = 10;
		layer = GRASS_LAYER;
		z = Z_GRASS;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.DESERT);
		id = SAND;
		autoTileID = id;
		is32 = false;
	}
	
	public Tile createNew() {
		return new SandTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Game.level.highlightedBlock.layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].layers[GRASS_LAYER] = new NoTile();
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(SAND_CLUMP, Sprites.sprites.get(Sprites.SAND_ITEM), collectibleRect, 1);
		
	}
}
