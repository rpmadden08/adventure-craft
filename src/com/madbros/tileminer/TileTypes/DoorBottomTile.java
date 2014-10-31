package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.Constants.State;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprite;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class DoorBottomTile extends CollisionTile {
	Boolean isOpen = false;
	Sprite[] sprites1 = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_BOTTOM_TILE);
	Sprite[] sprites2 = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_BOTTOM_OPEN_TILE);
	Sprite[] sprites3 = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_TOP_TILE);
	Sprite[] sprites4 = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_TOP_OPEN_TILE);
	public DoorBottomTile() {
		super();
		currentSpriteId = 0;
		sprites = Sprites.spriteCollections.get(Sprites.WOODEN_DOOR_BOTTOM_TILE);
		margin = new Margin(0, 0, 0, 0);
		id = WOOD_DOOR_BOTTOM_TILE;
		layer = OBJECT_LAYER;
		z = Z_OBJECT;
		isDiggable = false;
		isAutoTileable = false;
		autoTile = 0;
		isBreakable = false;
		currentHp = 15;
		maxHp = 15;
		isUseable = true;
		autoTileID = WOOD_WALL_BOTTOM_TILE;
		
	}
	
	@Override
	public void render(int x, int y) {
		if(isTransparent) {
			sprites[currentSpriteId].setColor(1,1,1,0.3f);
			sprites[currentSpriteId].draw(x, y, z);
		} else {
			sprites[currentSpriteId].setColor(1,1,1,1);
			sprites[currentSpriteId].draw(x, y, z);
		}
	}
	
	public void toggleOpenClose() {
		
		if(isOpen == false) {
			isOpen = true;
			sprites = sprites2;
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].layers[ABOVE_LAYER_1].sprites = sprites4;
					 
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY].collisionTile = null;
			isCollidable = false;
			Game.soundController.create("sounds/doorOpen.wav", 1f);
			
		} else {
			isOpen = false;
			sprites = sprites1;
			isCollidable = true;
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].layers[ABOVE_LAYER_1].sprites = sprites3;
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY].collisionTile = this;
			Game.soundController.create("sounds/doorClose.wav", 1f);
			//isOpen 
		}
	}
	
	public void rightClicked() {
		if(Game.currentState.type == State.MAIN && Game.level.hasPlacedItemOnClick == false) {
			if(!Item.isCollidingWithActor(Game.level.highlightedBlock, OBJECT_LAYER)) {
				toggleOpenClose();			
				Game.level.hasPlacedItemOnClick = true;
			}
		}
	}
	
	public Tile createNew() {
		return new DoorBottomTile();
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[OBJECT_LAYER] = new NoTile();
		b.layers[OBJECT_LAYER].setCollisionRect(b.absRect);
		b.collisionTile = null;
		b = activeBlocks[x][y-1];
		b.layers[ABOVE_LAYER_1] = new NoTile();
		b = activeBlocks[x][y-2];
		b.layers[ABOVE_LAYER_2] = new NoTile();
		b = activeBlocks[x][y-3];
		b.layers[ABOVE_LAYER_3] = new NoTile();
		Game.level.autoTileBlock(x, y);
		Game.level.autoTileBlock(x, y-1);
		Game.level.autoTileBlock(x, y-2);
		Game.level.autoTileBlock(x, y-3);
	}
}
