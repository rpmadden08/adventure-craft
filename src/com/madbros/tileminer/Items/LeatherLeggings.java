package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class LeatherLeggings extends Clothing {
	public LeatherLeggings() {
		id = LEATHER_LEGGINGS;
		name = "Leather Leggings";
		sprite = Sprites.sprites.get(Sprites.LEATHER_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_LEGS_ITEM);
		defensePower = 1;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{LEATHER};
		craftCostAmount = new int[]{2};
	}
	
	@Override
	public LeatherLeggings createNew() {
		return new LeatherLeggings();
	}
}
