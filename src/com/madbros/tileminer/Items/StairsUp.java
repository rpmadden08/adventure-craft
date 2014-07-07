package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class StairsUp extends BlockItem32 {
	public StairsUp() {
		id = STAIRS_UP;
		name = "Stairs Up";
		tileId = STAIRS_UP_TILE;
		placeableTileIds = new int[]{DIRT};
		craftCost = new int[]{STONE};
		craftCostAmount = new int[]{10};
		sprite = Sprites.sprites.get(Sprites.STAIRS_UP);
	}
	
	@Override
	public BlockItem32 createNew() {
		return new StairsUp();
	}
	
	public void useRight() {
		checkUsability();
		if(Game.currentLevel != OVERWORLD_FOLDER) {
			super.useRight();
		} else {
			Game.notificationController.addAlert("Cannot go up!");
			Game.level.hasPlacedItemOnClick = true;
		}
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
			hB.layers[OBJECT_LAYER] = tile;
			//hB.setCollisionTile((CollisionTile)tile);
			Game.level.hasPlacedItemOnClick = true;
			hB.layers[OBJECT_LAYER].cRect.x = hB.getAbsX()* TILE_SIZE;
			hB.layers[OBJECT_LAYER].cRect.y = hB.getAbsY()* TILE_SIZE;

	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

				sprite.setColor(HIGHLIGHT_COLOR);
				sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
				sprite.setColor(Color.WHITE);
			}else {
				super.highlightItem(block, x, y);
			}
	}
}
