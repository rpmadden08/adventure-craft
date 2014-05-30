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
		is32 = true;
	}
	@Override
	public abstract BlockItem createNew();
	
	@Override
	public void useRight() {
		//if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id && !isPlacementCollidingWithHero(hB, tile.layer)) {
				placeTile(hB, tile);
				stackSize -= 1;
				Game.inventory.deleteItemIfNecessary();
				Game.level.autoTileHighlightedBlock();
				int x = hB.getX(Game.level.activeBlocks);
				int y = hB.getY(Game.level.activeBlocks);
				//Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_1] = new DirtMountainTopTile();
				Game.level.autoTileBlock(x, y-2);
			}
		//}
	}
	
	@Override
	public void checkIsInRange() {
		if(isInRange) {
			useRight();
		}
	}
	
	public abstract void placeTile(Block hB, Tile tile);
}
