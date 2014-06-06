package com.madbros.tileminer.Items;


public abstract class StackableItem extends BaseItem {
	public StackableItem() {
		maxStackSize = 99;
	}
	
	@Override
	public abstract StackableItem createNew();
}
