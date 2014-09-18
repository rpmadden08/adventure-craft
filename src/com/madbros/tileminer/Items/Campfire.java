package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class Campfire extends TileItem {
	public Campfire() {
		id = CAMPFIRE_ITEM;
		name = "Campfire";
		tileId = CAMPFIRE;
		placeableTileIds = OBJECT_PLACEABLE_TILE_IDS;
		sprite = Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE);
	}
	
	@Override
	public TileItem createNew() {
		return new Campfire();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		//hB.setCollisionTile((CollisionTile)tile);
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

				sprite.setColor(HIGHLIGHT_COLOR);
				sprite.draw(x, y, Z_CHARACTER);
				sprite.setColor(Color.WHITE);
			}
	}
}
