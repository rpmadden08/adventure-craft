package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class IronBoots extends Clothing {
	public IronBoots() {
		id = IRON_BOOTS;
		name = "Iron Boots";
		sprite = Sprites.sprites.get(Sprites.PLATE_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_FEET_ITEM);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{IRON_BAR};
		craftCostAmount = new int[]{4};
	}
	
	@Override
	public IronBoots createNew() {
		return new IronBoots();
	}
}
