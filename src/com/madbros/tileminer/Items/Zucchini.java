package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
public class Zucchini extends FoodItem {
	public Zucchini() {
		id = ZUCCHINI;
		name = "Zucchini";
		sprite = Sprites.sprites.get(Sprites.ZUCCHINI);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Zucchini();
	}
}
