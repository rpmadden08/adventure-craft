package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;


import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.TileTypes.FurnaceTile;
public class BurningSlot extends CraftingSlot{
	
	public BurningSlot(int x, int y) {
		super(x, y);
	}
	
	public void handleLeftClick(Inventory inv) {
		
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			
			FurnaceTile furnace = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			if(furnace.furnaceSlots[0].item.id == 0) {
				furnace.furnaceSlots[0].item = ITEM_HASH.get(this.item.id).createNew();
			}else if(furnace.furnaceSlots[0].item.id == this.item.id) {
				furnace.furnaceSlots[0].item.stackSize = furnace.furnaceSlots[0].item.stackSize +1;
			} else { 
				return;
			}
			
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			if(isSoundLooping = false) {
				Game.soundController.create("sounds/clickSelect.wav", 0.5f);
			}
			if(this.item.areIngredientsInInventory()) {
				this.isInactive = false;
			} else {
				this.isInactive = true;
			}
			
		}
	}
	public void handleLeftClickShift(Inventory inv) {
		isSoundLooping = true;
		FurnaceTile furnace = (FurnaceTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(furnace.furnaceSlots[0].item.id == 0 || furnace.furnaceSlots[0].item.id == this.item.id) {
			while(furnace.furnaceSlots[0].item.stackSize + this.item.stackSize <= furnace.furnaceSlots[0].item.maxStackSize) {
				if(isInactive) {
					return;
				} else {
					handleLeftClick(inv);
				}
			}
		}
		
		Game.soundController.create("sounds/clickSelect.wav", 0.5f);
		isSoundLooping = false;
	}
}