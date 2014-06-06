package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

public class CauldronSlot extends Slot{
	public boolean hasIngedients = false;
	public CauldronSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
//	@Override
//	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
//		craftAnotherItemIfPossible(invCrafting, invCrafted);
//	}
}
