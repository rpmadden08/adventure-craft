package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;

public class BagSlot extends Slot{
	public BagSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	public void handleLeftClickShift(Inventory inv) {
		if(this.item.type == HELMET_TYPE) {
			swapArmorSlots(0);
		} else if(this.item.type == ARMOR_TYPE) {
			swapArmorSlots(1);
		} else if(this.item.type == LEGGINGS_TYPE) {
			swapArmorSlots(2);
		} else if(this.item.type == BOOTS_TYPE) {
			swapArmorSlots(3);
		} else if(inv.isSlotAvailable(this.item, inv.invBar)) {
			inv.addItemToSlotArray(this.item, inv.invBar);
			inv.removeSlot(this);
		}
		Game.soundController.create("sounds/clickSelect.wav", 0.5f);
	}
	
	public void handleLeftClickShiftChest(Inventory inv) {
		if(inv.isSlotAvailable(this.item, inv.invChest)) {
			inv.addItemToSlotArray(this.item, inv.invChest);
			inv.removeSlot(this);
		} else if(inv.isSlotAvailable(this.item, inv.invBar)) {
			inv.addItemToSlotArray(this.item, inv.invBar);
			inv.removeSlot(this);
		}
		Game.soundController.create("sounds/clickSelect.wav", 0.5f);
	}
}
