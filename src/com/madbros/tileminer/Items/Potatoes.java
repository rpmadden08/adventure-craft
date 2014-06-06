package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Potatoes extends FoodItem {
	public Potatoes() {
		id = POTATOES;
		name = "Potatoes";
		sprite = Sprites.sprites.get(Sprites.POTATOES);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Potatoes();
	}
}
