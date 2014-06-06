package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Honey extends CraftItem {
	public Honey() {
		id = HONEY;
		name = "Honey";
		sprite = Sprites.sprites.get(Sprites.HONEY);
		itemsPossiblyCraftable = new int[]{BEEHIVE};
		itemsPossiblyBrewable = new int[]{HEALTH_POTION};
	}
	
	@Override
	public Honey createNew() {
		return new Honey();
	}
}
