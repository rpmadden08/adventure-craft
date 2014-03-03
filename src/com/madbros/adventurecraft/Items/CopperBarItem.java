package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class CopperBarItem extends CraftItem {
	public CopperBarItem() {
		id = COPPER_BAR;
		name = "Copper Bar";
		sprite = Sprites.sprites.get(Sprites.COPPER_BAR);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public CopperBarItem createNew() {
		return new CopperBarItem();
	}
}
