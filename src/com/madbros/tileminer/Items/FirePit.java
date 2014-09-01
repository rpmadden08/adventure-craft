package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class FirePit extends BlockItem32 {
	public FirePit() {
		id = FIRE_PIT;
		name = "Fire Pit";
		tileId = FIRE_PIT_TILE;
		craftCost = new int[]{STICK, LOG};
		craftCostAmount = new int[]{10, 2};
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.FIRE_PIT);
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new FirePit();
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) {

				sprite.setColor(HIGHLIGHT_COLOR);
				sprite.draw(x, y, Z_CHARACTER);
				sprite.setColor(Color.WHITE);
			}else {
				super.highlightItem(block, x, y);
			}
			
	}
}
