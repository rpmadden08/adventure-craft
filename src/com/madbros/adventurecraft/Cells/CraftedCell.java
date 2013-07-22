package com.madbros.adventurecraft.Cells;

import static com.madbros.adventurecraft.Constants.EMPTY;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Items.*;

public class CraftedCell extends Cell{
	public CraftedCell(int x, int y, int type) {
		super(x, y, type);
	}
	
	@Override
	public void handleLeftClick(Inventory inv) { //Item inv.heldItem, Cell[] inv.invCrafting, Cell[]invCrafted) {
		if(inv.heldItem.id == EMPTY) {
			swapItems(inv);
			removeRecipeItemsFromCraftingCells(inv.heldItem.craftCost, inv.invCrafting);
			craftAnotherItemIfPossible(inv.invCrafting, inv.invCrafted);
		} else if (inv.heldItem.id == this.item.id) {
			int total = inv.heldItem.stackSize + this.item.stackSize;
			if(total <= inv.heldItem.maxStackSize) {
				inv.heldItem.stackSize = total;
				this.item = new NoItem();
				removeRecipeItemsFromCraftingCells(inv.heldItem.craftCost, inv.invCrafting);
				craftAnotherItemIfPossible(inv.invCrafting, inv.invCrafted);
			}
		}
	}
	
	@Override
	public void handleRightClick(Inventory inv) {
		handleLeftClick(inv);	//because it is effectively handled the same
	}
}
