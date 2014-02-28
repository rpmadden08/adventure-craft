package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class TorchTile extends LightTile {

	public TorchTile() {
		super();
		lightSize = 500;
		id = TORCH;
		sprites = Sprites.torchAnimation;
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
	}
	@Override
	public LightTile createNew() {
		return new TorchTile();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[0].draw(x, y, z);
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Game.collectibleController.add(TORCH, Sprites.sprites.get(Sprites.TORCH), collectibleRect, 1);
	}
}
