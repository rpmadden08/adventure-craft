package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Copper extends CraftItem {
	public Copper() {
		id = COPPER_ITEM;
		name = "Copper Clump";
		sprite = Sprites.sprites.get(Sprites.COPPER_ITEM);
		itemsPossiblyBurnable = new int[]{COPPER_BAR};
	}
	
	@Override
	public Copper createNew() {
		return new Copper();
	}
}
