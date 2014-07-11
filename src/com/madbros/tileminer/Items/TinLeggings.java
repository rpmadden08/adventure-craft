package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class TinLeggings extends Clothing {
	public TinLeggings() {
		id = TIN_LEGGINGS;
		name = "Tin Leggings";
		sprite = Sprites.sprites.get(Sprites.TIN_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_LEGS_ITEM);
		defensePower = 2;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{7};
		type = LEGGINGS_TYPE;
		uses = 90;
		maxUses = 90;
	}
	
	@Override
	public TinLeggings createNew() {
		return new TinLeggings();
	}
}
