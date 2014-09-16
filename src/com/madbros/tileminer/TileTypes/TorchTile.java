package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

public class TorchTile extends LightTile {

	public TorchTile() {
		super();
		lightSize = 500;
		id = TORCH_TILE;
		sprites = Sprites.torchAnimation;
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = true;
		currentHp = 1;
		maxHp = 1;
		margin = new Margin(11, 12, 1, 0);
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
		b.collisionTile = null;
		b.layers[OBJECT_LAYER].isLightSource = false;
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(TORCH).createNew();
		Game.collectibleController.add(TORCH, Sprites.sprites.get(Sprites.TORCH), collectibleRect, 1, item.maxUses);
	}
}
