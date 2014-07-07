package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.DirtTile;
import com.madbros.tileminer.TileTypes.NoTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class StairsDown extends BlockItem32 {
	public StairsDown() {
		id = STAIRS_DOWN;
		name = "Stairs Down";
		tileId = STAIRS_DOWN_TILE;
		placeableTileIds = new int[]{HOLE};
		craftCost = new int[]{STONE};
		craftCostAmount = new int[]{10};
		sprite = Sprites.sprites.get(Sprites.STAIRS_DOWN);
		
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new StairsDown();
	}

	@Override
	public void useRight() {
		checkUsability();
		if(Game.currentLevel != UNDERGROUND_1_FOLDER) {
			super.useRight();
		} else {
			Game.notificationController.addAlert("Cannot go down!");
			Game.level.hasPlacedItemOnClick = true;
		}
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[WATER_LAYER] = new NoTile();
		hB.layers[LIGHT_DIRT_LAYER] = new DirtTile();
		hB.layers[OBJECT_LAYER] = tile;
		hB.layers[OBJECT_LAYER].cRect.x = hB.getAbsX()* TILE_SIZE;
		hB.layers[OBJECT_LAYER].cRect.y = hB.getAbsY()* TILE_SIZE;
		//hB.setCollisionTile((CollisionTile)tile);
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

				sprite.setColor(HIGHLIGHT_COLOR);
				sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
				sprite.setColor(Color.WHITE);
			}
	}
}
