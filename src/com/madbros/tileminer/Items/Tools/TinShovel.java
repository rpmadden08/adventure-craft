package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class TinShovel extends Shovel {
	public TinShovel() {
		id = TIN_SHOVEL;
		name = "Tin Shovel";
		swingSprite = sprite;
		sprite = Sprites.sprites.get(Sprites.TIN_SHOVEL);
		attackPower = 4;
		shovelPower = 7;
		isRepeatable = true;
		craftCost = new int[]{TIN_BAR, STICK};
		craftCostAmount = new int[]{1, 2};
		maxUses = 100;
		uses = 100;
	}
	
	@Override
	public TinShovel createNew() {
		return new TinShovel();
	}
	
}
