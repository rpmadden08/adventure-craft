package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class TallGrassA extends BlockItem32 {
	public TallGrassA() {
		id = TALL_GRASS_A;
		tileId = TALL_GRASS_A_TILE;
		name = "Tall Grass";
		placeableTileIds = new int[]{GRASS};
		sprite = Sprites.sprites.get(Sprites.TALL_GRASS_A_ITEM);
		
	}
	@Override
	public BlockItem32 createNew() {
		return new TallGrassA();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		//if(hB.layers[OBJECT_LAYER].autoTile == 5) {
			Random rand = new Random();
			int tileID = rand.nextInt(3) + 1;
			if(tileID == 2) {
				tile = TILE_HASH.get(45).createNew();
			} else if(tileID == 3) {
				tile = TILE_HASH.get(46).createNew();
			}
			
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
