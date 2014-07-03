package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class IronHelmet extends Clothing {
	public IronHelmet() {
		id = IRON_HELMET;
		name = "Iron Helmet";
		sprite = Sprites.sprites.get(Sprites.PLATE_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.PLATE_HELMET_ITEM);
		defensePower = 1;
		slotType = HELMET_SLOT;
		maxStackSize =1;
		craftCost = new int[]{IRON_BAR};
		craftCostAmount = new int[]{5};
		type = HELMET_TYPE;
	}
	
	@Override
	public IronHelmet createNew() {
		return new IronHelmet();
	}
}
