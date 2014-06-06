package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Artichoke extends FoodItem {
	public Artichoke() {
		id = ARTICHOKE;
		name = "Artichoke";
		sprite = Sprites.sprites.get(Sprites.ARTICHOKE);
		itemsPossiblyBrewable = new int[]{SLOWNESS_POTION};
		itemsPossiblyCraftable = new int[]{};
		energyPercentage = 0.3;
		
	}
	@Override
	public FoodItem createNew() {
		return new Artichoke();
	}
}
