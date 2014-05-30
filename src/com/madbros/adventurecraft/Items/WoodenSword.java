package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

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
		itemPower = 2;
		maxUses = 15;
		uses = 15;
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	
	@Override
	public WoodenSword createNew() {
		return new WoodenSword();
	}
}
