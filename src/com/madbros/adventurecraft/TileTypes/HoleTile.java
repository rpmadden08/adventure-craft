package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class HoleTile extends CollisionTile {
	public HoleTile() {
		super();
		currentTexture = 0;
		layer = WATER_LAYER;
		z = Z_WATER;
		textures = Textures.holeTextures;
		margin = new Margin(14, 14, 14, 14);
		id = HOLE;
		isDiggable = false;
	}
	
	public Tile createNew() {
		return new HoleTile();
	}
}
