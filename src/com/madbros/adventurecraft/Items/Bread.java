package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Bread extends FoodItem {
	public Bread() {
		id = BREAD;
		name = "Bread";
		sprite = Sprites.sprites.get(Sprites.BREAD);
		craftCost = new int[]{WHEAT};
		craftCostAmount = new int[]{4};
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Bread();
	}
}
