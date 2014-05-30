package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.Tile;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public abstract class BlockItem32 extends StackableItem{
	int tileId = DIRT;
	int[] placeableTileIds;
	
	@Override
	public abstract BlockItem32 createNew();
	
	@Override
	public void useRight() {
		//if(isInRange) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.getTopTerrainTile().id) && AIR == hB.layers[OBJECT_LAYER].id && !isPlacementCollidingWithHero(hB, tile.layer)&& !isPlacementCollidingWithMob(hB, tile.layer)) {
				placeTile(hB, tile);
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
