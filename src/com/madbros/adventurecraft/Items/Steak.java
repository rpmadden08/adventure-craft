package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Steak extends FoodItem {
	public Steak() {
		id = STEAK;
		name = "Raw Steak";
		sprite = Sprites.sprites.get(Sprites.STEAK);
		itemsPossiblyBurnable = new int[]{COOKED_STEAK};
	}
	
	@Override
	public Steak createNew() {
		return new Steak();
	}
}
