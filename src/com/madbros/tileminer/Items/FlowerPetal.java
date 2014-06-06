package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

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
