package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Venom extends CraftItem {
	public Venom() {
		id = VENOM;
		name = "Venom";
		sprite = Sprites.sprites.get(Sprites.VENOM);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Venom createNew() {
		return new Venom();
	}
}
