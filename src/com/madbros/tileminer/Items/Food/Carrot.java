package com.madbros.tileminer.Items.Food;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Carrot extends FoodItem {
	public Carrot() {
		id = CARROT;
		name = "Carrot";
		sprite = Sprites.sprites.get(Sprites.CARROT);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Carrot();
	}
}
