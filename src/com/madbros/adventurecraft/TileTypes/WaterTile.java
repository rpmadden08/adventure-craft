package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class WaterTile extends CollisionTile {
	public WaterTile() {
		super();
		currentTexture = 0;
		isBaseTile = true;
		textures = Textures.waterTextures;
		margin = new Margin(1, 1, 0, 0);
		id = WATER;
	}
	
	public Tile createNew() {
		return new WaterTile();
	}
}
