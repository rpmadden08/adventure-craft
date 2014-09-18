package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class TomatoSprout extends TileItem {
	public TomatoSprout() {
		id = TOMATO_SPROUT;
		tileId = TOMATO_TILE;
		name = "Tomato Sprout";
		placeableTileIds = new int[]{DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.TOMATO_SPROUT);
		
	}
	
	@Override
	public TileItem createNew() {
		return new TomatoSprout();
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
		}else {
			super.highlightItem(block, x, y);
		}
	}
}
