package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Tin extends CraftItem {
	public Tin() {
		id = TIN_ITEM;
		name = "Tin Clump";
		sprite = Sprites.sprites.get(Sprites.TIN_ITEM);
		itemsPossiblyBurnable = new int[]{TIN_BAR};
	}
	
	@Override
	public Tin createNew() {
		return new Tin();
	}
}
