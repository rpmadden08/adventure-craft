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
		z = Z_OBJECT;
		isDiggable = false;
	}
	
	@Override
	public void render(int x, int y) {
		textures[currentTexture].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
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
			
			int layer;
			Block b;
			int[] xs = {x, x-1, x-1, x, x+1, x+1};
			int[] ys = {y, y, y-1, y-1, y-1, y};
			
			for(int i = 0; i < 6; i++) {
				System.out.println(xs[i]+" "+ys[i]);
				b = Game.level.activeBlocks[xs[i]][ys[i]];
				if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR && b.layers[ABOVE_LAYER_5].id != AIR) layer = ABOVE_LAYER_6;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR && b.layers[ABOVE_LAYER_4].id != AIR) layer = ABOVE_LAYER_5;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR && b.layers[ABOVE_LAYER_3].id != AIR) layer = ABOVE_LAYER_4;
				else if(b.layers[ABOVE_LAYER_1].id != AIR && b.layers[ABOVE_LAYER_2].id != AIR) layer = ABOVE_LAYER_3;
				else if(b.layers[ABOVE_LAYER_1].id != AIR) layer = ABOVE_LAYER_2;
				else layer = ABOVE_LAYER_1;
				b.layers[layer] = new TreeLeafTile(); 
				b.layers[layer].currentTexture = i;
				b.layers[layer].z = Z_ABOVE_LAYER;
			}
		}
	}
}
