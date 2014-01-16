package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class EarthClump extends BlockItem {
	public EarthClump() {
		id = EARTH_CLUMP;
		name = "Dirt";
		tileId = DIRT;
		sprite = Sprites.sprites.get(Sprites.DIRT_ITEM);
		placeableTileIds = new int[]{WATER, SAND, DARK_DIRT, HOLE};
	}
	
	@Override
	public EarthClump createNew() {
		return new EarthClump();
	}
	
	@Override
	public void useRight() {
		Tile tile = TILE_HASH.get(tileId).createNew();
		Block hB = Game.level.highlightedBlock;
		
		if(hB.canPlace == false) {
			if(Time.getTime() - hB.timePlaced > 300) {
				hB.canPlace = true;
			}
		}
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && hB.canPlace == true) {
			placeTile(hB, tile);
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		if(hB.layers[WATER_LAYER].id == HOLE || hB.layers[WATER_LAYER].id == WATER) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j< 2; j++) {
					placeDarkDirt(Game.level.activeBlocks[Game.level.highlightedBlockX+i][Game.level.highlightedBlockY+j]);
				}
			}
		} else if(hB.canPlace == true) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j< 2; j++) {
					placeLightDirt(Game.level.activeBlocks[Game.level.highlightedBlockX+i][Game.level.highlightedBlockY+j], tile);
				}
			}
		}
	}
	
	public void placeDarkDirt(Block hB) {
		hB.canPlace = false;
		hB.timePlaced = Time.getTime();
		hB.layers[GRASS_LAYER] = new NoTile();
		hB.layers[WATER_LAYER] = new NoTile();

		hB.collisionTile = null;
	}
	
	public void placeLightDirt(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = new NoTile();
		hB.layers[WATER_LAYER] = new NoTile();
		hB.layers[LIGHT_DIRT_LAYER] = tile.createNew();
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) { 
			int i = block.getX(Game.level.activeBlocks); 
			int j = block.getY(Game.level.activeBlocks);
	
			
			//Tile autoTileCheckTile = Game.level.activeBlocks[i][j].layers[ABOVE_LAYER_6];
			Tile tile = TILE_HASH.get(tileId);
			tile.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile.sprites[0].draw(x, y, Z_CHARACTER);
			tile.sprites[0].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
			tile.sprites[2].setColor(HIGHLIGHT_COLOR);
			tile.sprites[2].draw(x, y+32, Z_CHARACTER);
			tile.sprites[2].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[6].setColor(HIGHLIGHT_COLOR);
			tile.sprites[6].draw(x+32, y, Z_CHARACTER);
			tile.sprites[6].setColor(Color.WHITE);
			
			//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[8].setColor(HIGHLIGHT_COLOR);
			tile.sprites[8].draw(x+32, y+32, Z_CHARACTER);
			tile.sprites[8].setColor(Color.WHITE);
			
		}
	}
}
