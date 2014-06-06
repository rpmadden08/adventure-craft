package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Eyeball extends CraftItem {
	public Eyeball() {
		id = EYEBALL;
		name = "Eyeball";
		sprite = Sprites.sprites.get(Sprites.EYEBALL);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Eyeball createNew() {
		return new Eyeball();
	}
}
