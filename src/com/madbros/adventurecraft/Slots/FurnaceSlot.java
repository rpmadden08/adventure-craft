package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;


public class FurnaceSlot extends Slot{
	public boolean hasIngedients = false;
	public FurnaceSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
	
//	@Override
//	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
//		craftAnotherItemIfPossible(invCrafting, invCrafted);
//	}
}
