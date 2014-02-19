package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class CopperItem extends CraftItem {
	public CopperItem() {
		id = COPPER_ITEM;
		name = "Copper Clump";
		sprite = Sprites.sprites.get(Sprites.COPPER_ITEM);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public CopperItem createNew() {
		return new CopperItem();
	}
}
