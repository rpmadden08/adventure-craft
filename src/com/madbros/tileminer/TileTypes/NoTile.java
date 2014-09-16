package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

public class NoTile extends CollisionTile {
	public NoTile() {
		super();
		currentSpriteId = 0;

		layer = DARK_DIRT_LAYER;
		z = Z_DARK_DIRT;
		isCollidable = false;
		isDiggable = false;
		id = AIR;
		isAutoTileable = false;
		//setCollisionRect(absRect);
	}
	
	@Override
	public void render(int x, int y) {}
	
	@Override
	public NoTile createNew() {
		return new NoTile();
	}

}
