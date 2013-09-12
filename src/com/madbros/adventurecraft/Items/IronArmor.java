package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronArmor extends ClothingItem {
	public IronArmor() {
		id = IRON_ARMOR;
		sprite = Sprites.ironArmorSprite;
		animatedSprite = Sprites.animatedSprites.get(Sprites.IRON_ARMOR);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronArmor createNew() {
		return new IronArmor();
	}
}
