package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class StoneSword extends Sword {
	public StoneSword() {
		id = STONE_SWORD;
		name = "Stone Sword";
		sprite = Sprites.sprites.get(Sprites.STONE_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{STONE, STICK};
		craftCostAmount = new int[]{2, 1};
		attackPower = 6;
		maxUses = 40;
		uses = 40;
		knockBackPower = 0.2f;
	}
	
	@Override
	public StoneSword createNew() {
		return new StoneSword();
	}
}
