package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Log extends CraftItem {
	public Log() {
		id = LOG;
		name = "Log";
		isFuelSource = true;
		fuelAmount = 60;
		sprite = Sprites.sprites.get(Sprites.LOG_ITEM);
		itemsPossiblyCraftable = new int[]{PLANK};
	}
	
	@Override
	public Log createNew() {
		return new Log();
	}
}
