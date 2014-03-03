package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.Helpers;

public class StoneMountain extends BlockItem {
	public StoneMountain() {
		id = STONE_MOUNTAIN_ITEM;
		name = "Stone Mountain";
		tileId = STONE_MOUNTAIN_BOTTOM;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS};
		sprite = Sprites.sprites.get(Sprites.STONE_MOUNTAIN_ITEM);
	}
	
	@Override
	public StoneMountain createNew() {
		return new StoneMountain();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
		int x = hB.getX(Game.level.activeBlocks);
		int y = hB.getY(Game.level.activeBlocks);
		Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_1] = new DirtMountainTopTile();
		
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
			
			
			//Tile autoTileCheckTile = Game.level.activeBlocks[i][j].layers[ABOVE_LAYER_6];
			tile = TILE_HASH.get(12);
			tile.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile.sprites[0].draw(x, y-64, Z_CHARACTER);
			tile.sprites[0].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
			tile.sprites[2].setColor(HIGHLIGHT_COLOR);
			tile.sprites[2].draw(x, y-32, Z_CHARACTER);
			tile.sprites[2].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[6].setColor(HIGHLIGHT_COLOR);
			tile.sprites[6].draw(x+32, y-64, Z_CHARACTER);
			tile.sprites[6].setColor(Color.WHITE);
			
			//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[8].setColor(HIGHLIGHT_COLOR);
			tile.sprites[8].draw(x+32, y-32, Z_CHARACTER);
			tile.sprites[8].setColor(Color.WHITE);
		}
	}
}
