package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class Eyeball extends CraftItem {
	public Eyeball() {
		id = EYEBALL;
		name = "Eyeball";
		sprite = Sprites.sprites.get(Sprites.EYEBALL);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Eyeball createNew() {
		return new Eyeball();
	}
}
