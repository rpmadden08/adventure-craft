package com.madbros.tileminer.Items;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class WoodenPick extends Pick {
	public WoodenPick() {
		id = WOODEN_PICK;
		name = "Wooden Pickaxe";
		sprite = Sprites.sprites.get(Sprites.WOODEN_PICK);
		swingSprite = sprite;
		attackPower = 2;
		itemPower = 4;
		isRepeatable = true;
		craftCost = new int[]{PLANK, STICK};
		craftCostAmount = new int[]{3, 2};
		maxUses = 15;// 15
		uses = 15;
		
	}
	
	@Override
	public WoodenPick createNew() {
		return new WoodenPick();
	}
	
//	public void useLeft() {
//		
////		Tile topTile = Game.level.highlightedBlock.getTopTile();
//		
//		if(Game.level.tileBeingAttacked.isPickable && Game.level.tileBeingAttacked2.isPickable 
//				&& Game.level.tileBeingAttacked3.isPickable 
//				&& Game.level.tileBeingAttacked4.isPickable &&isInRange == true) {
//			swing();
//
//		}
//		Game.hero.attack(this);
//	}
//	public void impact() {
//		Game.level.tileBeingAttacked.currentHp -= attackPower;
//		if(Game.level.tileBeingAttacked.currentHp < 1) {
//			Game.level.highlightedBlock.deleteObjectTile();
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].deleteObjectTile();
//			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].deleteObjectTile();
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].deleteObjectTile();
//			
//			Game.level.highlightedBlock.collisionTile = null;
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY].collisionTile = null;
//			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY+1].collisionTile = null;
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY+1].collisionTile = null;
//			
//			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_1);
//			Game.level.activeBlocks[Game.level.highlightedBlockX][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY-2].deleteTile(ABOVE_LAYER_1);
//			Game.level.activeBlocks[Game.level.highlightedBlockX+1][Game.level.highlightedBlockY-1].deleteTile(ABOVE_LAYER_1);
//			
//			Game.level.tileBeingAttacked.deleteMe(Game.level.highlightedBlockX, Game.level.highlightedBlockY, Game.level.activeBlocks);
//			
//			
//			Game.level.autoTileBlock(Game.level.highlightedBlockX, Game.level.highlightedBlockY-2);
//			Game.level.autoTileHighlightedBlock();
//			calculateUsage();
//		}
//	}
//	
//	public void highlightItem(Block block, int x, int y) {
//		
//		if(Game.level.tileBeingAttacked.isPickable && 
//				Game.level.tileBeingAttacked2.isPickable && 
//				Game.level.tileBeingAttacked3.isPickable && 
//				Game.level.tileBeingAttacked4.isPickable) {
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
//			//Someday maybe add the above layer 1 highlighting...
//		}
//	}
}
