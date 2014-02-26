package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class TallGrassBTile extends TallGrassATile {
	
	public TallGrassBTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.TALL_GRASS_B);
		margin = new Margin(9, 9, 12, 11);
		id = TALL_GRASS_A_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = true;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new TallGrassBTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;

		
		
		//FIXME this should drop something other than tall grass
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(TALL_GRASS_A, Sprites.sprites.get(Sprites.TALL_GRASS_A_ITEM), collectibleRect, 1);
		
	}
}
