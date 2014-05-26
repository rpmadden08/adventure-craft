package com.madbros.adventurecraft.TileTypes;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class ChestTile extends CollisionTile {
	
	public ChestTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.CHEST);
		margin = new Margin(0, 0, 0, 0);
		id = CHEST_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = true;
		isUseable = true;
		currentHp = 1;
		maxHp = 1;
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
	}
	
	@Override
	public void rightClicked() {
		if(Game.currentState.type == State.MAIN && Game.level.hasPlacedItemOnClick == false) {
			int x = absX;
			int y = absY;
			Game.inventory.chestOn = true;
			Game.inventory.currentInvBlockX = x;
			Game.inventory.currentInvBlockY = y;
//			System.out.println(x);
//			System.out.println(y);
			File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
			if(f.exists()) {
				Game.saveGame.loadChest(x, y);
			}
			Game.toggleChestState();
			
		}
	}
	
	public Tile createNew() {
		return new ChestTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.collisionTile = null;
		File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + x + "-" + y + ".sv");
		if(f.exists()) {
			Game.saveGame.loadChest(x, y);
		f.delete();
		}
		
		
		Rect collectibleRect = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
		Item item = ITEM_HASH.get(CHEST).createNew();
		Game.collectibleController.add(CHEST, Sprites.sprites.get(Sprites.CHEST_ITEM), collectibleRect, 1, item.maxUses);
		
		for(int i = 0; i < Game.inventory.invChest.length; i++) {
			if(Game.inventory.invChest[i].item.id != 0) {
				Rect collectibleRect2 = new Rect(activeBlocks[x][y].absRect.x, activeBlocks[x][y].absRect.y, 32, 32);
				item = ITEM_HASH.get(Game.inventory.invChest[i].item.id).createNew();
				Game.collectibleController.add(Game.inventory.invChest[i].item.id, Game.inventory.invChest[i].item.sprite, collectibleRect2, Game.inventory.invChest[i].item.stackSize, item.maxUses);
			}
		}
		
	}
}
