package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.FurnaceTile;
import com.madbros.tileminer.TileTypes.FurnaceTopTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class Furnace extends BlockItem32 {
	public Furnace() {
		id = FURNACE;
		name = "Furnace";
		tileId = FURNACE_TILE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, DARK_GRASS, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.FURNACE_SINGLE);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{STONE};
		craftCostAmount = new int[]{8};
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Furnace();
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
		int x = hB.getX(Game.level.activeBlocks);
		int y = hB.getY(Game.level.activeBlocks);
		Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new FurnaceTopTile();
		
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {
			FurnaceTile tile1 = new FurnaceTile();
			tile1.sprites = Sprites.furnaceStatic;
			tile1.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile1.sprites[0].draw(x, y, Z_CHARACTER);
			tile1.sprites[0].setColor(Color.WHITE);
			
			FurnaceTopTile tile2 = new FurnaceTopTile();
			tile2.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile2.sprites[0].draw(x, y-32, Z_CHARACTER);
			tile2.sprites[0].setColor(Color.WHITE);
			
			
		}else {
			super.highlightItem(block, x, y);
		}
	}

}
