package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public abstract class BlockItem32 extends StackableItem{
	int tileId = DIRT;
	int[] placeableTileIds;
	
	@Override
	public abstract BlockItem32 createNew();
	
	@Override
	public void useRight() {
		super.useRight();
		//if(isInRange) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id && !isPlacementCollidingWithHero(hB, tile.layer)&& !isPlacementCollidingWithMob(hB, tile.layer)) {
				placeTile(hB, tile);
				Game.soundController.create("sounds/blockPlacement.wav", 1f);
				stackSize -= 1;
				Game.inventory.deleteItemIfNecessary();
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
