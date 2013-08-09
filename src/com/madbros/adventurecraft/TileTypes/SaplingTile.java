package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

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
		if(Time.getTime() - timeCreated > 30) {// 3000
			Game.level.activeBlocks[x][y].layers[OBJECT_LAYER] = new TreeTile();
			Game.level.activeBlocks[x][y].collisionTile = new TreeTile();
			Game.level.activeBlocks[x][y].cRect = new Rect(Game.level.activeBlocks[x][y].aRect, Game.level.activeBlocks[x][y].collisionTile.margin);
			
			Game.level.activeBlocks[x-1][y].layers[ABOVE_LAYER_1] = new TreeTile(); 
			Game.level.activeBlocks[x-1][y].layers[ABOVE_LAYER_1].currentTexture = 1;
			
			Game.level.activeBlocks[x-1][y-1].layers[ABOVE_LAYER_1] = new TreeTile(); 
			Game.level.activeBlocks[x-1][y-1].layers[ABOVE_LAYER_1].currentTexture = 2;
			
			Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new TreeTile(); 
			Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1].currentTexture = 3;
			
			Game.level.activeBlocks[x+1][y-1].layers[ABOVE_LAYER_1] = new TreeTile(); 
			Game.level.activeBlocks[x+1][y-1].layers[ABOVE_LAYER_1].currentTexture = 4;
			
			Game.level.activeBlocks[x+1][y].layers[ABOVE_LAYER_1] = new TreeTile(); 
			Game.level.activeBlocks[x+1][y].layers[ABOVE_LAYER_1].currentTexture = 5;
		}
		
	}
}
