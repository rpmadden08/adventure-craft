package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.Utils.Helpers;

public class Torch extends BlockItem32 {
	public Torch() {
		id = TORCH;
		name = "Torch";
		tileId = TORCH_TILE;
		placeableTileIds = new int[]{DIRT, DARK_DIRT, GRASS, DARK_GRASS, SAND};
		sprite = Sprites.sprites.get(Sprites.TORCH);
		craftCost = new int[]{STICK, COAL_ITEM};
		craftCostAmount = new int[]{1, 1};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Torch();
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
