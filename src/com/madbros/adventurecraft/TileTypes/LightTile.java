package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.Utils.Margin;

public abstract class LightTile extends CollisionTile {
	
	public LightTile() {
		super();
		id = CAMPFIRE;
		margin = new Margin(0, 0, 0, 0);
		currentSpriteId = 0;
		layer = OBJECT_LAYER;
		z = Z_DARK_DIRT;
		isDiggable = false;
		isChoppable = false;
		timeCreated = Time.getTime();
		isLightSource = true;
	}
	
	@Override
	public abstract LightTile createNew();
}
