package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Steak extends FoodItem {
	public Steak() {
		id = STEAK;
		name = "Raw Steak";
		sprite = Sprites.sprites.get(Sprites.STEAK);
		itemsPossiblyBurnable = new int[]{COOKED_STEAK};
		energyPercentage = 0.1;
	}
	
	@Override
	public Steak createNew() {
		return new Steak();
	}
}
