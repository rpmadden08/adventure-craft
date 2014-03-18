package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class TinLeggings extends Clothing {
	public TinLeggings() {
		id = TIN_LEGGINGS;
		name = "Tin Leggings";
		sprite = Sprites.sprites.get(Sprites.TIN_LEGS_ITEM);
		animatedSprite = Sprites.animatedSprites.get(Sprites.TIN_LEGS_ITEM);
		defensePower = 1;
		slotType = LEGGINGS_SLOT;
		maxStackSize =1;
	}
	
	@Override
	public TinLeggings createNew() {
		return new TinLeggings();
	}
}
