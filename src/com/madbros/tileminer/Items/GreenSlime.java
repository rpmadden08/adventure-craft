package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

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
