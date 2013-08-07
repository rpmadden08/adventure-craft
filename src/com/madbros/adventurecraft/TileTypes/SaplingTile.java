package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;

public class SaplingTile extends CollisionTile {
	
	public SaplingTile() {
		super();
		currentTexture = 0;
		textures = Textures.saplingTexture;
		margin = new Margin(9, 9, 12, 11);
		id = SAPLING;
		layer = OBJECT_LAYER;
		isDiggable = false;
	}
	
	@Override
	public void render(int x, int y) {
		textures[currentTexture].draw(x, y, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new SaplingTile();
	}
	
	@Override
	public void update(int x, int y) {
		if(Time.getTime() - timeCreated > 3000) {
			Game.level.activeBlocks[x][y].layers[OBJECT_LAYER] = new TreeTile();
		}
		
	}
}
