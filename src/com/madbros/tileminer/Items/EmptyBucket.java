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
	

	
	public void highlightItem(Block block, int x, int y) {
		
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id) { 
//			int i = block.getX(Game.level.activeBlocks); 
//			int j = block.getY(Game.level.activeBlocks);
	
			
			//Tile autoTileCheckTile = Game.level.activeBlocks[i][j].layers[ABOVE_LAYER_6];
			Tile tile = TILE_HASH.get(tileId);
			tile.sprites[0].setColor(HIGHLIGHT_COLOR);
			tile.sprites[0].draw(x, y, Z_CHARACTER);
			tile.sprites[0].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
			tile.sprites[5].setColor(HIGHLIGHT_COLOR);
			tile.sprites[5].draw(x, y+16, Z_CHARACTER);
			tile.sprites[5].setColor(Color.WHITE);
			
			//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[30].setColor(HIGHLIGHT_COLOR);
			tile.sprites[30].draw(x+16, y, Z_CHARACTER);
			tile.sprites[30].setColor(Color.WHITE);
			
			//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
			tile.sprites[35].setColor(HIGHLIGHT_COLOR);
			tile.sprites[35].draw(x+16, y+16, Z_CHARACTER);
			tile.sprites[35].setColor(Color.WHITE);
			
		}
	}
}
