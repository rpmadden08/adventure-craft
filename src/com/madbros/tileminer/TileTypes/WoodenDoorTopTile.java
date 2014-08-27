package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class WoodenDoorTopTile extends CollisionTile {
	
	public WoodenDoorTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_TOP_TILE);
		margin = new Margin(9, 9, 0, 11);
		id = WOOD_DOOR_TOP_TILE;
		layer = ABOVE_LAYER_1;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
		autoTileID = WOOD_WALL_MIDDLE_TILE;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public Tile createNew() {
		return new WoodenDoorTopTile();
	}
}
