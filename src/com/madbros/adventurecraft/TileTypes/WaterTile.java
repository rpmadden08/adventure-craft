package com.madbros.adventurecraft.TileTypes;

import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;

import static com.madbros.adventurecraft.Constants.*;

public class WaterTile extends CollisionTile {
	public WaterTile() {
		super();
		currentSpriteId = 0;
		layer = WATER_LAYER;
		z = Z_WATER;
		margin = new Margin(14, 14, 14, 14);
		id = WATER;
		isDiggable = false;
		sprites = Sprites.waterSprites;
	}
	
	public Tile createNew() {
		return new WaterTile();
	}
}
