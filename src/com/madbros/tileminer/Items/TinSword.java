package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class TinSword extends Sword {
	public TinSword() {
		id = TIN_SWORD;
		name = "Tin Sword";
		sprite = Sprites.sprites.get(Sprites.TIN_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{TIN_BAR, STICK};
		craftCostAmount = new int[]{2, 1};
		attackPower = 9;
		maxUses = 100;
		uses = 100;
	}
	
	@Override
	public TinSword createNew() {
		return new TinSword();
	}
}
