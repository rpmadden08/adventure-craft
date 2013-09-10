package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.*;

public class CraftedSlot extends Slot{
	public CraftedSlot(int x, int y) {
		super(x, y);
		type = CRAFTED_SLOT;
	}
	
	@Override
	public void handleLeftClick(Inventory inv) { //Item inv.heldItem, Slot[] inv.invCrafting, Slot[]invCrafted) {
		if(inv.heldItem.id == EMPTY) {
			swapItems(inv);
			removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, inv.invCrafting);
			craftAnotherItemIfPossible(inv.invCrafting, inv.invCrafted);
		} else if (inv.heldItem.id == this.item.id) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total <= inv.heldItem.maxStackSize) {
				inv.heldItem.stackSize = total;
				this.item = new NoItem();
				removeRecipeItemsFromCraftingSlots(inv.heldItem.craftCost, inv.invCrafting);
				craftAnotherItemIfPossible(inv.invCrafting, inv.invCrafted);
			}
		}
	}
	
	@Override
	public void handleRightClick(Inventory inv) {
		handleLeftClick(inv);	//because it is effectively handled the same
	}
}
