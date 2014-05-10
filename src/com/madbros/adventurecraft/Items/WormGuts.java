package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class WormGuts extends CraftItem {
	public WormGuts() {
		id = WORM_GUTS;
		name = "Worm Guts";
		sprite = Sprites.sprites.get(Sprites.WORM_GUTS);
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{HARMING_POTION};
	}
	
	@Override
	public WormGuts createNew() {
		return new WormGuts();
	}
}
