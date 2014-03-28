package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class LeatherArmor extends Clothing {
	public LeatherArmor() {
		id = LEATHER_ARMOR;
		name = "Leather Armor";
		sprite = Sprites.sprites.get(Sprites.LEATHER_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_TORSO_ITEM);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{LEATHER};
		craftCostAmount = new int[]{8};
	}
	
	@Override
	public LeatherArmor createNew() {
		return new LeatherArmor();
	}
}
