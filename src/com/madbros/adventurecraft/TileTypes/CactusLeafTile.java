package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.*;
import com.madbros.adventurecraft.Utils.Margin;

public class CactusLeafTile extends TreeLeafTile {
	public CactusLeafTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CACTUS);

		margin = new Margin(9, 9, 12, 11);
		id = CACTUS_LEAF;
		layer = ABOVE_LAYER_4;
		z = Z_ABOVE_LAYER;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isTreeLeafTile = true;
	}
	
//	@Override
	public void render(int x, int y, int layer) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	@Override
	public void update(int x, int y) {
		if(Game.level.activeBlocks[x][y].layers[OBJECT_LAYER].id == 6) {
			isVisible = false;
		} else {
			isVisible = true;
		}
	}
	
	public CactusLeafTile createNew() {
		return new CactusLeafTile();
	}
}
