package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.OBJECT_LAYER;

import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.Furnace;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Slots.*;

public class InventoryStateInput extends MainStateInput {
	public void additionalKeyDown(int key){
		switch(key) {
			case Keys.E: Game.toggleInventoryState(); break; //TODO: Game.inventory.dropItemsInCraftingGrid();
		}
	}
	
	public void additionalKeyUp(int key) {
		
	}
	
	public void additionalMouseDown() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);

		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafted, Game.inventory.invClothing};
		Boolean droppedItemInSlot = false;
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					slots[i][j].isHighlighted = true;
					
					if(mouseLeftDown) slots[i][j].handleLeftClick(Game.inventory);
					else if(mouseRightDown) slots[i][j].handleRightClick(Game.inventory);
					droppedItemInSlot = true;
				} else {
					slots[i][j].isHighlighted = false;
					
				}
			}
		}
		if(Game.inventory.craftingTableOn == true) {
			for(int i = 0; i < Game.inventory.invTable.length; i++) {
				if(mouseRect.detectCollision(Game.inventory.invTable[i].slotRect)) {
					Game.inventory.invTable[i].isHighlighted = true;
					
					if(mouseLeftDown) Game.inventory.invTable[i].handleLeftClick(Game.inventory);
					else if(mouseRightDown) Game.inventory.invTable[i].handleRightClick(Game.inventory);
					droppedItemInSlot = true;
				} else {
					Game.inventory.invTable[i].isHighlighted = false;
					
				}
			}
		} else if(Game.inventory.furnaceOn == true) {
			Furnace furnaceTile = (Furnace) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][] slots2 = {furnaceTile.craftedSlot, furnaceTile.furnaceSlots};
			for(int i = 0; i < slots2.length; i++) {
				for(int j = 0; j < slots2[i].length; j++) {
					if(mouseRect.detectCollision(slots2[i][j].slotRect)) {
						slots2[i][j].isHighlighted = true;
						
						if(mouseLeftDown) slots2[i][j].handleLeftClick(Game.inventory);
						else if(mouseRightDown) slots2[i][j].handleRightClick(Game.inventory);
						droppedItemInSlot = true;
					} else {
						slots2[i][j].isHighlighted = false;
						
					}
				}
			}
			
			
//			for(int i = 0; i < Game.inventory.furnace.furnaceSlots.length; i++) {
//				if(mouseRect.detectCollision(Game.inventory.furnace.furnaceSlots[i].slotRect)) {
//					Game.inventory.furnace.furnaceSlots[i].isHighlighted = true;
//					
//					if(mouseLeftDown) Game.inventory.furnace.furnaceSlots[i].handleLeftClick(Game.inventory);
//					else if(mouseRightDown) Game.inventory.furnace.furnaceSlots[i].handleRightClick(Game.inventory);
//					droppedItemInSlot = true;
//				} else {
//					Game.inventory.furnace.furnaceSlots[i].isHighlighted = false;
//					
//				}
//			}
		}else if (Game.inventory.chestOn == true) {
			for(int i = 0; i < Game.inventory.invChest.length; i++) {
				if(mouseRect.detectCollision(Game.inventory.invChest[i].slotRect)) {
					Game.inventory.invChest[i].isHighlighted = true;
					
					if(mouseLeftDown) Game.inventory.invChest[i].handleLeftClick(Game.inventory);
					else if(mouseRightDown) Game.inventory.invChest[i].handleRightClick(Game.inventory);
					droppedItemInSlot = true;
				} else {
					Game.inventory.invChest[i].isHighlighted = false;
					
				}
			}
			
		} else {
			for(int i = 0; i < Game.inventory.invCrafting.length; i++) {
				if(mouseRect.detectCollision(Game.inventory.invCrafting[i].slotRect)) {
					Game.inventory.invCrafting[i].isHighlighted = true;
					
					if(mouseLeftDown) Game.inventory.invCrafting[i].handleLeftClick(Game.inventory);
					else if(mouseRightDown) Game.inventory.invCrafting[i].handleRightClick(Game.inventory);
					droppedItemInSlot = true;
				} else {
					Game.inventory.invCrafting[i].isHighlighted = false;
					
				}
			}
		}
		
		if(droppedItemInSlot == false) {
			if(mouseLeftDown && Game.inventory.heldItem.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(Game.inventory.heldItem.id, Game.inventory.heldItem.sprite, collectibleRect, Game.inventory.heldItem.stackSize);
				Game.inventory.heldItem.stackSize = 0;
				Game.inventory.heldItem = new NoItem();
			}
		}
	}
	
	public void additionalMouseMove() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		
		if(Game.inventory.furnaceOn) { 
			Furnace furnaceTile = (Furnace) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
			Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted, Game.inventory.invClothing, Game.inventory.invTable, Game.inventory.invChest, furnaceTile.furnaceSlots};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					if(mouseRect.detectCollision(slots[i][j].slotRect)) {
						slots[i][j].isHighlighted = true;
					} else {
						slots[i][j].isHighlighted = false;
					}
				}
			}
		} else {
			Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted, Game.inventory.invClothing, Game.inventory.invTable, Game.inventory.invChest};
			for(int i = 0; i < slots.length; i++) {
				for(int j = 0; j < slots[i].length; j++) {
					if(mouseRect.detectCollision(slots[i][j].slotRect)) {
						slots[i][j].isHighlighted = true;
					} else {
						slots[i][j].isHighlighted = false;
					}
				}
			}
		}
		
	}
	
	public void additionalMouseUp() {
		
	}
	
//	@Override
//	protected void getAdditionalMouseInput() {
//		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
//
//		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted, Game.inventory.invClothing};
//		
//		for(int i = 0; i < slots.length; i++) {
//			for(int j = 0; j < slots[i].length; j++) {
//				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
//					slots[i][j].isHighlighted = true;
//					
//					if(leftMouseButtonPressed) slots[i][j].handleLeftClick(Game.inventory);
//					else if(rightMouseButtonPressed) slots[i][j].handleRightClick(Game.inventory);
//				} else {
//					slots[i][j].isHighlighted = false;
//				}
//			}
//		}
//	}
	
}
