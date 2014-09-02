package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class Floor extends BlockItem32 {
	public Floor() {
		id = WOODEN_FLOOR;
		tileId = WOOD_FLOOR_TILE;
		name = "Wood Flooring";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.WOODEN_FLOOR);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{2};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Floor();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return false;
		//return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = tile;
		Game.level.autoTileHighlightedBlock();
		hB.layers[GRASS_LAYER].housingNumber = Game.level.currentHousing;
		hB.layers[GRASS_LAYER].cRect.x = hB.getAbsX()* TILE_SIZE;
		hB.layers[GRASS_LAYER].cRect.y = hB.getAbsY()* TILE_SIZE;
	}
	
	public void highlightItem(Block block, int x, int y) {
		String houseNumber = "House #" +Game.level.currentHousing;
		String UIText = "(-/+)";
		Sprites.font.draw(Game.batch, houseNumber, 4, 108);
		Sprites.font.draw(Game.batch, UIText, 18, 108-Sprites.font.getLineHeight());
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id)) {
			//Tile tile = TILE_HASH.get(tileId);
			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		} else {
			super.highlightItem(block, x, y);
		}
}
	

}
