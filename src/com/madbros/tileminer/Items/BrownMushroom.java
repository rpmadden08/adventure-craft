package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class BrownMushroom extends TileItem {
	public BrownMushroom() {
		id = BROWN_MUSHROOM;
		tileId = BROWN_MUSHROOM_TILE;
		name = "Brown Mushroom";
		placeableTileIds = new int[]{GRASS};
		sprite = Sprites.sprites.get(Sprites.BROWN_MUSHROOM);
		
	}
	@Override
	public void useRight() {
		checkUsability();
		//if(isInRange) {
			Block hB = Game.level.highlightedBlock;
			if(hB.layers[GRASS_LAYER].autoTile == 4) {
				super.useRight();
			}
		//}
		
	}
	@Override
	public TileItem createNew() {
		return new BrownMushroom();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		//if(hB.layers[OBJECT_LAYER].autoTile == 5) {
			hB.layers[OBJECT_LAYER] = tile;
			hB.layers[OBJECT_LAYER].setCollisionRect(hB.absRect);
		//}
	}
	
	public void highlightItem(Block block, int x, int y) {
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id && block.layers[GRASS_LAYER].autoTile == 4) {

			//sprite.setColor(HIGHLIGHT_COLOR);
			sprite.draw(x, y, Z_CHARACTER);
			sprite.setColor(Color.WHITE);
		}
	}
	

}
