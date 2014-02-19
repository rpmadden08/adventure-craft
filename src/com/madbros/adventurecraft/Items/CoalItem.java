package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class CoalItem extends CraftItem {
	public CoalItem() {
		id = COAL_ITEM;
		name = "Coal";
		isFuelSource = true;
		fuelAmount = 60;
		sprite = Sprites.sprites.get(Sprites.COAL_ITEM);
		itemsPossiblyCraftable = new int[]{PLANK};
	}
	
	@Override
	public CoalItem createNew() {
		return new CoalItem();
	}
}
