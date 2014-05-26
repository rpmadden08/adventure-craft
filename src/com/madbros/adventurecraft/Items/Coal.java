package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Coal extends CraftItem {
	public Coal() {
		id = COAL_ITEM;
		name = "Coal";
		isFuelSource = true;
		fuelAmount = 60;
		sprite = Sprites.sprites.get(Sprites.COAL_ITEM);
		itemsPossiblyCraftable = new int[]{TORCH};
	}
	
	@Override
	public Coal createNew() {
		return new Coal();
	}
}
