package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Margin;

public class StairsDown extends BlockItem32 {
	public StairsDown() {
		id = STAIRS_DOWN;
		name = "Stairs Down";
		tileId = STAIRS_DOWN_TILE;
		placeableTileIds = new int[]{DIRT};
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
		if(Game.currentLevel != UNDERGROUND_1_FOLDER) {
			super.useRight();
		} else {
			Game.notificationController.addAlert("Cannot go down!");
			Game.level.hasPlacedItemOnClick = true;
		}
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
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
