package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class Table extends CollisionTile {
	
	public Table() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.TABLE);
		margin = new Margin(9, 9, 0, 11);
		id = TABLE;
		layer = OBJECT_LAYER;
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
	
	@Override
	public void rightClicked() {
		
		if(Game.currentState.type == State.MAIN) {
			Game.inventory.craftingTableOn = true;
			Game.toggleInventoryState();
		}
	}
	
	public Tile createNew() {
		return new Table();
	}
}
