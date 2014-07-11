package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;

public class ChestSlot extends Slot{
	public ChestSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	public void handleLeftClickShiftChest(Inventory inv) {
		if(inv.isSlotAvailable(this.item, inv.invBar)) {
			inv.addItemToSlotArray(this.item, inv.invBar);
			inv.removeSlot(this);
			Game.soundController.create("sounds/clickSelect.wav", 1f);
		} else if(inv.isSlotAvailable(this.item, inv.invBag)) {
			inv.addItemToSlotArray(this.item, inv.invBag);
			inv.removeSlot(this);
			Game.soundController.create("sounds/clickSelect.wav", 1f);
		}
	}
}
