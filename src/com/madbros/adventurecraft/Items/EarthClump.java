package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.TileTypes.*;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		tileID = DIRT;
		texture = Textures.earthClumpTexture;
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}
	
	@Override
	public void useRight() {
		Tile tile = TILE_HASH.get(tileID).createNew();
		if(tile.id != Game.level.highlightedBlock.layers[LIGHT_DIRT_LAYER].id || Game.level.highlightedBlock.layers[GRASS_LAYER].id != AIR || Game.level.highlightedBlock.layers[WATER_LAYER].id != AIR) {
			Game.level.highlightedBlock.layers[GRASS_LAYER] = new NoTile();
			Game.level.highlightedBlock.layers[WATER_LAYER] = new NoTile();
			Game.level.highlightedBlock.layers[LIGHT_DIRT_LAYER] = tile;
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
}
