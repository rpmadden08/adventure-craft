package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class CornSprout extends BlockItem32 {
	public CornSprout() {
		id = CORN_SPROUT;
		tileId = CORN_TILE;
		name = "Corn Sprout";
		placeableTileIds = new int[]{DARK_DIRT};
		sprite = Sprites.sprites.get(Sprites.CORN_SPROUT);
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new CornSprout();
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
		}
	}
}