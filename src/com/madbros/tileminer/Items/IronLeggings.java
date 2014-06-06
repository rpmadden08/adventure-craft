package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class IronLeggings extends Clothing {
	public IronLeggings() {
		id = IRON_LEGGINGS;
		name = "Iron Leggings";
		sprite = Sprites.sprites.get(Sprites.PLATE_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_LEGS_ITEM);
		defensePower = 1;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{IRON_BAR};
		craftCostAmount = new int[]{7};
	}
	
	@Override
	public IronLeggings createNew() {
		return new IronLeggings();
	}
}
