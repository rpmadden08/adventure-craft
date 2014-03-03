package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class CornTopTile extends CollisionTile {
	
	public CornTopTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CORN_TILE);
		margin = new Margin(9, 9, 0, 11);
		id = CORN_TOP;
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
		return new CornTopTile();
	}
}
