package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronArmor extends ClothingItem {
	public IronArmor() {
		id = IRON_ARMOR;
		name = "Iron Armor";
		sprite = Sprites.sprites.get(Sprites.PLATE_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_TORSO_ITEM);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronArmor createNew() {
		return new IronArmor();
	}
}
