package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Time;
import com.madbros.tileminer.Utils.Margin;

public abstract class LightTile extends CollisionTile {
	public float lightSize = 0;
	
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
