package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Clothing;
import com.madbros.adventurecraft.Items.NoItem;

public class ClothingSlot extends Slot{
	public ClothingSlot(int x, int y, int type) {
		super(x, y);
		this.type = type;
	}
	
	@Override
	public void handleLeftClick(Inventory inv) {
		if (inv.heldItem instanceof Clothing) {
			Clothing heldItem = (Clothing) inv.heldItem;
			if (heldItem.slotType == this.type) {
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
					Game.hero.addClothingItem(heldItem);
					if(item instanceof Clothing) Game.hero.removeClothingItem((Clothing)item);
					swapItems(inv);
					Game.hero.calcArmor();
				}
			}
		} else if(inv.heldItem.id == EMPTY && item.id != EMPTY) {
			Clothing item = (Clothing) this.item;
			Game.hero.removeClothingItem(item);
			swapItems(inv);
			Game.hero.calcArmor();
		}
		
		handleAdditional(inv.invCrafting, inv.invCrafted);
	}
}
