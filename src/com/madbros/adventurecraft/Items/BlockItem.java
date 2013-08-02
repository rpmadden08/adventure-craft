package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;

public abstract class BlockItem extends StackableItem{
	int tileId = DIRT;
	int[] placeableTileIds;
	
	@Override
	public abstract BlockItem createNew();
	
	@Override
	public void useRight() {
		Tile tile = TILE_HASH.get(tileId).createNew();
		Block hB = Game.level.highlightedBlock;
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id)) {
			placeTile(hB, tile);
			stackSize -= 1;
			Game.inventory.deleteItemIfNecessary();
			Game.level.autoTileHighlightedBlock();
		}
	}
	
	public abstract void placeTile(Block hB, Tile tile);
}
