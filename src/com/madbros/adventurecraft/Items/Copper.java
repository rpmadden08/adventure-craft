package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

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
