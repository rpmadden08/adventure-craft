package com.madbros.tileminer.Items;


public abstract class CraftItem extends StackableItem {
	
	@Override
	public abstract CraftItem createNew();
}
