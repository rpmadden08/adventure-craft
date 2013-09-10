package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Sprites.AnimatedSprite;

public abstract class ClothingItem extends Item {
	protected int defensePower = 1;
	public int slotType = 0;
	public AnimatedSprite animatedSprite;
	
//	public int clothingType = 0;
	@Override
	public abstract ClothingItem createNew();
}
