package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class TinBar extends CraftItem {
	public TinBar() {
		id = TIN_BAR;
		name = "Tin Bar";
		sprite = Sprites.sprites.get(Sprites.TIN_BAR);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public TinBar createNew() {
		return new TinBar();
	}
}
