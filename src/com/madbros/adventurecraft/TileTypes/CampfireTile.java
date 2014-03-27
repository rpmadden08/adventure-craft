package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class CampfireTile extends LightTile {

	public CampfireTile() {
		super();
		lightSize = 500;
		id = CAMPFIRE;
		sprites = Sprites.campfireAnimation;
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
	}
	
	@Override
	public LightTile createNew() {
		return new CampfireTile();
	}
	
	public void update(int x,int y) {
		System.out.println(cRect.x);
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z);
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(CAMPFIRE, Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE), collectibleRect, 1);
	}
}
