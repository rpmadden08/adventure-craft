package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Margin;

public class DirtMountainTopTile extends CollisionTile {
	public DirtMountainTopTile() {
		super();
		currentTexture = 0;
		textures = Textures.dirtMountainTopTextures;
		margin = new Margin(9, 9, 12, 11);
		id = DIRT_MOUNTAIN_TOP;
		layer = ABOVE_LAYER_1;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
	}
	
	public void render(int x, int y, int layer) {
		float z;
//		if(currentTexture == 0 ) {
		Helpers.println(String.valueOf(layer / 100f));	//FIXME!!
		z = this.z + Math.max(layer / 100f, layer / 100f);
//			else z = this.z + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);
//		} else {
//			z = Z_ABOVE_LAYER;
//		}
//		if(y > Game.hero.sRect.y) z = Z_ABOVE_LAYER;// + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);	//FIXME: not an ideal conditional
//		else z = this.z;// + Math.max(Game.level.test.x / 1000f, Game.level.test.y / 1000f);
		textures[currentTexture].draw(x, y, z, TILE_SIZE * Game.pixelModifier, TILE_SIZE * Game.pixelModifier);
	}
	
	public Tile createNew() {
		return new DirtMountainTopTile();
	}
}
