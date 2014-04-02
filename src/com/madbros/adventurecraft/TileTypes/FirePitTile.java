package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class FirePitTile extends CollisionTile {

	public FirePitTile() {
		super();
		//lightSize = 500;
		id = FIRE_PIT_TILE;
		sprites = Sprites.spriteCollections.get(Sprites.FIRE_PIT_TILE);
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		currentSpriteId = 0;
		margin = new Margin(9, 9, 12, 11);
		layer = OBJECT_LAYER;
	}
	
	@Override
	public Tile createNew() {
		return new FirePitTile();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(FIRE_PIT, Sprites.sprites.get(Sprites.FIRE_PIT), collectibleRect, 1);
	}
}
