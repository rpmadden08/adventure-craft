package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class TinHelmet extends Clothing {
	public TinHelmet() {
		id = TIN_HELMET;
		name = "Tin Hat";
		sprite = Sprites.sprites.get(Sprites.TIN_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_HELMET_ITEM);
		defensePower = 2;
		slotType = HELMET_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{5};
	}
	
	@Override
	public TinHelmet createNew() {
		return new TinHelmet();
	}
}
