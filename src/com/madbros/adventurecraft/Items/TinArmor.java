package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class TinArmor extends Clothing {
	public TinArmor() {
		id = TIN_ARMOR;
		name = "Tin Armor";
		sprite = Sprites.sprites.get(Sprites.TIN_TORSO_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_TORSO_ITEM);
		defensePower = 1;
		slotType = ARMOR_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{8};
	}
	
	@Override
	public TinArmor createNew() {
		return new TinArmor();
	}
}
