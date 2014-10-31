package com.madbros.tileminer.Items.Weapons;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class WoodenSword extends Sword {
	public WoodenSword() {
		id = WOODEN_SWORD;
		name = "Wooden Sword";
		sprite = Sprites.sprites.get(Sprites.WOODEN_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{PLANK, STICK};
		craftCostAmount = new int[]{2, 1};
		attackPower = 4;
		pickPower = 2;
		maxUses = 15;
		uses = 15;
		knockBackPower = 0.1f;
	}
	
	@Override
	public WoodenSword createNew() {
		return new WoodenSword();
	}
}