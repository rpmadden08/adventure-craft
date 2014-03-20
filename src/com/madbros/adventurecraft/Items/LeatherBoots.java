package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class LeatherBoots extends Clothing {
	public LeatherBoots() {
		id = LEATHER_BOOTS;
		name = "Leather Boots";
		sprite = Sprites.sprites.get(Sprites.LEATHER_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_FEET_ITEM);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{4};
	}
	
	@Override
	public LeatherBoots createNew() {
		return new LeatherBoots();
	}
}
