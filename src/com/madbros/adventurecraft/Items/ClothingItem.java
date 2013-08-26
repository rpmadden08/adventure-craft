package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Item;

public abstract class ClothingItem extends Item {
	protected int defensePower = 1;
	public int clothingType = 0;
	@Override
	public abstract ClothingItem createNew();
}
