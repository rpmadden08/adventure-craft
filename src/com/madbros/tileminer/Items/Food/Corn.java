package com.madbros.tileminer.Items.Food;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Corn extends FoodItem {
	public Corn() {
		id = CORN;
		name = "Corn";
		sprite = Sprites.sprites.get(Sprites.CORN);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Corn();
	}
}
