package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class IronBar extends CraftItem {
	public IronBar() {
		id = IRON_BAR;
		name = "Iron Bar";
		sprite = Sprites.sprites.get(Sprites.IRON_BAR);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public IronBar createNew() {
		return new IronBar();
	}
}
