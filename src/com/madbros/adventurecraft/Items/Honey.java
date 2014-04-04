package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Honey extends CraftItem {
	public Honey() {
		id = HONEY;
		name = "Honey";
		sprite = Sprites.sprites.get(Sprites.HONEY);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Honey createNew() {
		return new Honey();
	}
}
