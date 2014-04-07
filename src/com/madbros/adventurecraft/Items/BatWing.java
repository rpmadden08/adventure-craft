package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class BatWing extends CraftItem {
	public BatWing() {
		id = BAT_WING;
		name = "Bat Wing";
		sprite = Sprites.sprites.get(Sprites.BAT_WING);
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{SLOWNESS_POTION};
	}
	
	@Override
	public BatWing createNew() {
		return new BatWing();
	}
}
