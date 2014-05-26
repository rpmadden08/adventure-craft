package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class FlowerPetal extends CraftItem {
	public FlowerPetal() {
		id = FLOWER_PETAL;
		name = "Magic Flower Petal";
		sprite = Sprites.sprites.get(Sprites.FLOWER_PETAL);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public FlowerPetal createNew() {
		return new FlowerPetal();
	}
}
