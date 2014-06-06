package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class CopperSword extends Sword {
	public CopperSword() {
		id = COPPER_SWORD;
		name = "Copper Sword";
		sprite = Sprites.sprites.get(Sprites.COPPER_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{COPPER_BAR, STICK};
		craftCostAmount = new int[]{2, 1};
		attackPower = 12;
		maxUses = 200;
		uses = 200;
	}
	
	@Override
	public CopperSword createNew() {
		return new CopperSword();
	}
}
