package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronHelmet extends ClothingItem {
	public IronHelmet() {
		id = IRON_HELMET;
		sprite = Sprites.ironHelmetSprite;
		animatedSprite = Sprites.animatedSprites.get(Sprites.IRON_HELMET);
		defensePower = 1;
		slotType = HELMET_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronHelmet createNew() {
		return new IronHelmet();
	}
}
