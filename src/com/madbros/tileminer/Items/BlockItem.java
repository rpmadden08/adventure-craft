package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

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
		super.useRight();
		//if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			System.out.println(hB.layers[tile.layer].cRect.x+"-"+hB.layers[tile.layer].cRect.y);
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id && !isPlacementCollidingWithHero(hB, tile.layer)) {
				placeTile(hB, tile);
				stackSize -= 1;
				Game.inventory.deleteItemIfNecessary();
				Game.level.autoTileHighlightedBlock();
				int x = hB.getX(Game.level.activeBlocks);
				int y = hB.getY(Game.level.activeBlocks);
				//Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_1] = new DirtMountainTopTile();
				Game.level.autoTileBlock(x, y-2);
				Game.level.autoTileBlock(x, y-3);
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
