package com.madbros.adventurecraft.Cells;

import static com.madbros.adventurecraft.Constants.EMPTY;

import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.ClothingItem;
import com.madbros.adventurecraft.Items.NoItem;

public class ClothingCell extends Cell{
	public ClothingCell(int x, int y, int type) {
		super(x, y, type);
	}
	
	@Override
	public void handleLeftClick(Inventory inv) {//Item heldItem, Cell[] invCrafting, Cell[]invCrafted, Inventory inv) {
		if (inv.heldItem instanceof ClothingItem) {
			ClothingItem invItem = (ClothingItem) inv.heldItem;
			if (invItem.clothingType+5 == this.type) {
			
				if(inv.heldItem.id == this.item.id && this.item.id != EMPTY) {
					int total = inv.heldItem.stackSize + this.item.stackSize;
					if(total > inv.heldItem.maxStackSize) {
						this.item.stackSize = this.item.maxStackSize;
						inv.heldItem.stackSize = total - inv.heldItem.maxStackSize;
					} else {
						this.item.stackSize = total;
						inv.heldItem = new NoItem();
					}
				} else {
					swapItems(inv);
				}
			}
		} else if(inv.heldItem.id == EMPTY) {
			swapItems(inv);
		}
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
}
