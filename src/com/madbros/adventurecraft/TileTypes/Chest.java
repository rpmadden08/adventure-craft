package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;

public class Chest extends CollisionTile {
	
	public Chest() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CHEST);
		margin = new Margin(0, 0, 0, 0);
		id = CHEST;
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
			int x = absX;
			int y = absY;
			Game.inventory.chestOn = true;
			Game.inventory.currentInvBlockX = x;
			Game.inventory.currentInvBlockY = y;
			File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
			if(f.exists()) {
				Game.level.saveGame.loadChest(x, y);
			}
			Game.toggleInventoryState();
			
		}
	}
	
	public Tile createNew() {
		return new Chest();
	}
}
