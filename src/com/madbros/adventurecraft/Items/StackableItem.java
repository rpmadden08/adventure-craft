package com.madbros.adventurecraft.Items;


public abstract class StackableItem extends Item {
	public StackableItem() {
		maxStackSize = 99;
	}
	
	@Override
	public abstract StackableItem createNew();
}
