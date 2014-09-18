package com.madbros.tileminer.Items.Clothing;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class CopperBoots extends Clothing {
	public CopperBoots() {
		id = COPPER_BOOTS;
		name = "Copper Boots";
		sprite = Sprites.sprites.get(Sprites.COPPER_FEET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.COPPER_FEET_ITEM);
		defensePower = 3;
		slotType = BOOTS_SLOT;
		maxStackSize =1;
		craftCost = new int[]{COPPER_BAR};
		craftCostAmount = new int[]{4};
		type = BOOTS_TYPE;
		uses = 100;
		maxUses = 100;
	}
	
	@Override
	public CopperBoots createNew() {
		return new CopperBoots();
	}
}
