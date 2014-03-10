package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class GreenSlime extends CraftItem {
	public GreenSlime() {
		id = GREEN_SLIME;
		name = "Green Slime";
		sprite = Sprites.sprites.get(Sprites.GREEN_SLIME);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public GreenSlime createNew() {
		return new GreenSlime();
	}
}
