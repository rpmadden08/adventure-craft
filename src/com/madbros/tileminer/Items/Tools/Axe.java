package com.madbros.tileminer.Items.Tools;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Items.ToolItem;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class Axe extends ToolItem {
	public Axe() {
		id = AXE;
		name = "Axe";
		sprite = Sprites.sprites.get(Sprites.IRON_AXE);
		swingSprite = sprite;
		axePower = 5;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,32,82);
		cRectR = new Rect (46,0,32,82);
		isRepeatable = true;
		
	}
	
	@Override
	public Axe createNew() {
		return new Axe();
	}
//	public void useLeft() {
//		if(Game.hero.isSwimming == false) {
//			if(Game.level.tileBeingAttacked.isBreakable && isInRange == true) {
//				Game.level.tileBeingAttacked.currentHp -= attackPower;
//				if(Game.level.tileBeingAttacked.currentHp < 1) {
//					Game.level.highlightedBlock.deleteObjectTile();
//					Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//					
//					//Game.level.autoTileHighlightedBlock();
//				}
//			} else if(isInRange == true && !isPlacementCollidingWithHero(Game.level.highlightedBlock, Game.level.tileBeingAttacked.layer) && Game.level.tileBeingAttacked.isToolStrongEnough(this)) {
//				swing();
//			} else if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
//				Game.soundController.create("sounds/swordSwing1.wav", 0.5f);
//			}
//			
//			Game.hero.attack(this);
//		}
//			//Game.soundController.create(sound);
//	}
//	public void impact() {
//		//Game.p.allowCompletion();
//		Game.level.tileBeingAttacked.currentHp -= itemPower();
//		String tileParticle = Game.level.tileBeingAttacked.particleEffect;
//		if(Game.level.tileBeingAttacked.currentHp < 1) {
//			if(Game.level.tileBeingAttacked.breakingSound != null) {
//				Game.soundController.create(Game.level.tileBeingAttacked.breakingSound, 0.8f);
//			}
//			Game.level.highlightedBlock.deleteObjectTile();
//			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//			//Game.level.autoTileHighlightedBlock();
//			calculateUsage();
//		}
//		Game.soundController.create(Game.level.tileBeingAttacked.sound, 0.5f);
//		RectInt hBAbsRect = Game.level.highlightedBlock.absRect.getRectInt();
//		Game.particleEffectController.add(tileParticle, 
//				hBAbsRect.x +(TILE_SIZE/2), hBAbsRect.y + (TILE_SIZE/2));
//	}
//
//	
//	public void highlightItem(Block block, int x, int y) {
//		if(Game.level.tileBeingAttacked.isChoppable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
//			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
//		}
//	}
}