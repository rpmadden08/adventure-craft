package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Item;
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
		if(this.item.id == EMPTY || this.isInactive == true) {
		} else {
			//Add items to inventory.  
			
			for(int x = 0; x < this.item.craftCost.length; x++) {
				Item removedItem = ITEM_HASH.get(this.item.craftCost[x]).createNew();
				Game.inventory.remove(removedItem, this.item.craftCostAmount[x]);
			}
			System.out.println(this.item.id);
			Game.inventory.add(this.item, this.item.stackSize);
			System.out.println(this.item.stackSize);
		}
	}
}
