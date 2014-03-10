package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class BlueSlime extends CraftItem {
	public BlueSlime() {
		id = BLUE_SLIME;
		name = "Blue Slime";
		sprite = Sprites.sprites.get(Sprites.BLUE_SLIME);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public BlueSlime createNew() {
		return new BlueSlime();
	}
}
