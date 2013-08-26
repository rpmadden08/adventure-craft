package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class IronHelmet extends ClothingItem {
	public IronHelmet() {
		id = IRON_HELMET;
		texture = Textures.ironHelmetTexture;
		defensePower = 1;
		clothingType = HELMET;
		maxStackSize =1;
	}
	
	@Override
	public IronHelmet createNew() {
		return new IronHelmet();
	}
}
