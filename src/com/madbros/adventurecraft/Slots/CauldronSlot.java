package com.madbros.adventurecraft.Slots;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Inventory;
import com.madbros.adventurecraft.Items.Item;
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
