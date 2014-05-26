package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class Beehive extends BlockItem32 {
	public Beehive() {
		id = BEEHIVE;
		tileId = BEEHIVE_TILE;
		name = "Beehive";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, DARK_GRASS, GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.BEEHIVE);
		maxStackSize = 1;
		numberProducedByCrafting = 1;
		craftCost = new int[]{STICK, GRASS, HONEY};
		craftCostAmount = new int[]{4,4,1};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Beehive();
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
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		}
	}

}
