package com.madbros.adventurecraft;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Slots.CraftingSlot;
import com.madbros.adventurecraft.Slots.Slot;

public class CraftingMenu {
	public CraftingSlot[] craftSlots= new CraftingSlot[INV_LENGTH * INV_HEIGHT];
	public int[] craftableList = {
		CAULDRON, CHEST, COPPER_ARMOR, COPPER_AXE, COPPER_BOOTS, COPPER_HELMET, COPPER_HOE, COPPER_LEGGINGS, COPPER_PICK, COPPER_SHOVEL, COPPER_SWORD, FURNACE, IRON_ARMOR, IRON_AXE, IRON_BOOTS, IRON_HELMET, IRON_HOE, IRON_LEGGINGS, IRON_PICK, IRON_SHOVEL, IRON_SWORD, LEATHER_ARMOR, LEATHER_BOOTS, LEATHER_HAT, LEATHER_LEGGINGS, PLANK, STICK, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD, TABLE, TIN_ARMOR, TIN_AXE, TIN_BOOTS, TIN_HELMET, TIN_HOE, TIN_LEGGINGS, TIN_PICK, TIN_SHOVEL, TIN_SWORD, TORCH, WOODEN_AXE, WOODEN_HOE, WOODEN_PICK, WOODEN_SHOVEL, WOODEN_SWORD
	};
	public int[] currentCraftableList = craftableList;
	
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
