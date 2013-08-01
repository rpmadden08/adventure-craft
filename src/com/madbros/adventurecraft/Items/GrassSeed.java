package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Textures;
import com.madbros.adventurecraft.TileTypes.*;

public class GrassSeed extends SeedItem {
	public GrassSeed() {
		id = GRASS_SEED;
		tileID = GRASS;
		texture = Textures.grassSeedTexture;
	}
	
	@Override
	public GrassSeed createNew() {
		return new GrassSeed();
	}
	
	@Override
	public void useRight() {
		Tile tile = TILE_HASH.get(tileID).createNew();
		Block hB = Game.level.highlightedBlock;
		if(tile.id != hB.layers[GRASS_LAYER].id && (hB.layers[DARK_DIRT_LAYER].id == DIRT || hB.layers[LIGHT_DIRT_LAYER].id == DIRT) && 
			(!hB.layers[WATER_LAYER].isMiddleTile || hB.layers[WATER_LAYER].id == AIR)) {
			hB.layers[GRASS_LAYER] = tile;
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
}
