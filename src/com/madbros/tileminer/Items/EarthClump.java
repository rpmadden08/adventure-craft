package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.*;

public class EarthClump extends AutoTileItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		name = "Dirt";
		tileId = DIRT;
		sprite = Sprites.sprites.get(Sprites.DIRT_ITEM);
		placeableTileIds = new int[]{WATER, DARK_DIRT, HOLE};
		is32 = true;
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		Game.level.highlightedBlock.collisionTile = null;
		if(hB.layers[WATER_LAYER].id == HOLE || hB.layers[WATER_LAYER].id == WATER) {
			placeLightDirt(Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY], tile);
		} else if(hB.canPlace == true) {
			placeLightDirt(Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY], tile);
		}
	}
	
	public void placeLightDirt(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = new NoTile();
		hB.layers[WATER_LAYER] = new NoTile();
		hB.layers[LIGHT_DIRT_LAYER] = tile.createNew();
		
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
