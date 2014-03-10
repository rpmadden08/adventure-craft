package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class BeetleShell extends CraftItem {
	public BeetleShell() {
		id = BEETLE_SHELL;
		name = "Beetle Shell";
		sprite = Sprites.sprites.get(Sprites.BEETLE_SHELL);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public BeetleShell createNew() {
		return new BeetleShell();
	}
}
