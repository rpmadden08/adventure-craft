package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class BarrelTopTile extends CollisionTile {
	
	public BarrelTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.BARREL_TOP);
		margin = new Margin(9, 9, 0, 11);
		id = BARREL_TOP;
		layer = ABOVE_LAYER_1;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new BarrelTopTile();
	}
}
