package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		name = "Dirt";
		tileId = DIRT;
		sprite = Sprites.sprites.get(Sprites.DIRT_ITEM);
		placeableTileIds = new int[]{WATER, SAND, DARK_DIRT, HOLE};
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}
	
	@Override
	public void useRight() {
		Tile tile = TILE_HASH.get(tileId).createNew();
		Block hB = Game.level.highlightedBlock;
		
		if(hB.canPlace == false) {
			if(Time.getTime() - hB.timePlaced > 300) {
				hB.canPlace = true;
			}
		}
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && hB.canPlace == true) {
			placeTile(hB, tile);
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		if(hB.layers[WATER_LAYER].id == HOLE || hB.layers[WATER_LAYER].id == WATER) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j< 2; j++) {
					placeDarkDirt(Game.level.activeBlocks[Game.level.highlightedBlockX+i][Game.level.highlightedBlockY+j]);
				}
			}
		} else if(hB.canPlace == true) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j< 2; j++) {
					placeLightDirt(Game.level.activeBlocks[Game.level.highlightedBlockX+i][Game.level.highlightedBlockY+j], tile);
				}
			}
		}
	}
	
	public void placeDarkDirt(Block hB) {
		hB.canPlace = false;
		hB.timePlaced = Time.getTime();
		hB.layers[GRASS_LAYER] = new NoTile();
		hB.layers[WATER_LAYER] = new NoTile();

		hB.collisionTile = null;
	}
	
	public void placeLightDirt(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = new NoTile();
		hB.layers[WATER_LAYER] = new NoTile();
		hB.layers[LIGHT_DIRT_LAYER] = tile.createNew();
	}
}
