package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class Sapling extends BlockItem32 {
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
	public BlockItem32 createNew() {
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
		}
	}
}
