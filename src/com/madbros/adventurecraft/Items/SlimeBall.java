package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class SlimeBall extends CraftItem {
	public SlimeBall() {
		id = SLIME_BALL;
		name = "Slime Ball";
		sprite = Sprites.sprites.get(Sprites.SLIME_BALL);
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{SLOWNESS_POTION};
	}
	
	@Override
	public SlimeBall createNew() {
		return new SlimeBall();
	}
}
