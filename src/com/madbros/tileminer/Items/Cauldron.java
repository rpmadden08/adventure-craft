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

public class Cauldron extends BlockItem32 {
	public Cauldron() {
		id = CAULDRON;
		tileId = CAULDRON_TILE;
		name = "Cauldron";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.CAULDRON_SINGLE);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{5};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Cauldron();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return false;
		//return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
//		int x = hB.getX(Game.level.activeBlocks);
//		int y = hB.getY(Game.level.activeBlocks);
//		Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new FurnaceTop();
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {
			//Tile tile = TILE_HASH.get(tileId);
			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
			sprite.setColor(Color.WHITE);
		}
}
	

}
