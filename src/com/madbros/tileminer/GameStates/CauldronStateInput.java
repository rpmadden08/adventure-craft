package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.OBJECT_LAYER;

import com.badlogic.gdx.Input.Keys;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Items.NoItem;
import com.madbros.tileminer.Slots.*;
import com.madbros.tileminer.TileTypes.CauldronTile;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Rect;

public class CauldronStateInput extends InventoryStateInput {
	public void additionalKeyDown(int key){
		switch(key) {
			case Keys.E: Game.toggleCauldronState(); break; //TODO: Game.inventory.dropItemsInCraftingGrid();
			case Keys.A: Game.inventory.craftingMenu.lastPage2(); break;
			case Keys.D: Game.inventory.craftingMenu.nextPage2(); break;
			case Keys.ALT_LEFT: altKeyDown = true; break; //TODO: Game.inventory.dropItemsInCraftingGrid();
			case Keys.SHIFT_LEFT: shiftKeyDown = true; break; 
			case Keys.SHIFT_RIGHT: shiftKeyDown = true; break; 
		}
	}
	
	public void additionalKeyUp(int key) {
		switch(key) {
			case Keys.ALT_LEFT: altKeyDown = false; break; //TODO: Game.inventory.dropItemsInCraftingGrid();
			case Keys.SHIFT_LEFT: shiftKeyDown = false; break; 
			case Keys.SHIFT_RIGHT: shiftKeyDown = false; break; 
		}
	}
	
	public boolean touchUp(int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer, button);
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Game.inventory.craftingMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(cauldronTile.cauldronSlots[0].item.id != 0) {
			cauldronTile.cauldronSlots[0].handleMouseInput(mouseLeftDown, mouseLeftUp, mouseRect);
		}
		return false;
	}
	
	public void additionalMouseDown() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Game.inventory.craftingMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		Boolean droppedItemInSlot = false;
		CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(cauldronTile.cauldronSlots[0].item.id != 0) {
			cauldronTile.cauldronSlots[0].handleMouseInput(mouseLeftDown, mouseLeftUp, mouseRect);
		}
		Slot[][] slots2 = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.craftingMenu.craftSlots, Game.inventory.invClothing, cauldronTile.craftedSlot, cauldronTile.cauldronSlots, cauldronTile.fuelSlot};
		for(int i = 0; i < slots2.length; i++) {
			for(int j = 0; j < slots2[i].length; j++) {
				if(mouseRect.detectCollision(slots2[i][j].slotRect)) {
					slots2[i][j].isHighlighted = true;
					
					if(mouseLeftDown && altKeyDown) slots2[i][j].handleLeftClickCrafting(Game.inventory, Game.inventory.craftingMenu.brewableList, slots2[i][j].item.itemsPossiblyBrewable);
					else if(mouseLeftDown && shiftKeyDown) slots2[i][j].handleLeftClickShift(Game.inventory);
					else if(mouseRightDown && shiftKeyDown) slots2[i][j].handleRightClickShift(Game.inventory);
					else if(mouseLeftDown) slots2[i][j].handleLeftClick(Game.inventory);
					else if(mouseRightDown) slots2[i][j].handleRightClick(Game.inventory);
					droppedItemInSlot = true;
				} else {
					slots2[i][j].isHighlighted = false;
					
				}
			}
		}
		
		if(droppedItemInSlot == false) {
			if(mouseLeftDown && Game.inventory.heldItem.id != 0) {
				Rect collectibleRect = new Rect(Game.hero.absRect.x, Game.hero.absRect.y, 16, 16);
				Game.collectibleController.add(Game.inventory.heldItem.id, Game.inventory.heldItem.sprite, collectibleRect, Game.inventory.heldItem.stackSize, Game.inventory.heldItem.uses);
				Game.inventory.heldItem.stackSize = 0;
				Game.inventory.heldItem = new NoItem();
			}
		}
	}
	
	public void additionalMouseMove() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Game.inventory.craftingMenu.handleMouseMove(mouseRect.x, mouseRect.y);
		
		CauldronTile cauldronTile = (CauldronTile) Game.level.activeBlocks[Game.inventory.currentInvActiveBlockX][Game.inventory.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(cauldronTile.cauldronSlots[0].item.id != 0) {
			cauldronTile.cauldronSlots[0].handleMouseMove(mouseRect.x, mouseRect.y);
		}
		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.craftingMenu.craftSlots, Game.inventory.invCrafted, Game.inventory.invClothing,cauldronTile.cauldronSlots, cauldronTile.fuelSlot, cauldronTile.craftedSlot};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					if(slots[i][j].isHighlighted == false) {
						Game.soundController.create("sounds/mouseOver.wav", 0.15f);
					}
					slots[i][j].isHighlighted = true;
				} else {
					slots[i][j].isHighlighted = false;
				}
			}
		}
	
		
	}
	
	public void additionalMouseUp() {
		
	}
	
}
