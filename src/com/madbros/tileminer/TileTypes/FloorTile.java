package com.madbros.tileminer.TileTypes;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Margin;

public class FloorTile extends CollisionTile {
	public Boolean numberVisible = false;
	public FloorTile() {
		super();
		//lightSize = 500;
		id = WOOD_FLOOR_TILE;
		sprites = Sprites.spriteCollections.get(Sprites.WOODEN_FLOOR_TILE);
		autoTile = 0;
		isAutoTileable = false;
		isBreakable = false;
		isChoppable = true;
		currentHp = 10;
		maxHp = 10;
		currentSpriteId = 0;
		margin = new Margin(2, 0, 8, 0);
		layer = GRASS_LAYER;
		sound = "sounds/axeChop.wav";
	}
	
	@Override
	public Tile createNew() {
		return new FloorTile();
	}
	
	@Override
	public void render(int x, int y) {
		sprites[currentSpriteId].draw(x, y, z);
		if(numberVisible == true) {
			Sprites.arial24.draw(Game.batch, ""+housingNumber, x*TILE_SIZE, y*TILE_SIZE);
		}
	}
	
	public void update(int x, int y) {
		if(Item.isCollidingWithActor(Game.level.activeBlocks[x][y], GRASS_LAYER)) {
			Game.level.currentCollisionHousing = this.housingNumber;
		}
		//If a housing item is selected then display the house number on the tile...
		if(Game.inventory.invBar[Game.inventory.itemSelected].item.id == WOODEN_FLOOR) {
			numberVisible = true;
		} else {
			numberVisible = false;
		}
		if(Game.level.currentCollisionHousing == this.housingNumber) {
			Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_1].isTransparent = true;
			Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_2].isTransparent = true;
			Game.level.activeBlocks[x][y].layers[ABOVE_LAYER_3].isTransparent = true;
		} 
	}
	
	public void deleteMe(int x, int y, Block[][] activeBlocks) {
		Block b = activeBlocks[x][y];
		b.layers[GRASS_LAYER] = new NoTile();
		b.layers[GRASS_LAYER].setCollisionRect(b.absRect);
	}
}
