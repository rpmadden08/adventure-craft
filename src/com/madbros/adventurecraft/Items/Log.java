package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Textures;

public class Log extends CraftItem {
	public Log() {
		id = LOG;
		texture = Textures.logTexture;
		itemsPossiblyCraftable = new int[]{PLANK};
	}
	
	@Override
	public Log createNew() {
		return new Log();
	}
}
