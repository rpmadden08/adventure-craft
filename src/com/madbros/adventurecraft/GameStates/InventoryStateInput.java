package com.madbros.adventurecraft.GameStates;

import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Game;
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

		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.currentState.standardInventory.invCrafted, Game.inventory.invClothing};
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
		for(int i = 0; i < Game.currentState.standardInventory.invCrafting.length; i++) {
			if(mouseRect.detectCollision(Game.currentState.standardInventory.invCrafting[i].slotRect)) {
				Game.currentState.standardInventory.invCrafting[i].isHighlighted = true;
				
				if(mouseLeftDown) Game.currentState.standardInventory.invCrafting[i].handleLeftClick(Game.inventory);
				else if(mouseRightDown) Game.currentState.standardInventory.invCrafting[i].handleRightClick(Game.inventory);
				droppedItemInSlot = true;
			} else {
				Game.currentState.standardInventory.invCrafting[i].isHighlighted = false;
				
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

		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.currentState.standardInventory.invCrafting, Game.currentState.standardInventory.invCrafted, Game.inventory.invClothing};
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
