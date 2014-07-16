package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Inventory;

public class BarSlot extends Slot{
	public int currentlySelectedBarSlot = 0;
	public BarSlot(int x, int y, int currentlySelectedSlot) {
		super(x, y);
		type = CRAFTING_SLOT;
		currentlySelectedBarSlot = currentlySelectedSlot;
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
		} else if(inv.isSlotAvailable(this.item, inv.invBag)) {
			inv.addItemToSlotArray(this.item, inv.invBag);
			inv.removeSlot(this);
		}
		Game.soundController.create("sounds/clickSelect.wav", 0.5f);
	}
	public void handleLeftClickShiftChest(Inventory inv) {
		if(inv.isSlotAvailable(this.item, inv.invChest)) {
			inv.addItemToSlotArray(this.item, inv.invChest);
			inv.removeSlot(this);
		} else if(inv.isSlotAvailable(this.item, inv.invBag)) {
			inv.addItemToSlotArray(this.item, inv.invBag);
			inv.removeSlot(this);
		}
		Game.soundController.create("sounds/clickSelect.wav", 0.5f);
	}
	
	public void handleLeftClickMainState(Inventory inv) {
		inv.itemSelected = this.currentlySelectedBarSlot;
	}
	
}
