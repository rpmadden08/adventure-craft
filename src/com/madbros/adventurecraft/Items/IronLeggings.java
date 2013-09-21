package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronLeggings extends ClothingItem {
	public IronLeggings() {
		id = IRON_LEGGINGS;
		sprite = Sprites.sprites.get(Sprites.PLATE_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_LEGS_ITEM);
		defensePower = 1;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronLeggings createNew() {
		return new IronLeggings();
	}
}
