package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class SandClump extends AutoTileItem {
	public SandClump() {
		id = SAND_CLUMP;
		tileId = SAND;
		name = "Sand";
		placeableTileIds = new int[]{DIRT, DARK_DIRT, DARK_GRASS, GRASS, WATER};
		itemsPossiblyBurnable = new int[]{GLASS_BOTTLE};
		sprite = Sprites.sprites.get(Sprites.SAND_ITEM);
	}
	
	@Override
	public SandClump createNew() {
		return new SandClump();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		if(hB.layers[WATER_LAYER].id == WATER) {
			hB.layers[GRASS_LAYER] = tile;
			hB.layers[WATER_LAYER] = new NoTile();
			hB.collisionTile = null;
		} else {
			hB.layers[GRASS_LAYER] = tile;
		}
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) { 
//			int i = block.getX(Game.level.activeBlocks); 
//			int j = block.getY(Game.level.activeBlocks);
	
			
			//Tile autoTileCheckTile = Game.level.activeBlocks[i][j].layers[ABOVE_LAYER_6];
			Tile tile = TILE_HASH.get(tileId);
			tile.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile.sprites[0].draw(x, y, Z_CHARACTER);
			tile.sprites[0].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
			tile.sprites[5].setColor(HIGHLIGHT_COLOR);
			tile.sprites[5].draw(x, y+16, Z_CHARACTER);
			tile.sprites[5].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[30].setColor(HIGHLIGHT_COLOR);
			tile.sprites[30].draw(x+16, y, Z_CHARACTER);
			tile.sprites[30].setColor(Color.WHITE);
			
			//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[35].setColor(HIGHLIGHT_COLOR);
			tile.sprites[35].draw(x+16, y+16, Z_CHARACTER);
			tile.sprites[35].setColor(Color.WHITE);
			
		}else {
			super.highlightItem(block, x, y);
		}
	}
}
