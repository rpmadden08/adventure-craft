package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Bread extends FoodItem {
	public Bread() {
		id = BREAD;
		name = "Bread";
		sprite = Sprites.sprites.get(Sprites.BREAD);
		craftCost = new int[]{WHEAT};
		craftCostAmount = new int[]{4};
		energyPercentage = 0.25;
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Bread();
	}
}