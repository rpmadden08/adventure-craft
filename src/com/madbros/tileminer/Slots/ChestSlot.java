package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

public class ChestSlot extends Slot{
	public ChestSlot(int x, int y) {
		super(x, y);
		type = CRAFTING_SLOT;
	}
}
