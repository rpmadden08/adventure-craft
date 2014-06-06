package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Venom extends CraftItem {
	public Venom() {
		id = VENOM;
		name = "Venom";
		sprite = Sprites.sprites.get(Sprites.VENOM);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Venom createNew() {
		return new Venom();
	}
}
