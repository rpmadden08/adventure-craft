package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Tin extends CraftItem {
	public Tin() {
		id = TIN_ITEM;
		name = "Tin Clump";
		sprite = Sprites.sprites.get(Sprites.TIN_ITEM);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Tin createNew() {
		return new Tin();
	}
}
