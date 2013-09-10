package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronBoots extends ClothingItem {
	public IronBoots() {
		id = IRON_BOOTS;
		sprite = Sprites.ironBootsSprite;
		animatedSprite = Sprites.animatedSprites.get(Sprites.IRON_BOOTS);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronBoots createNew() {
		return new IronBoots();
	}
}
