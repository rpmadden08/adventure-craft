package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.CollisionTile;
import com.madbros.tileminer.TileTypes.FurnaceTopTile;
import com.madbros.tileminer.TileTypes.Tile;
import com.madbros.tileminer.TileTypes.WoodenDoorTopTile;
import com.madbros.tileminer.Utils.Helpers;

public class WoodenDoor extends BlockItem32 {
	public WoodenDoor() {
		id = WOODEN_DOOR;
		name = "Wooden Door";
		tileId = WOOD_DOOR_BOTTOM_TILE;
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		placeableTileIds = new int[]{WOOD_WALL_BOTTOM_TILE};
		sprite = Sprites.sprites.get(Sprites.WOODEN_DOOR);
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new WoodenDoor();
	}
	
	public void useRight() {
		super.useRight();
		//if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.layers[OBJECT_LAYER].id)) {
				if(hB.layers[ABOVE_LAYER_3].id != this.tileId && hB.layers[ABOVE_LAYER_3].isMiddleTile) {
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
