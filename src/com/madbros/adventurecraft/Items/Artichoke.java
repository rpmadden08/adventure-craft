package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.Sprites.Sprites;

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
