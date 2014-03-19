package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.OBJECT_LAYER;

import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.TileTypes.CauldronTile;
import com.madbros.adventurecraft.TileTypes.FurnaceTile;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;
import com.madbros.adventurecraft.Items.NoItem;
import com.madbros.adventurecraft.Slots.*;

public class CraftingStateInput extends MainStateInput {
	public void additionalKeyDown(int key){
		switch(key) {
			case Keys.E: Game.toggleInventoryState(); break; //TODO: Game.inventory.dropItemsInCraftingGrid();
		}
	}
	
	public void additionalKeyUp(int key) {
		
	}
	
	public void additionalMouseDown() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);

		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.craftingMenu.craftSlots};
		//Boolean droppedItemInSlot = false;
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					slots[i][j].isHighlighted = true;
					
					if(mouseLeftDown) slots[i][j].handleLeftClickCrafting(Game.inventory);
					//else if(mouseRightDown) slots[i][j].handleRightClick(Game.inventory);
				} else {
					slots[i][j].isHighlighted = false;
				}
			}
		}
	}
	
	public void additionalMouseMove() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
			Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag};
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
	
}
