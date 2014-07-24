package com.madbros.tileminer.Items;

import com.madbros.tileminer.Block;
import com.madbros.tileminer.Sprites.AnimatedSprite;

public abstract class Clothing extends BaseItem {
	public int defensePower = 1;
	public int slotType = 0;
	public AnimatedSprite animatedSprite;
	
//	public int clothingType = 0;
	@Override
	public abstract Clothing createNew();
	
	public void useLeft() {
		NoItem tempItem = new NoItem();
		tempItem.useLeft();
	}
	
	public void highlightItem(Block block, int x, int y) {
		NoItem tempItem = new NoItem();
		tempItem.highlightItem(block, x, y);
	}
}
