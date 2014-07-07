package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class YellowFlowers extends BlockItem32 {
	public YellowFlowers() {
		id = YELLOW_FLOWERS;
		tileId = YELLOW_FLOWERS_TILE;
		name = "Yellow Flowers";
		placeableTileIds = new int[]{GRASS};
		sprite = Sprites.sprites.get(Sprites.YELLOW_FLOWERS);
		
	}
	@Override
	public BlockItem32 createNew() {
		return new YellowFlowers();
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
		}else {
			super.highlightItem(block, x, y);
		}
	}
	

}
