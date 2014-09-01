package com.madbros.tileminer.Items;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;
public abstract class BaseItem extends ToolItem {
	
	public BaseItem() {
		maxStackSize = 99;
		swingSprite = Sprites.sprites.get(Sprites.SWORD);
		swingSprite.setColor(0, 0, 0, 0);
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		hitSound = "sounds/batmanPunch.wav";
		attackPower = 1;
//		itemPower = 1;
		isInUse = false;
		
		
//		cRectU = new Rect (15,14,41,16);
//		cRectD = new Rect (15,54,41,16);
//		cRectL = new Rect (-4,20,16,41);
//		cRectR = new Rect (52,20,16,41);
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
	}
	
	public void calculateUsage() {
		Game.hero.eP = Game.hero.eP - 0.1; //0.1
		//Game.hero.eP = Game.hero.eP - 1;
	}
	
	@Override
//	public void useLeft() {
//		
//		if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
//				
//			Game.hero.attack(this);
//			Game.soundController.create(sound, 0.2f);
//		}
//		if(Game.level.highlightedBlock != null && !isCollidingWithActor(Game.level.highlightedBlock)) {
//			if(Game.level.tileBeingAttacked.isPickable || Game.level.tileBeingAttacked.isDiggable || Game.level.tileBeingAttacked.isChoppable) {
//				if(isInRange == true && Game.level.tileBeingAttacked.isToolStrongEnough(this)) {
//					swing();
//				}
//			}
//			if(Game.level.tileBeingAttacked.isBreakable && isInRange == true) {
//				Game.level.tileBeingAttacked.currentHp -= attackPower;
//				if(Game.level.tileBeingAttacked.currentHp < 1) {
//					Game.level.highlightedBlock.deleteObjectTile();
//					Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//				}
//			}
//		}
//		
//	}
	
//	public void impact() {
//		if(Game.level.tileBeingAttacked.isPickable) {
//			Game.level.tileBeingAttacked.currentHp -= attackPower;
//			if(Game.level.tileBeingAttacked.currentHp < 1) {
//				Game.level.highlightedBlock.deleteObjectTile();
//				Game.level.highlightedBlock.collisionTile = null;
//				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
//				Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_2);
//				
//				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//				
//				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-3);
//				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
//				Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-1);
//				Game.level.autoTileHighlightedBlock();
//				calculateUsage();
//			}
//		} else if(Game.level.tileBeingAttacked.isChoppable) {
//			Game.level.tileBeingAttacked.currentHp -= attackPower;
//			if(Game.level.tileBeingAttacked.currentHp < 1) {
//				Game.level.highlightedBlock.deleteObjectTile();
//				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//				//Game.level.autoTileHighlightedBlock();
//				calculateUsage();
//			}
//			Game.soundController.create(sound, 0.2f);
//			RectInt hBAbsRect = Game.level.highlightedBlock.absRect.getRectInt();
//			Game.particleEffectController.add("chunks.p", 
//					hBAbsRect.x +(TILE_SIZE/2), hBAbsRect.y + (TILE_SIZE/2));
//
//		} else if(Game.level.tileBeingAttacked.isDiggable) {
//			Game.level.tileBeingAttacked.currentHp -= attackPower;
//			if(Game.level.tileBeingAttacked.layer == OBJECT_LAYER) {
//				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//				calculateUsage();
//			} else if(Game.level.tileBeingAttacked.currentHp < 1) {
//				
//				Game.level.highlightedBlock.deleteTopGrassTile();
//				
//				Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//				
//				Game.level.autoTileHighlightedBlock();
//				calculateUsage();
//			}
//		}
//	}
	
	public void swing() {
		if(isInUse == false) {
			swingRemaining = 0;
			isInUse = true;	
		} else {
			swingRemaining = swingRemaining -1;
			if (swingRemaining <= 0) {
				swingRemaining = swingSpeed;
				impact();

			}
		}
	}
	
	@Override
	public abstract BaseItem createNew();
	
//	public boolean checkID(int id) {
//		if(id == AIR  || id == TALL_GRASS_A_TILE || id == TALL_GRASS_B_TILE || id == TALL_GRASS_C_TILE || 
//				id == RED_MUSHROOM_TILE || id == BROWN_MUSHROOM_TILE || id == RED_FLOWERS_TILE || id == YELLOW_FLOWERS_TILE ) {
//			return true;	
//		} else {
//			return false;
//		}
//		
//	}
	
//	public void highlightItem(Block block, int x, int y) {
//		if(Game.level.tileBeingAttacked.isPickable && Game.level.tileBeingAttacked.isAutoTileable && 
//				Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
//			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
//
//			
//			
//			//Someday maybe add the above layer 1 and 2 highlighting...
//		} else if(Game.level.tileBeingAttacked.isPickable) {
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].setColor(HIGHLIGHT_COLOR);
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].draw(x, y, Z_CHARACTER);
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.currentSpriteId].setColor(1f,1f,1f,1f);
//			
//		} else if(Game.level.tileBeingAttacked.isDiggable ) {
//			int objectTileID = Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id;
//			if (checkID(objectTileID)) {
//
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(HIGHLIGHT_COLOR);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].draw(x, y, Z_CHARACTER);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topLeftAutoTile].setColor(1f,1f,1f,1f);
//				
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(HIGHLIGHT_COLOR);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].draw(x+16, y, Z_CHARACTER);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.topRightAutoTile].setColor(1f,1f,1f,1f);
//				
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(HIGHLIGHT_COLOR);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].draw(x, y+16, Z_CHARACTER);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomLeftAutoTile].setColor(1f,1f,1f,1f);
//				
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(HIGHLIGHT_COLOR);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].draw(x+16, y+16, Z_CHARACTER);
//				Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.bottomRightAutoTile].setColor(1f,1f,1f,1f);
//			}
//		} else if(Game.level.tileBeingAttacked.isChoppable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
//			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
//		} 
//	}
	
	
	public void playHitSound() {
		Game.soundController.create(hitSound, 0.5f);
	}
}
