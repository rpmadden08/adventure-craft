package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Slots.Slot;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Stick extends CraftItem {
	public Stick() {
		id = STICK;
		name = "Stick";
		sprite = Sprites.sprites.get(Sprites.STICK);
		numberProducedByCrafting = 4;
		itemsPossiblyCraftable = new int[]{};
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{2};
		//set recipes
	}
	
	@Override
	public boolean isValidRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == PLANK && craftingSlots[1].item.id == PLANK) {
			return true;
		} else if(craftingSlots[2].item.id == PLANK && craftingSlots[3].item.id == PLANK) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isValidTableRecipe(Slot[] craftingSlots) {
		if(craftingSlots[0].item.id == PLANK && craftingSlots[1].item.id == PLANK) {
			return true;
		} else if(craftingSlots[1].item.id == PLANK && craftingSlots[2].item.id == PLANK) {
			return true;
		} else if(craftingSlots[3].item.id == PLANK && craftingSlots[4].item.id == PLANK) {
			return true;
		} else if(craftingSlots[4].item.id == PLANK && craftingSlots[5].item.id == PLANK) {
			return true;
		} else if(craftingSlots[6].item.id == PLANK && craftingSlots[7].item.id == PLANK) {
			return true;
		} else if(craftingSlots[7].item.id == PLANK && craftingSlots[8].item.id == PLANK) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Stick createNew() {
		return new Stick();
	}
}
