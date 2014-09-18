package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class RedFlowers extends TileItem {
	public RedFlowers() {
		id = RED_FLOWERS;
		tileId = RED_FLOWERS_TILE;
		name = "Red Flowers";
		placeableTileIds = new int[]{GRASS};
		sprite = Sprites.sprites.get(Sprites.RED_FLOWERS);
		itemsPossiblyBrewable = new int[]{HEALTH_POTION};
		
	}
	
	@Override
	public TileItem createNew() {
		return new RedFlowers();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		//if(hB.layers[OBJECT_LAYER].autoTile == 5) {
			hB.layers[OBJECT_LAYER] = tile;
			hB.layers[OBJECT_LAYER].setCollisionRect(hB.absRect);
		//}
	}
	
	public void highlightItem(Block block, int x, int y) {
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

			//sprite.setColor(HIGHLIGHT_COLOR);
			sprite.setColor(Color.WHITE);
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		} else {
			super.highlightItem(block, x, y);
		}
		
	}
	

}
