package com.madbros.tileminer.Items.Weapons;


//import com.badlogic.gdx.backends.openal.Wav.Sound;
import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class StingerSword extends Sword {
	public StingerSword() {
		id = STINGER_SWORD;
		name = "Stinger Sword";
		sprite = Sprites.sprites.get(Sprites.STINGER_SWORD);
		swingSprite = sprite;
		originX = 0;
		originY = 32;
		weaponOffsetX = 0;
		weaponOffsetY = 0;
		cRectFinal = new Rect (0,0,0,0);
		sound = "sounds/swordSwing1.wav";
		craftCost = new int[]{};
		craftCostAmount = new int[]{};
		
		
		attackPower = 15;
		maxUses = 200;
		uses = 200;
		knockBackPower = 0.4f;
	}
	
	@Override
	public StingerSword createNew() {
		return new StingerSword();
	}
}
