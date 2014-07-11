package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class TinHelmet extends Clothing {
	public TinHelmet() {
		id = TIN_HELMET;
		name = "Tin Hat";
		sprite = Sprites.sprites.get(Sprites.TIN_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_HELMET_ITEM);
		defensePower = 2;
		slotType = HELMET_SLOT;
		maxStackSize =1;
		craftCost = new int[]{TIN_BAR};
		craftCostAmount = new int[]{5};
		type = HELMET_TYPE;
		uses = 70;
		maxUses = 70;
	}
	
	@Override
	public TinHelmet createNew() {
		return new TinHelmet();
	}
}
