package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.*;

public class EmptyBucket extends BlockItem {
	public EmptyBucket() {
		id = EMPTY_BUCKET;
		name = "Bucket";
		tileId = HOLE;
		sprite = Sprites.sprites.get(Sprites.EMPTY_BUCKET);
		placeableTileIds = new int[]{WATER};
		is32 = true;
		maxStackSize = 1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{5};
	}
	
	@Override
	public EmptyBucket createNew() {
		return new EmptyBucket();
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		Game.level.highlightedBlock.collisionTile = null;
		hB.layers[WATER_LAYER] = new HoleTile();
		hB.setCollisionTile((CollisionTile)hB.layers[WATER_LAYER]);
		Game.inventory.invBar[Game.inventory.itemSelected].item = new WaterBucket();
		Game.inventory.invBar[Game.inventory.itemSelected].item.stackSize = 1;
		Game.level.hasPlacedItemOnClick = true;
	}
}
