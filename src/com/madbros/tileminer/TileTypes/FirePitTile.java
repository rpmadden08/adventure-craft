package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;
import com.madbros.tileminer.Utils.Rect;

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
		margin = new Margin(2, 0, 8, 0);
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
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(FIRE_PIT).createNew();
		Game.collectibleController.add(FIRE_PIT, Sprites.sprites.get(Sprites.FIRE_PIT), collectibleRect, 1, item.maxUses);
	}
}
