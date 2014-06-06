package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Log extends CraftItem {
	public Log() {
		id = LOG;
		name = "Log";
		isFuelSource = true;
		fuelAmount = 10;
		sprite = Sprites.sprites.get(Sprites.LOG_ITEM);
		itemsPossiblyCraftable = new int[]{PLANK, FIRE_PIT};
	}
	
	@Override
	public Log createNew() {
		return new Log();
	}
}
