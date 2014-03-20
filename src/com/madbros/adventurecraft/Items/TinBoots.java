package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class TinBoots extends Clothing {
	public TinBoots() {
		id = TIN_BOOTS;
		name = "Tin Boots";
		sprite = Sprites.sprites.get(Sprites.TIN_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_FEET_ITEM);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{4};
	}
	
	@Override
	public TinBoots createNew() {
		return new TinBoots();
	}
}
