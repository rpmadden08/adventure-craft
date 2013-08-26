package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class IronBoots extends ClothingItem {
	public IronBoots() {
		id = IRON_BOOTS;
		texture = Textures.ironBootsTexture;
		defensePower = 1;
		clothingType = BOOTS;
		maxStackSize =1;
	}
	
	@Override
	public IronBoots createNew() {
		return new IronBoots();
	}
}
