package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Log extends CraftItem {
	public Log() {
		id = LOG;
		sprite = Sprites.logSprite;
		itemsPossiblyCraftable = new int[]{PLANK};
	}
	
	@Override
	public Log createNew() {
		return new Log();
	}
}
