package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class CopperArmor extends Clothing {
	public CopperArmor() {
		id = COPPER_ARMOR;
		name = "Copper Armor";
		sprite = Sprites.sprites.get(Sprites.COPPER_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.COPPER_TORSO_ITEM);
		defensePower = 5;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{COPPER_BAR};
		craftCostAmount = new int[]{8};
		type = ARMOR_TYPE;
		uses = 120;
		maxUses = 120;
	}
	
	@Override
	public CopperArmor createNew() {
		return new CopperArmor();
	}
}
