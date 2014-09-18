package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class StoneShovel extends Shovel {
	public StoneShovel() {
		id = STONE_SHOVEL;
		name = "Stone Shovel";
		sprite = Sprites.sprites.get(Sprites.STONE_SHOVEL);
		swingSprite = sprite;
		attackPower = 3;
		shovelPower = 5;
		isRepeatable = true;
		craftCost = new int[]{STONE, STICK};
		craftCostAmount = new int[]{1, 2};
		maxUses = 40;
		uses = 40;
	}
	
	@Override
	public StoneShovel createNew() {
		return new StoneShovel();
	}
	
}
