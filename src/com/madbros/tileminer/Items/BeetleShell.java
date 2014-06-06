package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

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
