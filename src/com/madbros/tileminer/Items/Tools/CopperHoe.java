package com.madbros.tileminer.Items.Tools;



import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class CopperHoe extends Hoe {
	public CopperHoe() {
		id = COPPER_HOE;
		name = "Copper Hoe";
		sprite = Sprites.sprites.get(Sprites.COPPER_HOE);
		swingSprite = sprite;
		attackPower = 5;
		isRepeatable = true;
		craftCost = new int[]{COPPER_BAR, STICK};
		craftCostAmount = new int[]{2, 2};
		maxUses = 200;
		uses = 200;
	}
	
	@Override
	public CopperHoe createNew() {
		return new CopperHoe();
	}
}
