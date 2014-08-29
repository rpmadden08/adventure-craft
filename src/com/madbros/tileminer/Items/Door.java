package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.TileTypes.WallBorderTile;
import com.madbros.tileminer.TileTypes.WallBottomTile;
import com.madbros.tileminer.Utils.Helpers;

public class Door extends BlockItem32 {
	int tile2Id = WOOD_DOOR_TOP_TILE;
	public Door() {
		id = WOODEN_DOOR;
		name = "Wooden Door";
		tileId = WOOD_DOOR_BOTTOM_TILE;
		placeableTileIds = new int[]{WOOD_FLOOR_TILE};
		sprite = Sprites.sprites.get(Sprites.WOODEN_DOOR);
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK, TIN_BAR};
		craftCostAmount = new int[]{8, 1};
		maxStackSize = 99;
		
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new Door();
	}
	
	public void useRight() {
//		super.useRight();
		//if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.layers[GRASS_LAYER].id) && AIR == hB.layers[OBJECT_LAYER].id && isPlaceable(hB)) {
//				if(hB.layers[ABOVE_LAYER_3].id != this.tileId && hB.layers[ABOVE_LAYER_3].isMiddleTile) {
					placeTile(hB, tile);
					stackSize -= 1;
					Game.inventory.deleteItemIfNecessary();
					Game.level.autoTileHighlightedBlock();
					int x = hB.getX(Game.level.activeBlocks);
					int y = hB.getY(Game.level.activeBlocks);
					//Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_1] = new DirtMountainTopTile();
					Game.level.autoTileBlock(x, y-2);
					Game.level.autoTileBlock(x, y-3);
//				}
			} else {
				checkUsability();
			}
		//}
	}

	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[OBJECT_LAYER] = tile;
		hB.setCollisionTile((CollisionTile)tile);
		Game.level.hasPlacedItemOnClick = true;
		int x = hB.getX(Game.level.activeBlocks);
		int y = hB.getY(Game.level.activeBlocks);
		Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = TILE_HASH.get(tile2Id).createNew();
		Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_2] = TILE_HASH.get(Game.level.activeBlocks[x-1][y-2].layers[ABOVE_LAYER_2].id).createNew();
		Game.level.activeBlocks[x][y-3].layers[ABOVE_LAYER_3] = new WallBorderTile();
	}
	public boolean isPlaceable(Block block) {
		int x2 = block.getX(Game.level.activeBlocks);
		int y2 = block.getY(Game.level.activeBlocks);
		if(WallBottomTile.class.isAssignableFrom(Game.level.activeBlocks[x2-1][y2].layers[OBJECT_LAYER].getClass()) &&
				WallBottomTile.class.isAssignableFrom(Game.level.activeBlocks[x2+1][y2].layers[OBJECT_LAYER].getClass())) {
			return true;
		} else 
			return false;
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id && isPlaceable(block)) {
				Tile tile = TILE_HASH.get(tileId);
				tile.sprites[0].setColor(HIGHLIGHT_COLOR);
				tile.sprites[0].draw(x, y, Z_CHARACTER);
				tile.sprites[0].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
//				tile.sprites[5].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[5].draw(x, y+16, Z_CHARACTER);
//				tile.sprites[5].setColor(Color.WHITE);
//				
//				//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
//				tile.sprites[30].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[30].draw(x+16, y, Z_CHARACTER);
//				tile.sprites[30].setColor(Color.WHITE);
//				
//				//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
//				tile.sprites[35].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[35].draw(x+16, y+16, Z_CHARACTER);
//				tile.sprites[35].setColor(Color.WHITE);
				
				//Block block2 = Game.level.activeBlocks[i][j-1];
				
				tile = TILE_HASH.get(tile2Id);
				tile.sprites[0].setColor(HIGHLIGHT_COLOR);
				tile.sprites[0].draw(x, y-32, Z_CHARACTER);
				tile.sprites[0].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
//				tile.sprites[5].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[5].draw(x, y-16, Z_CHARACTER);
//				tile.sprites[5].setColor(Color.WHITE);
//				
//				//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
//				tile.sprites[30].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[30].draw(x+16, y-32, Z_CHARACTER);
//				tile.sprites[30].setColor(Color.WHITE);
//				
//				//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
//				tile.sprites[35].setColor(HIGHLIGHT_COLOR);
//				tile.sprites[35].draw(x+16, y-16, Z_CHARACTER);
//				tile.sprites[35].setColor(Color.WHITE);
				
				//block2 = Game.level.activeBlocks[i][j-2];
				int x2 = block.getX(Game.level.activeBlocks);
				int y2 = block.getY(Game.level.activeBlocks);
				tile = TILE_HASH.get(Game.level.activeBlocks[x2-1][y2-2].layers[ABOVE_LAYER_2].id);
				tile.sprites[0].setColor(HIGHLIGHT_COLOR);
				tile.sprites[0].draw(x, y-64, Z_CHARACTER);
				tile.sprites[0].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
				tile.sprites[5].setColor(HIGHLIGHT_COLOR);
				tile.sprites[5].draw(x, y-48, Z_CHARACTER);
				tile.sprites[5].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
				tile.sprites[30].setColor(HIGHLIGHT_COLOR);
				tile.sprites[30].draw(x+16, y-64, Z_CHARACTER);
				tile.sprites[30].setColor(Color.WHITE);
				
				//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
				tile.sprites[35].setColor(HIGHLIGHT_COLOR);
				tile.sprites[35].draw(x+16, y-48, Z_CHARACTER);
				tile.sprites[35].setColor(Color.WHITE);
				
				
				tile = new WallBorderTile();
				tile.sprites[0].setColor(HIGHLIGHT_COLOR);
				tile.sprites[0].draw(x, y-96, Z_CHARACTER);
				tile.sprites[0].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i+1][j].layers[ABOVE_LAYER_6];
				tile.sprites[5].setColor(HIGHLIGHT_COLOR);
				tile.sprites[5].draw(x, y-80, Z_CHARACTER);
				tile.sprites[5].setColor(Color.WHITE);
				
				//autoTileCheckTile = Game.level.activeBlocks[i][j+1].layers[ABOVE_LAYER_6];
				tile.sprites[30].setColor(HIGHLIGHT_COLOR);
				tile.sprites[30].draw(x+16, y-96, Z_CHARACTER);
				tile.sprites[30].setColor(Color.WHITE);
				
				//autoTileChe0kTile = Game.level.activeBlocks[i+1][j+1].layers[ABOVE_LAYER_6];
				tile.sprites[35].setColor(HIGHLIGHT_COLOR);
				tile.sprites[35].draw(x+16, y-80, Z_CHARACTER);
				tile.sprites[35].setColor(Color.WHITE);
			}else {
				super.highlightItem(block, x, y);
			}
			
	}
}
