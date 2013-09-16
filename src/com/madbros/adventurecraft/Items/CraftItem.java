package com.madbros.adventurecraft.Items;

public abstract class CraftItem extends StackableItem {
	
	@Override
	public abstract CraftItem createNew();
}
