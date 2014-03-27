package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.CollisionTile;
import com.madbros.adventurecraft.TileTypes.StairsUpTopTile;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public class StairsUp extends BlockItem32 {
	public StairsUp() {
		id = STAIRS_UP;
		name = "Stairs Up";
		tileId = STAIRS_UP_BOTTOM_TILE;
		placeableTileIds = new int[]{DIRT};
		sprite = Sprites.sprites.get(Sprites.STAIRS_UP);
	}
	
	@Override
	public BlockItem32 createNew() {
		return new StairsUp();
	}
	
	public void useRight() {
		if(Game.currentLevel != OVERWORLD_FOLDER) {
			super.useRight();
		}
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
			hB.layers[OBJECT_LAYER] = tile;
			//hB.setCollisionTile((CollisionTile)tile);
			Game.level.hasPlacedItemOnClick = true;
			int x = hB.getX(Game.level.activeBlocks);
			int y = hB.getY(Game.level.activeBlocks);
			Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new StairsUpTopTile();
			hB.layers[OBJECT_LAYER].cRect.x = hB.getAbsX()* TILE_SIZE;
			hB.layers[OBJECT_LAYER].cRect.y = hB.getAbsY()* TILE_SIZE;
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
