package com.madbros.tileminer.Items.Clothing;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class LeatherHat extends Clothing {
	public LeatherHat() {
		id = LEATHER_HAT;
		name = "Leather Hat";
		sprite = Sprites.sprites.get(Sprites.LEATHER_HELMET_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.LEATHER_HELMET_ITEM);
		defensePower = 1;
		slotType = HELMET_SLOT;
		maxStackSize =1;
		craftCost = new int[]{LEATHER};
		craftCostAmount = new int[]{1};
		type = HELMET_TYPE;
		uses = 50;
		maxUses = 50;
		
	}
	
	@Override
	public LeatherHat createNew() {
		return new LeatherHat();
	}
}
