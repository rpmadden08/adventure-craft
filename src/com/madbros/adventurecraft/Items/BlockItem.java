package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public abstract class BlockItem extends StackableItem{
	int tileId = DIRT;
	int[] placeableTileIds;
	public BlockItem() {
		is32 = false;
	}
	@Override
	public abstract BlockItem createNew();
	
	@Override
	public void useRight() {
		if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			Block hB2 = Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY];
			Block hB3 = Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1];
			Block hB4 = Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1];
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id) {
				placeTile(hB, tile);
				placeTile(hB2, TILE_HASH.get(tileId).createNew());
				placeTile(hB3, TILE_HASH.get(tileId).createNew());
				placeTile(hB4, TILE_HASH.get(tileId).createNew());
				stackSize -= 1;
				Game.inventory.deleteItemIfNecessary();
				Game.level.autoTileHighlightedBlock();
			}
		}
	}
	
	public abstract void placeTile(Block hB, Tile tile);
}
