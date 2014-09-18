package com.madbros.tileminer.Items.Food;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Tomato extends FoodItem {
	public Tomato() {
		id = TOMATO;
		name = "Tomato";
		sprite = Sprites.sprites.get(Sprites.TOMATO);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Tomato();
	}
}
