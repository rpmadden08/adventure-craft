package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

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
		craftCostAmount = new int[]{5};
	}
	
	@Override
	public LeatherHat createNew() {
		return new LeatherHat();
	}
}
