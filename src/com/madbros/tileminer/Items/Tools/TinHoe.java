package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class TinHoe extends Hoe {
	public TinHoe() {
		id = TIN_HOE;
		name = "Tin Hoe";
		sprite = Sprites.sprites.get(Sprites.TIN_HOE);
		swingSprite = sprite;
		attackPower = 4;
		isRepeatable = true;
		craftCost = new int[]{TIN_BAR, STICK};
		craftCostAmount = new int[]{2, 2};
		maxUses = 100;
		uses = 100;
	}
	
	@Override
	public TinHoe createNew() {
		return new TinHoe();
	}
	
//	public void useLeft() {
//		
////		Tile topTile = Game.level.highlightedBlock.getTopTile();
//		
//		if(Game.level.tileBeingAttacked.isTillable && Game.level.tileBeingAttacked2.isTillable 
//				&& Game.level.tileBeingAttacked3.isTillable 
//				&& Game.level.tileBeingAttacked4.isTillable &&isInRange == true) {
//			swing();
//
//		}
//		Game.hero.attack(this);
//	}
//	public void impact() {
//		Game.level.tileBeingAttacked.currentHp -= attackPower;
//		if(Game.level.tileBeingAttacked.currentHp < 1) {
//			Game.level.highlightedBlock.deleteTopTileTilled();
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteTopTileTilled();
//			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteTopTileTilled();
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteTopTileTilled();
//			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//			
//			Game.level.autoTileHighlightedBlock();
//			calculateUsage();
//		}
//	}
//	
//	public void highlightItem(Block block, int x, int y) {
//		
//		if(Game.level.tileBeingAttacked.isTillable && 
//				Game.level.tileBeingAttacked2.isTillable && 
//				Game.level.tileBeingAttacked3.isTillable && 
//				Game.level.tileBeingAttacked4.isTillable && 
//				Game.level.activeBlocks[block.getX(Game.level.activeBlocks)][block.getY(Game.level.activeBlocks)].layers[OBJECT_LAYER].id == AIR) {
//
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].setColor(HIGHLIGHT_COLOR);
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].draw(x, y, Z_CHARACTER);
//			Game.level.tileBeingAttacked.sprites[Game.level.tileBeingAttacked.autoTile].setColor(1f,1f,1f,1f);
//			
//			Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].setColor(HIGHLIGHT_COLOR);
//			Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].draw(x+32, y, Z_CHARACTER);
//			Game.level.tileBeingAttacked2.sprites[Game.level.tileBeingAttacked2.autoTile].setColor(1f,1f,1f,1f);
//			
//			Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].setColor(HIGHLIGHT_COLOR);
//			Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].draw(x, y+32, Z_CHARACTER);
//			Game.level.tileBeingAttacked3.sprites[Game.level.tileBeingAttacked3.autoTile].setColor(1f,1f,1f,1f);
//			
//			Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].setColor(HIGHLIGHT_COLOR);
//			Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].draw(x+32, y+32, Z_CHARACTER);
//			Game.level.tileBeingAttacked4.sprites[Game.level.tileBeingAttacked4.autoTile].setColor(1f,1f,1f,1f);
//		}
//	}
}
