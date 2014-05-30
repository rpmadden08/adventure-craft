package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

public class StoneAxe extends Axe {
	public StoneAxe() {
		id = STONE_AXE;
		name = "Stone Axe";
		sprite = Sprites.sprites.get(Sprites.STONE_AXE);
		swingSprite = sprite;
		attackPower = 3;
		itemPower = 3;
		is32 = true;
		isInUse = false;
		sound = "sounds/axeChop.wav";
		craftCost = new int[]{STONE, STICK};
		craftCostAmount = new int[]{3, 2};
		maxUses = 40;
		uses = 40;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
		isRepeatable = true;
		
	}
	
	@Override
	public StoneAxe createNew() {
		return new StoneAxe();
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
