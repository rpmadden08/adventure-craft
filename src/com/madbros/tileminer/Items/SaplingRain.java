package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class SaplingRain extends BlockItem32 {
	public SaplingRain() {
		id = SAPLING_ITEM;
		tileId = SAPLING_RAIN;
		name = "Rain Forest Tree Sapling";
		
		placeableTileIds = new int[]{GRASS, DIRT,DARK_GRASS, DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.SAPLING);
		
	}
	
	@Override
	public SaplingRain createNew() {
		return new SaplingRain();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;

	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
			sprite.setColor(Color.WHITE);
		}
	}
}
