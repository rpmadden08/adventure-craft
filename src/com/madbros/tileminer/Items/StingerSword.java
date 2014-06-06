package com.madbros.tileminer.Items;


//import com.badlogic.gdx.backends.openal.Wav.Sound;
import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Utils.Rect;

public class StingerSword extends WeaponItem {
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
	}
	
	@Override
	public void useLeft() {
		if(!Game.hero.isAttacking && Game.hero.attackButtonReleased) {
			
			Game.hero.attack(this);
			Game.soundController.create(sound, 1);
		}
	}
	
	@Override
	public StingerSword createNew() {
		return new StingerSword();
	}
}
