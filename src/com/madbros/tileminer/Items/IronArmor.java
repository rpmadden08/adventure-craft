package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class IronArmor extends Clothing {
	public IronArmor() {
		id = IRON_ARMOR;
		name = "Iron Armor";
		sprite = Sprites.sprites.get(Sprites.PLATE_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_TORSO_ITEM);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{IRON_BAR};
		craftCostAmount = new int[]{8};
	}
	
	@Override
	public IronArmor createNew() {
		return new IronArmor();
	}
}
