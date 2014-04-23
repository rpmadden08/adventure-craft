package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class WoodenAxe extends Axe {
	public WoodenAxe() {
		id = WOODEN_AXE;
		name = "Wooden Axe";
		sprite = Sprites.sprites.get(Sprites.WOODEN_AXE);
		swingSprite = sprite;
		attackPower = 2;
		itemPower = 2;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		craftCost = new int[]{PLANK, STICK};
		craftCostAmount = new int[]{3, 2};
		maxUses = 15;
		uses = 15;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
		isRepeatable = true;
		
		craftCost = new int[]{STICK,PLANK};
		craftCostAmount = new int[]{2, 3};
		
	}
	
	@Override
	public WoodenAxe createNew() {
		return new WoodenAxe();
	}
//	public void impact() {
//		//Game.p.allowCompletion();
//		Game.level.tileBeingAttacked.currentHp -= attackPower;
//		if(Game.level.tileBeingAttacked.currentHp < 1) {
//			Game.level.highlightedBlock.deleteObjectTile();
//			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//			//Game.level.autoTileHighlightedBlock();
//			calculateUsage();
//		}
//		Game.soundController.create(sound, 1);
//		Game.particleEffectController.add("Chunks.p", 
//				Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2), Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2));
//
////		Game.p.x= Game.level.highlightedBlock.absRect.x +(TILE_SIZE/2);
////		Game.p.y = Game.level.highlightedBlock.absRect.y + (TILE_SIZE/2);
////		Game.p.start();
//	}
//	public void useLeft() {
//		if(Game.level.tileBeingAttacked.isChoppable && isInRange == true) {
//			swing();
//		}
//		
//			Game.hero.attack(this);
//			//Game.soundController.create(sound);
//	}
//	
//	public void highlightItem(Block block, int x, int y) {
//		if(Game.level.tileBeingAttacked.isChoppable && Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id != AIR) {
//			Game.level.tileBeingAttacked.highlightEntireObject(block.getX(Game.level.activeBlocks),block.getY(Game.level.activeBlocks), x, y);
//		}
//	}
}
