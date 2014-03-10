package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.Tile;

public class CopperShovel extends ToolItem {
	public CopperShovel() {
		id = COPPER_SHOVEL;
		name = "Copper Shovel";
		sprite = Sprites.sprites.get(Sprites.COPPER_SHOVEL);
		attackPower = 5;
		is32 = false;
		isRepeatable = true;
	}
	
	@Override
	public CopperShovel createNew() {
		return new CopperShovel();
	}
	
	public void useLeft() {
		if(Game.level.tileBeingAttacked.isDiggable && Game.level.tileBeingAttacked2.isDiggable 
				&& Game.level.tileBeingAttacked3.isDiggable 
				&& Game.level.tileBeingAttacked4.isDiggable &&isInRange == true) {
			swing();

		}
		Game.hero.attack(this);
	}
	public void impact() {
		Game.level.tileBeingAttacked.currentHp -= attackPower;
		if(Game.level.tileBeingAttacked.currentHp < 1) {
			
			Game.level.highlightedBlock.deleteTopGrassTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteTopGrassTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteTopGrassTile();
			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteTopGrassTile();
			
			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
			
			Game.level.autoTileHighlightedBlock();
			calculateUsage();
		}
	}
	public boolean checkID(int id) {
		if(id == AIR  || id == TALL_GRASS_A_TILE || id == TALL_GRASS_B_TILE || id == TALL_GRASS_C_TILE || 
				id == RED_MUSHROOM_TILE || id == BROWN_MUSHROOM_TILE || id == RED_FLOWERS_TILE || id == YELLOW_FLOWERS_TILE ) {
			return true;	
		} else {
			return false;
		}
		
	}
	
	public void getTopTile() {
		if(Game.level.tileBeingAttacked != Game.level.highlightedBlock.getTopTerrainTile()) {
			Game.level.tileBeingAttacked.currentHp = Game.level.tileBeingAttacked.maxHp;
			Game.level.tileBeingAttacked = Game.level.highlightedBlock.getTopTerrainTile();
			Game.level.tileBeingAttacked2 = Game.level.highlightedBlock2.getTopTerrainTile();
			Game.level.tileBeingAttacked3 = Game.level.highlightedBlock3.getTopTerrainTile();
			Game.level.tileBeingAttacked4 = Game.level.highlightedBlock4.getTopTerrainTile();
		}
	}
	
	public Tile topTile(Block block) {
		return block.getTopTerrainTile();
	}
	
	public void highlightItem(Block block, int x, int y) {
		
		if(Game.level.tileBeingAttacked.isDiggable && 
				Game.level.tileBeingAttacked2.isDiggable && 
				Game.level.tileBeingAttacked3.isDiggable && 
				Game.level.tileBeingAttacked4.isDiggable) {
//			int objectTileID = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
//			System.out.println(objectTileID);
//			if (objectTileID == AIR || objectTileID == TALL_GRASS_A_TILE) {
			int objectTileID = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
			int objectTileID2 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)+1][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
			int objectTileID3 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)+1].layers[OBJECT_LAYER].id;
			int objectTileID4 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)+1][block.getY(Game.level.activeBlocks)+1].layers[OBJECT_LAYER].id;
			//System.out.println(objectTileID+","+objectTileID2+","+objectTileID3+","+objectTileID4);
			if (checkID(objectTileID) && 
					checkID(objectTileID2) && 
					checkID(objectTileID3) && 
					checkID(objectTileID4)) {
//			Tile tile1 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[GRASS_LAYER];
//			Tile tile2 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)+1][block.getY(Game.level.activeBlocks)].layers[GRASS_LAYER];
//			Tile tile3 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)+1].layers[GRASS_LAYER];
//			Tile tile4 = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)+1][block.getY(Game.level.activeBlocks)+1].layers[GRASS_LAYER];
//				if(tile1.sprites != null) {
//				tile1.sprites[tile1.autoTile].setColor(HIGHLIGHT_COLOR);
//				tile1.sprites[tile1.autoTile].draw(x, y, Z_CHARACTER);
//				tile1.sprites[tile1.autoTile].setColor(1f,1f,1f,1f);
//				
//				
//				tile2.sprites[tile2.autoTile].setColor(HIGHLIGHT_COLOR);
//				tile2.sprites[tile2.autoTile].draw(x+32, y, Z_CHARACTER);
//				tile2.sprites[tile2.autoTile].setColor(1f,1f,1f,1f);
//				
//				tile3.sprites[tile3.autoTile].setColor(HIGHLIGHT_COLOR);
//				tile3.sprites[tile3.autoTile].draw(x, y+32, Z_CHARACTER);
//				tile3.sprites[tile3.autoTile].setColor(1f,1f,1f,1f);
//				
//				tile4.sprites[tile4.autoTile].setColor(HIGHLIGHT_COLOR);
//				tile4.sprites[tile4.autoTile].draw(x+32, y+32, Z_CHARACTER);
//				tile4.sprites[tile4.autoTile].setColor(1f,1f,1f,1f);
//				}
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].draw(x, y, Z_CHARACTER);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].draw(x+32, y, Z_CHARACTER);
				Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].draw(x, y+32, Z_CHARACTER);
				Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].draw(x+32, y+32, Z_CHARACTER);
				Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].setColor(1f,1f,1f,1f);
			}
		}
	}
}
