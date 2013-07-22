package com.madbros.adventurecraft.Cells;

import com.madbros.adventurecraft.*;

public class CraftingCell extends Cell{
	public CraftingCell(int x, int y, int type) {
		super(x, y, type);
	}
	
	@Override
	public void handleAdditional(Cell[] invCrafting, Cell[] invCrafted) {
		craftAnotherItemIfPossible(invCrafting, invCrafted);
	}
}
