package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class IronLeggings extends ClothingItem {
	public IronLeggings() {
		id = IRON_LEGGINGS;
		texture = Textures.ironLeggingsTexture;
		defensePower = 1;
		clothingType = LEGGINGS;
		maxStackSize =1;
	}
	
	@Override
	public IronLeggings createNew() {
		return new IronLeggings();
	}
}
