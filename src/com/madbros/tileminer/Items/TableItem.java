package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class TableItem extends BlockItem32 {
	public TableItem() {
		id = TABLE;
		tileId = TABLE_TILE;
		name = "Wooden Table";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, DARK_GRASS, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.TABLE_ITEM);
		maxStackSize = 1;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{4};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new TableItem();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
			sprite.setColor(Color.WHITE);
		}
	}

}
