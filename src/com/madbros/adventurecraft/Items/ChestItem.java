package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class ChestItem extends BlockItem32 {
	public ChestItem() {
		id = CHEST_ITEM;
		tileId = CHEST;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.CHEST_ITEM);
		maxStackSize = 99;
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK, PLANK, PLANK, PLANK, PLANK, PLANK, PLANK, PLANK};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new ChestItem();
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		return Helpers.containsXNumberOfItemsInSlots(4, PLANK, craftingSlots);
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		File f = new File(Game.locOfSavedGame + CHESTS_FOLDER + Game.level.tileBeingAttacked.absX + "-" + Game.level.tileBeingAttacked.absY + ".sv");
		if(f.exists()) { 
			try {
				FileUtils.deleteDirectory(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

}
