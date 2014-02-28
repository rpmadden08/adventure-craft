package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public class RedMushroom extends BlockItem32 {
	public RedMushroom() {
		id = RED_MUSHROOM;
		tileId = RED_MUSHROOM_TILE;
		name = "Red Mushroom";
		placeableTileIds = new int[]{GRASS};
		sprite = Sprites.sprites.get(Sprites.RED_MUSHROOM);
		
	}
	@Override
	public void useRight() {
		//if(isInRange) {
			Block hB = Game.level.highlightedBlock;
			if(hB.layers[GRASS_LAYER].autoTile == 4) {
				super.useRight();
			}
		//}
		
	}
	@Override
	public BlockItem32 createNew() {
		return new RedMushroom();
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
