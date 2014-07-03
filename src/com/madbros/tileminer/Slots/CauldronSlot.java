package com.madbros.tileminer.Slots;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.tileminer.Inventory;
import com.madbros.tileminer.Sprites.Sprites;

public class CauldronSlot extends Slot{
	public boolean hasIngedients = false;
	public CauldronSlot(int x, int y) {
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
