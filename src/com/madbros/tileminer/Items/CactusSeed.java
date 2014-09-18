package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class CactusSeed extends TileItem {
	public CactusSeed() {
		id = CACTUS_SEED;
		tileId = CACTUS_SAPLING;
		name = "Baby Cactus";
		placeableTileIds = new int[]{SAND, DIRT};
		sprite = Sprites.sprites.get(Sprites.CACTUS_ITEM);
		itemsPossiblyBrewable = new int[]{SPEED_POTION};
		
	}
	
	@Override
	public TileItem createNew() {
		return new CactusSeed();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		} else {
			super.highlightItem(block, x, y);
		}
	}
}
