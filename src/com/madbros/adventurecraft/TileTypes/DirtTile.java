package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Rect;

import static com.madbros.adventurecraft.Constants.*;

public class DirtTile extends Tile {
	public DirtTile() {
		maxHp = 20;
		currentHp = 20;
		layer = LIGHT_DIRT_LAYER;
		z = Z_LIGHT_DIRT;
		isCollidable = false;
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.LIGHT_DIRT);
		id = DIRT;
		autoTileID = id;
		isTillable = true;
	}
	
	public Tile createNew() {
		return new DirtTile();
	}
	
	public void DeleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(EARTH_CLUMP, Sprites.sprites.get(Sprites.DIRT_ITEM), collectibleRect, 1);
		
	}
}
