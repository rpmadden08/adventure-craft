package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class TinArmor extends Clothing {
	public TinArmor() {
		id = TIN_ARMOR;
		name = "Tin Armor";
		sprite = Sprites.sprites.get(Sprites.TIN_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_TORSO_ITEM);
		defensePower = 3;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{8};
		type = ARMOR_TYPE;
		uses = 100;
		maxUses = 100;
	}
	
	@Override
	public TinArmor createNew() {
		return new TinArmor();
	}
}
