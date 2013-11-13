package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;

public class SeedPotato extends BlockItem32 {
	public SeedPotato() {
		id = SEED_POTATO;
		tileId = POTATO_TILE;
		placeableTileIds = new int[]{DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SEED_POTATO);
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new SeedPotato();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
	}
}
