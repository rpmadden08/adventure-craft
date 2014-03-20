package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class CopperArmor extends Clothing {
	public CopperArmor() {
		id = COPPER_ARMOR;
		name = "Copper Armor";
		sprite = Sprites.sprites.get(Sprites.COPPER_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.COPPER_TORSO_ITEM);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{COPPER_BAR};
		craftCostAmount = new int[]{8};
	}
	
	@Override
	public CopperArmor createNew() {
		return new CopperArmor();
	}
}
