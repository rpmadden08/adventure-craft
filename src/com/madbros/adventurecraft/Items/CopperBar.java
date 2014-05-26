package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.*;

public class CopperBar extends CraftItem {
	public CopperBar() {
		id = COPPER_BAR;
		name = "Copper Bar";
		sprite = Sprites.sprites.get(Sprites.COPPER_BAR);
		itemsPossiblyCraftable = new int[]{COPPER_ARMOR, COPPER_AXE, COPPER_BOOTS, COPPER_HELMET, COPPER_HOE, COPPER_LEGGINGS, COPPER_PICK, COPPER_SHOVEL, COPPER_SWORD};
	}
	
	@Override
	public CopperBar createNew() {
		return new CopperBar();
	}
	
	public boolean isValidFurnaceRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == COPPER_ITEM) {
			return true;
		} else {
			return false;
		}
	}
}
