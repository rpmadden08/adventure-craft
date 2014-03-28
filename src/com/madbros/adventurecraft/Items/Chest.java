package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class Chest extends BlockItem32 {
	public Chest() {
		id = CHEST;
		name = "Chest";
		tileId = CHEST_TILE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS,DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.CHEST_ITEM);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{8};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Chest();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + Game.level.tileBeingAttacked.absX + "-" + Game.level.tileBeingAttacked.absY + ".sv");
		if(f.exists()) { 
			f.delete();
		}
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