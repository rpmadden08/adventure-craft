package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronBoots extends ClothingItem {
	public IronBoots() {
		id = IRON_BOOTS;
		sprite = Sprites.sprites.get(Sprites.PLATE_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_FEET_ITEM);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronBoots createNew() {
		return new IronBoots();
	}
}
