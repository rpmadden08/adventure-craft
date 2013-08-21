package com.madbros.adventurecraft.Slots;

public class CraftingSlot extends Slot{
	public CraftingSlot(int x, int y, int type) {
		super(x, y, type);
	}
	
	@Override
	public void handleAdditional(Slot[] invCrafting, Slot[] invCrafted) {
		craftAnotherItemIfPossible(invCrafting, invCrafted);
	}
}
