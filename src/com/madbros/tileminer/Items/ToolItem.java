package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.ABOVE_LAYER_1;
import static com.madbros.tileminer.Constants.ABOVE_LAYER_2;
import static com.madbros.tileminer.Constants.AIR;
import static com.madbros.tileminer.Constants.BROWN_MUSHROOM_TILE;
import static com.madbros.tileminer.Constants.HIGHLIGHT_COLOR;
import static com.madbros.tileminer.Constants.OBJECT_LAYER;
import static com.madbros.tileminer.Constants.RED_FLOWERS_TILE;
import static com.madbros.tileminer.Constants.RED_MUSHROOM_TILE;
import static com.madbros.tileminer.Constants.TALL_GRASS_A_TILE;
import static com.madbros.tileminer.Constants.TALL_GRASS_B_TILE;
import static com.madbros.tileminer.Constants.TALL_GRASS_C_TILE;
import static com.madbros.tileminer.Constants.TILE_SIZE;
import static com.madbros.tileminer.Constants.YELLOW_FLOWERS_TILE;
import static com.madbros.tileminer.Constants.Z_CHARACTER;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Utils.RectInt;



public abstract class ToolItem extends WeaponItem {
	
	
	
	public ToolItem() {
		super();
		isRepeatable = true;
		
	}
	@Override
	public void useLeft() {
		if(Game.hero.isSwimming == false) {
			
			if(Game.level.highlightedBlock != null && !isCollidingWithActor(Game.level.highlightedBlock, Game.level.tileBeingAttacked.layer)) {
				if(Game.level.tileBeingAttacked.isPickable || Game.level.tileBeingAttacked.isDiggable || Game.level.tileBeingAttacked.isChoppable) {
					if(isInRange == true && Game.level.tileBeingAttacked.isToolStrongEnough(this)) {
						swing();
					}
				}
				if(Game.level.tileBeingAttacked.isBreakable && isInRange == true) {
					Game.level.tileBeingAttacked.currentHp -= attackPower;
					if(Game.level.tileBeingAttacked.currentHp < 1) {
						Game.level.highlightedBlock.deleteObjectTile();
						Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
					}
				}
			}
			if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
				
				Game.hero.attack(this);
				//Game.soundController.create("sounds/swordSwing1.wav", 0.2f);
			}
		}
	}
	
	public void impact() {
		if(Game.level.tileBeingAttacked.isPickable) {
			Game.level.tileBeingAttacked.currentHp -= itemPower();
			Game.soundController.create("sounds/pickSound.wav", 0.2f);
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteObjectTile();
				Game.level.highlightedBlock.collisionTile = null;
				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_2);
				
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-3);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-1);
				Game.level.autoTileHighlightedBlock();
				calculateUsage();
			}
		} else if(Game.level.tileBeingAttacked.isChoppable) {
			Game.level.tileBeingAttacked.currentHp -= itemPower();
			if(Game.level.tileBeingAttacked.currentHp < 1) {
				Game.level.highlightedBlock.deleteObjectTile();
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				//Game.level.autoTileHighlightedBlock();
				calculateUsage();
			}
			Game.soundController.create("sounds/axeChop.wav", 0.2f);
			RectInt hBAbsRect = Game.level.highlightedBlock.absRect.getRectInt();
			Game.particleEffectController.add("chunks.p", 
					hBAbsRect.x +(TILE_SIZE/2), hBAbsRect.y + (TILE_SIZE/2));

		} else if(Game.level.tileBeingAttacked.isDiggable) {
			Game.level.tileBeingAttacked.currentHp -= itemPower();
			Game.soundController.create("sounds/shovelDig.wav", 0.2f);
			if(Game.level.tileBeingAttacked.layer == OBJECT_LAYER) {
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				calculateUsage();
			} else if(Game.level.tileBeingAttacked.currentHp < 1) {
				
				Game.level.highlightedBlock.deleteTopGrassTile();
				
				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
				
				Game.level.autoTileHighlightedBlock();
				calculateUsage();
			}
		}
	}
	
	
	public void swing() {
//		if(isInUse == false) {
//			swingRemaining = 0;
//			isInUse = true;	
//		} else {
			swingRemaining = swingRemaining -1;
			if (swingRemaining <= 0) {
				swingRemaining = swingSpeed;
				impact();

			}
//		}
	}

	@Override
	public abstract ToolItem createNew();
	
	public boolean checkID(int id) {
		if(id == AIR  || id == TALL_GRASS_A_TILE || id == TALL_GRASS_B_TILE || id == TALL_GRASS_C_TILE || 
				id == RED_MUSHROOM_TILE || id == BROWN_MUSHROOM_TILE || id == RED_FLOWERS_TILE || id == YELLOW_FLOWERS_TILE ) {
			return true;	
		} else {
			return false;
		}
		
	}
	
	
	
	public void highlightItem(Block block, int x, int y) {
		if(Game.level.tileBeingAttacked.isPickable && Game.level.tileBeingAttacked.isAutoTileable && 
				Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);

			
			
			//Someday maybe add the above layer 1 and 2 highlighting...
		} else if(Game.level.tileBeingAttacked.isPickable) {
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].setColor(HIGHLIGHT_COLOR);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].draw(x, y, Z_CHARACTER);
			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].setColor(1f,1f,1f,1f);
			
		} else if(Game.level.tileBeingAttacked.isDiggable ) {
			int objectTileID = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
			if (checkID(objectTileID)) {

				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].draw(x, y, Z_CHARACTER);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].draw(x+16, y, Z_CHARACTER);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].draw(x, y+16, Z_CHARACTER);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(1f,1f,1f,1f);
				
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].draw(x+16, y+16, Z_CHARACTER);
				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(1f,1f,1f,1f);
			}
		} else if(Game.level.tileBeingAttacked.isChoppable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
		} 
	}
}
