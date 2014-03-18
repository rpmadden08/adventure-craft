package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Slots.CraftingSlot;
import com.madbros.adventurecraft.Slots.Slot;

public class CraftingMenu {
	public CraftingSlot[] craftSlots= new CraftingSlot[INV_LENGTH * INV_HEIGHT];
	public int[] craftableList = {
		STICK, PLANK
	};
	
	public CraftingMenu() {
		int k = 0;
		for(int x = 0; x < INV_LENGTH; x++) {
			for(int y = 0; y < INV_HEIGHT; y++) {
				craftSlots[k] = new CraftingSlot(INV_BAG_RECT.x + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.right) * y+200,
									 INV_BAG_RECT.y + (INV_SLOT_SIZE + INV_MENU_SLOT_MARGIN.bottom) * x);
				k++;
			}
		}
//		for(int x = 0; x <craftableList.length; x++) {
//			craftSlots[x].item = ITEM_HASH.get(craftableList[x]).createNew();
//			craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
//			if(craftSlots[x].item.areIngredientsInInventory()) {
//				craftSlots[x].isInactive = false;
//			} else {
//				craftSlots[x].isInactive = true;
//			}
//		}
		
	}
	
	public void refreshCraftSlots(int[] itemsPossiblyCraftable) {
		for(int x = 0; x <craftSlots.length; x++) {
			if(x < itemsPossiblyCraftable.length) {
				
				craftSlots[x].item = ITEM_HASH.get(itemsPossiblyCraftable[x]).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
				//If the item is craftable then do nothing.  If it is then mark the slot as uncraftable.  
				if(craftSlots[x].item.areIngredientsInInventory()) {
					craftSlots[x].isInactive = false;
				} else {
					craftSlots[x].isInactive = true;
				}
			} else {
				craftSlots[x].item = ITEM_HASH.get(EMPTY).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			}
		}
	}
	
	
	public void resetCraftSlots(int[] itemsPossiblyCraftable) {
		for(int x = 0; x <craftSlots.length; x++) {
			if(x < itemsPossiblyCraftable.length) {
				craftSlots[x].item = ITEM_HASH.get(craftableList[x]).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			} else {
				craftSlots[x].item = ITEM_HASH.get(EMPTY).createNew();
				craftSlots[x].item.stackSize = craftSlots[x].item.numberProducedByCrafting;
			}
		}
	}
}
