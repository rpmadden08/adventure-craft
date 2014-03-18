package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Inventory;
public class CraftingSlot extends Slot{
	public boolean hasIngedients = false;
	public CraftingSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	@Override
//	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
//		craftAnotherItemIfPossible(invCrafting, invCrafted);
//	}
	public void handleLeftClickCrafting(Inventory inv) {
		//System.out.println("Need to code what happens when clicked!");
		if(this.item.id == EMPTY) {
			inv.craftingMenu.resetCraftSlots(inv.craftingMenu.craftableList);
		} else {
			inv.craftingMenu.refreshCraftSlots(this.item.itemsPossiblyCraftable);
		}
	}
}
