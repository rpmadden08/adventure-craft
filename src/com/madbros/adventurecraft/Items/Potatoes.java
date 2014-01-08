package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Potatoes extends FoodItem {
	public Potatoes() {
		id = POTATOES;
		name = "Potatoes";
		sprite = Sprites.sprites.get(Sprites.POTATOES);
		
		itemsPossiblyCraftable = new int[]{BAKED_POTATOES};
		
	}
	@Override
	public FoodItem createNew() {
		return new Potatoes();
	}
}
