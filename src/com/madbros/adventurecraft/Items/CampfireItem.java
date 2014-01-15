package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class CampfireItem extends BlockItem32 {
	public CampfireItem() {
		id = CAMPFIRE_ITEM;
		name = "Campfire";
		tileId = CAMPFIRE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS};
		sprite = Sprites.sprites.get(Sprites.CAMPFIRE_SINGLE);
	}
	
	@Override
	public BlockItem32 createNew() {
		return new CampfireItem();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

	@Override
	public void highlightItem(int x, int y) {
			
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id) {
				//topTile.sprites[topTile.autoTile].setColor(highlightColor);
				//sprite.render(x, y);
				//System.out.println("CHECK");
				sprite.setColor(1f,1f,1f,0.3f);
				sprite.draw(x, y, Z_CHARACTER, hB.absRect.w, hB.absRect.h);
				sprite.setColor(Color.WHITE);
				//topTile.sprites[topTile.autoTile].setColor(Color.WHITE);
			}
	}
}
