package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class IronArmor extends ClothingItem {
	public IronArmor() {
		id = IRON_ARMOR;
		texture = Textures.ironArmorTexture;
		defensePower = 1;
		clothingType = ARMOR;
		maxStackSize =1;
	}
	
	@Override
	public IronArmor createNew() {
		return new IronArmor();
	}
}
