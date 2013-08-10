package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class WaterTile extends CollisionTile {
	public WaterTile() {
		super();
		currentTexture = 0;
		layer = WATER_LAYER;
		z = Z_WATER;
		textures = Textures.waterTextures;
		margin = new Margin(14, 14, 14, 14);
		id = WATER;
		isDiggable = false;
	}
	
	public Tile createNew() {
		return new WaterTile();
	}
}
