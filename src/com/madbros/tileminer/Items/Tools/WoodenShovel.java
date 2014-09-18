package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class WoodenShovel extends Shovel {
	public WoodenShovel() {
		id = WOODEN_SHOVEL;
		name = "Wooden Shovel";
		sprite = Sprites.sprites.get(Sprites.WOODEN_SHOVEL);
		swingSprite = sprite;
		attackPower = 4;
		shovelPower = 4;
		isRepeatable = true;
		craftCost = new int[]{PLANK, STICK};
		craftCostAmount = new int[]{1, 2};
		maxUses = 15;
		uses = 15;
	}
	
	@Override
	public WoodenShovel createNew() {
		return new WoodenShovel();
	}
	
}
