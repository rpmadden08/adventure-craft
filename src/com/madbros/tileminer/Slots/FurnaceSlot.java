package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Inventory;


public class FurnaceSlot extends Slot{
	public boolean hasIngedients = false;
	public FurnaceSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
	public void render() {		
		item.render(slotRect);
	}
	
	public void handleLeftClick(Inventory inv) {
		
	}
	
	public void handleRightClick(Inventory inv) {
		
	}
}
