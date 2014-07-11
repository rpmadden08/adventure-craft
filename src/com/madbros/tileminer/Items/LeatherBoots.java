package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class LeatherBoots extends Clothing {
	public LeatherBoots() {
		id = LEATHER_BOOTS;
		name = "Leather Boots";
		sprite = Sprites.sprites.get(Sprites.LEATHER_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_FEET_ITEM);
		defensePower = 1;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{LEATHER};
		craftCostAmount = new int[]{1};
		type = BOOTS_TYPE;
		uses = 60;
		maxUses = 60;
	}
	
	@Override
	public LeatherBoots createNew() {
		return new LeatherBoots();
	}
}
