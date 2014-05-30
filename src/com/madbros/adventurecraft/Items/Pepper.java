package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Pepper extends FoodItem {
	public Pepper() {
		id = PEPPER;
		name = "Pepper";
		sprite = Sprites.sprites.get(Sprites.PEPPER);
		
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{HARMING_POTION};
		
	}
	@Override
	public FoodItem createNew() {
		return new Pepper();
	}
}
