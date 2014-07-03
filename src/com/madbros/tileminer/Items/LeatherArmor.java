package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class LeatherArmor extends Clothing {
	public LeatherArmor() {
		id = LEATHER_ARMOR;
		name = "Leather Armor";
		sprite = Sprites.sprites.get(Sprites.LEATHER_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_TORSO_ITEM);
		defensePower = 2;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{LEATHER};
		craftCostAmount = new int[]{3};
		type = ARMOR_TYPE;
	}
	
	@Override
	public LeatherArmor createNew() {
		return new LeatherArmor();
	}
}
