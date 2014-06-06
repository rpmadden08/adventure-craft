package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Coal extends CraftItem {
	public Coal() {
		id = COAL_ITEM;
		name = "Coal";
		isFuelSource = true;
		fuelAmount = 60;
		sprite = Sprites.sprites.get(Sprites.COAL_ITEM);
		itemsPossiblyCraftable = new int[]{TORCH};
	}
	
	@Override
	public Coal createNew() {
		return new Coal();
	}
}
