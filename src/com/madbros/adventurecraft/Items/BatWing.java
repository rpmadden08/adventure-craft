package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class BatWing extends CraftItem {
	public BatWing() {
		id = BAT_WING;
		sprite = Sprites.sprites.get(Sprites.BAT_WING);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public BatWing createNew() {
		return new BatWing();
	}
}
