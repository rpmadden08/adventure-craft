package com.madbros.adventurecraft.Items;


public abstract class StackableItem extends BaseItem {
	public StackableItem() {
		maxStackSize = 99;
	}
	
	@Override
	public abstract StackableItem createNew();
}
