package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Wheat extends FoodItem {
	public Wheat() {
		id = WHEAT;
		name = "Wheat";
		sprite = Sprites.sprites.get(Sprites.WHEAT);
		
		itemsPossiblyCraftable = new int[]{BREAD};
		
	}
	@Override
	public FoodItem createNew() {
		return new Wheat();
	}
}
