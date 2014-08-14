package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Items.Item;
import com.madbros.tileminer.TileTypes.CauldronTile;
public class BrewingSlot extends CraftingSlot{
	
	public BrewingSlot(int x, int y) {
		super(x, y);
	}
	
	public void handleLeftClick(Inventory inv) {
		
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			
			CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
			if(cauldron.cauldronSlots[0].item.id == 0) {
				cauldron.cauldronSlots[0].item = ITEM_HASH.get(this.item.id).createNew();
			}else if(cauldron.cauldronSlots[0].item.id == this.item.id) {
				cauldron.cauldronSlots[0].item.stackSize = cauldron.cauldronSlots[0].item.stackSize +1;
			} else { 
				return;
			}
			
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			if(this.item.areIngredientsInInventory()) {
				this.isInactive = false;
			} else {
				this.isInactive = true;
			}
			
			if(isSoundLooping == false) {
			Game.soundController.create("sounds/clickSelect.wav", 0.5f);
			}
			
		}
	}
	public void handleLeftClickShift(Inventory inv) {
		isSoundLooping = true;
		CauldronTile cauldron = (CauldronTile) Game.level.activeBlocks[inv.currentInvActiveBlockX][inv.currentInvActiveBlockY].layers[OBJECT_LAYER];
		if(cauldron.cauldronSlots[0].item.id == 0 || cauldron.cauldronSlots[0].item.id == this.item.id) {
			while(cauldron.cauldronSlots[0].item.stackSize + this.item.stackSize <= cauldron.cauldronSlots[0].item.maxStackSize) {
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