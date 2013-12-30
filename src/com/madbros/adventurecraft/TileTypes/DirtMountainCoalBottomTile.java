package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class DirtMountainCoalBottomTile extends CollisionTile {
	public DirtMountainCoalBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.MOUNTAIN_COAL_BOTTOM);
		margin = new Margin(3, 3, 0, 12);
		id = DIRT_MOUNTAIN_COAL_BOTTOM;
		autoTileID = DIRT_MOUNTAIN_BOTTOM;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isPickable = true;
	}
	
	public void render(int x, int y, int layer) {
		sprites[currentSpriteId].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new DirtMountainCoalBottomTile();
	}
	
	@Override
	public void bloom(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y-2];
		//b = activeBlocks[xs[i]][ys[i]];
		b.layers[ABOVE_LAYER_2] = new DirtMountainTopTile(); 
		b.layers[ABOVE_LAYER_2].currentSpriteId = 0;
		b.layers[ABOVE_LAYER_2].z = Z_ABOVE_LAYER;
	}
}
