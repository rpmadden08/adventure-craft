package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;


import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Rect;

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
		
		cRectU = new Rect (0,-6,82,32);
		cRectD = new Rect (0,54,82,32);
		cRectL = new Rect (-22,0,40,82);
		cRectR = new Rect (46,0,40,82);
	}
	
	@Override
	public TinSword createNew() {
		return new TinSword();
	}
}
