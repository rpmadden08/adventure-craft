package com.madbros.tileminer.Items.Food;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

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
