package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class CopperShovel extends Shovel {
	public CopperShovel() {
		id = COPPER_SHOVEL;
		name = "Copper Shovel";
		sprite = Sprites.sprites.get(Sprites.COPPER_SHOVEL);
		swingSprite = sprite;
		attackPower = 5;
		shovelPower = 10;
		isRepeatable = true;
		craftCost = new int[]{COPPER_BAR, STICK};
		craftCostAmount = new int[]{1, 2};
		maxUses = 200;
		uses = 200;
	}
	
	@Override
	public CopperShovel createNew() {
		return new CopperShovel();
	}
}