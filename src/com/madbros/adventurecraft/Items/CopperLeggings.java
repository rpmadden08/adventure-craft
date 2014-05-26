package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class CopperLeggings extends Clothing {
	public CopperLeggings() {
		id = COPPER_LEGGINGS;
		name = "Copper Leggings";
		sprite = Sprites.sprites.get(Sprites.COPPER_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.COPPER_LEGS_ITEM);
		defensePower = 4;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{COPPER_BAR};
		craftCostAmount = new int[]{7};
	}
	
	@Override
	public CopperLeggings createNew() {
		return new CopperLeggings();
	}
}
