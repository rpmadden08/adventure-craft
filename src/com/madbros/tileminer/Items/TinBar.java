package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Sprites.*;

public class TinBar extends CraftItem {
	public TinBar() {
		id = TIN_BAR;
		name = "Tin Bar";
		sprite = Sprites.sprites.get(Sprites.TIN_BAR);
		itemsPossiblyCraftable = new int[]{TIN_ARMOR, TIN_AXE, TIN_BOOTS, TIN_HELMET, TIN_HOE, TIN_LEGGINGS, TIN_PICK, TIN_SHOVEL, TIN_SWORD};
		craftCost = new int[]{TIN_ITEM};
		craftCostAmount = new int[]{1}; 
		workSpaceNeeded = new int[]{FURNACE_WORKSPACE};
	}
	
	@Override
	public TinBar createNew() {
		return new TinBar();
	}
	
	@Override
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == TIN_ITEM) {
			return true;
		} else {
			return false;
		}
	}
}
