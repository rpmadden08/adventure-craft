package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.TileTypes.WallBorderTile;
import com.madbros.tileminer.TileTypes.WoodWallBottomTile;
import com.madbros.tileminer.TileTypes.WoodenDoorTopTile;
import com.madbros.tileminer.Utils.Helpers;

public class WoodenDoor extends BlockItem32 {
	public WoodenDoor() {
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
		return new WoodenDoor();
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
		Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_1] = new WoodenDoorTopTile();
		Game.level.activeBlocks[x][y-2].layers[ABOVE_LAYER_2] = TILE_HASH.get(Game.level.activeBlocks[x-1][y-2].layers[ABOVE_LAYER_2].id).createNew();
		Game.level.activeBlocks[x][y-3].layers[ABOVE_LAYER_3] = new WallBorderTile();
	}
	public boolean isPlaceable(Block block) {
		int x2 = block.getX(Game.level.activeBlocks);
		int y2 = block.getY(Game.level.activeBlocks);
		if(WoodWallBottomTile.class.isAssignableFrom(Game.level.activeBlocks[x2-1][y2].layers[OBJECT_LAYER].getClass()) &&
				WoodWallBottomTile.class.isAssignableFrom(Game.level.activeBlocks[x2+1][y2].layers[OBJECT_LAYER].getClass())) {
			return true;
		} else 
			return false;
	}

	@Override
	public void highlightItem(Block block, int x, int y) {
			
			if(Helpers.arrayDoesContainInt(placeableTileIds, block.getTopTerrainTile().id) && AIR == block.layers[OBJECT_LAYER].id && isPlaceable(block)) {
					sprite.setColor(HIGHLIGHT_COLOR);
					sprite.draw(x, y, Z_CHARACTER);
					sprite.setColor(Color.WHITE);
			}else {
				super.highlightItem(block, x, y);
			}
			
	}
}
