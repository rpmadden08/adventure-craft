package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class GrassSeed extends BlockItem {
	public GrassSeed() {
		id = GRASS_SEED;
		name = "Grass Seed";
		tileId = GRASS;
		placeableTileIds = new int[]{DIRT};
		sprite = Sprites.sprites.get(Sprites.GRASS_ITEM);
	}
	
	@Override
	public GrassSeed createNew() {
		return new GrassSeed();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[GRASS_LAYER] = tile;
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
			
		}
	}
}
