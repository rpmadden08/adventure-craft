package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class IronHelmet extends ClothingItem {
	public IronHelmet() {
		id = IRON_HELMET;
		name = "Iron Helmet";
		sprite = Sprites.sprites.get(Sprites.PLATE_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_HELMET_ITEM);
		defensePower = 1;
		slotType = HELMET_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public IronHelmet createNew() {
		return new IronHelmet();
	}
}
