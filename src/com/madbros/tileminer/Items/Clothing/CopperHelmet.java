package com.madbros.tileminer.Items.Clothing;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class CopperHelmet extends Clothing {
	public CopperHelmet() {
		id = COPPER_HELMET;
		name = "Copper Hat";
		sprite = Sprites.sprites.get(Sprites.COPPER_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.COPPER_HELMET_ITEM);
		defensePower = 3;
		slotType = HELMET_SLOT;
		maxStackSize =1;
		craftCost = new int[]{COPPER_BAR};
		craftCostAmount = new int[]{5};
		type = HELMET_TYPE;
		uses = 90;
		maxUses = 90;
	}
	
	@Override
	public CopperHelmet createNew() {
		return new CopperHelmet();
	}
}