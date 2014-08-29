package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.TileTypes.*;
import com.madbros.tileminer.Utils.Helpers;

public class Roof extends BlockItem {
	public Roof() {
		id = ROOF;
		name = "Roof";
		tileId = ROOF_TILE;
		placeableTileIds = new int[]{WALL_BORDER};
		itemsPossiblyCraftable = new int[]{};
		sprite = Sprites.sprites.get(Sprites.ROOF);
		numberProducedByCrafting = 4;
		craftCost = new int[]{PLANK, STONE};
		craftCostAmount = new int[]{8,1};
		maxStackSize = 99;
	}
	
	@Override
	public Roof createNew() {
		return new Roof();
	}
	
	public void useLeft() {
		Block hB = Game.level.highlightedBlock;
		if(hB.layers[ABOVE_LAYER_4].id == ROOF_TILE && isInRange == true) {
			
			
			hB.layers[ABOVE_LAYER_4].currentHp -= attackPower;
			if(hB.layers[ABOVE_LAYER_4].currentHp < 1) {
				//.deleteObjectTile();
				hB.layers[ABOVE_LAYER_4].deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				if(Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[ABOVE_LAYER_2].id == AIR) {
					hB.layers[ABOVE_LAYER_4] = new NoTile();
				}
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-3);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-1);
				Game.level.autoTileHighlightedBlock();
			}
		} 
	}
	
	public void impact() {
		Block hB = Game.level.highlightedBlock;
		if(hB.layers[ABOVE_LAYER_4].id == ROOF_TILE) {
			hB.layers[ABOVE_LAYER_4].currentHp -= attackPower;
			if(hB.layers[ABOVE_LAYER_4].currentHp < 1) {
				//Game.level.highlightedBlock.deleteObjectTile();
				//Game.level.highlightedBlock.collisionTile = null;
//				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
//				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_2);
				
				hB.layers[ABOVE_LAYER_4].deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				if(Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].layers[ABOVE_LAYER_2].id == AIR) {
					hB.layers[ABOVE_LAYER_4] = new NoTile();
				}
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-3);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-1);
				Game.level.autoTileHighlightedBlock();
				calculateUsage();
			}
		}
	}
	
	public void useRight() {
		super.useRight();
		//if(isInRange == true) {
			Tile tile = TILE_HASH.get(tileId).createNew();
			Block hB = Game.level.highlightedBlock;
			if(Helpers.arrayDoesContainInt(placeableTileIds, hB.layers[ABOVE_LAYER_3].id) || isNextToRoofTile()) {
				if(hB.layers[ABOVE_LAYER_4].id != this.tileId) {
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
	
	public Boolean isNextToRoofTile() {
		int x = Game.level.highlightedBlockX;
		int y = Game.level.highlightedBlockY;
		if(Game.level.activeBlocks[x-1][y].layers[ABOVE_LAYER_4].id == ROOF_TILE) {
			return true;
		}
		if(Game.level.activeBlocks[x+1][y].layers[ABOVE_LAYER_4].id == ROOF_TILE) {
			return true;
		}
		if(Game.level.activeBlocks[x][y-1].layers[ABOVE_LAYER_4].id == ROOF_TILE) {
			return true;
		}
		if(Game.level.activeBlocks[x][y+1].layers[ABOVE_LAYER_4].id == ROOF_TILE) {
			return true;
		}
		return false;
	}
	
	@Override
	public void placeTile(Block hB, Tile tile) {
		hB.layers[ABOVE_LAYER_4] = tile;
		hB.layers[ABOVE_LAYER_4].housingNumber = Game.level.currentHousing;
	}
	
	public void highlightItem(Block block, int x, int y) {
		String houseNumber = "House #" +Game.level.currentHousing;
		String UIText = "(-/+)";
		Sprites.font.draw(Game.batch, houseNumber, 4, 108);
		Sprites.font.draw(Game.batch, UIText, 18, 108-Sprites.font.getLineHeight());
		if(Helpers.arrayDoesContainInt(placeableTileIds, block.layers[ABOVE_LAYER_3].id)|| isNextToRoofTile()) { 
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
			
		} else {
			super.highlightItem(block, x, y);
		}
	}
}
