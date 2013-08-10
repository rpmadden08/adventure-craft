package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.Time;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		tileId = DIRT;
		texture = Textures.earthClumpTexture;
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
			System.out.println(Time.getTime() - hB.timePlaced);
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
		System.out.println(hB.canPlace);
		
		if(hB.layers[WATER_LAYER].id == HOLE || hB.layers[WATER_LAYER].id == WATER) {
			hB.canPlace = false;
			hB.timePlaced = Time.getTime();
			hB.layers[GRASS_LAYER] = new NoTile();
			hB.layers[WATER_LAYER] = new NoTile();
//			hB.layers[LIGHT_DIRT_LAYER] = new NoTile();
			
			hB.collisionTile = null;
			hB.cRect = null;
		} else if(hB.canPlace == true) {
			hB.layers[GRASS_LAYER] = new NoTile();
			hB.layers[WATER_LAYER] = new NoTile();
			hB.layers[LIGHT_DIRT_LAYER] = tile;
		}
	}
}
