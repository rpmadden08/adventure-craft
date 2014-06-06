package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class BatWing extends CraftItem {
	public BatWing() {
		id = BAT_WING;
		name = "Bat Wing";
		sprite = Sprites.sprites.get(Sprites.BAT_WING);
		itemsPossiblyCraftable = new int[]{};
		itemsPossiblyBrewable = new int[]{SPEED_POTION};
	}
	
	@Override
	public BatWing createNew() {
		return new BatWing();
	}
}
