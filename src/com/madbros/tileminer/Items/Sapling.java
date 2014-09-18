package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class Sapling extends TileItem {
	public Sapling() {
		id = SAPLING_ITEM;
		tileId = SAPLING;
		name = "Sapling";
		placeableTileIds = new int[]{GRASS, DIRT,DARK_GRASS, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SAPLING);
	}
	
	public void useRight() {
		checkUsability();
		if(Game.currentLevel == OVERWORLD_FOLDER) {
			super.useRight();
		}
	}
	
	
	@Override
	public TileItem createNew() {
		return new Sapling();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		}else {
			super.highlightItem(block, x, y);
		}
	}
}
