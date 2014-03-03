package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class Campfire extends BlockItem32 {
	public Campfire() {
		id = CAMPFIRE_ITEM;
		name = "Campfire";
		tileId = CAMPFIRE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS};
		sprite = Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE);
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Campfire();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

				sprite.setColor(HIGHLIGHT_COLOR);
				sprite.draw(x, y, Z_CHARACTER, block.absRect.w, block.absRect.h);
				sprite.setColor(Color.WHITE);
			}
	}
}
