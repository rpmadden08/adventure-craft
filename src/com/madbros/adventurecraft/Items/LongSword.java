package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;

public class LongSword extends WeaponItem {
	public LongSword() {
		id = LONG_SWORD;
		sprite = Sprites.sprites.get(Sprites.LONG_SWORD);
		originX = 2;
		originY = 62;
		weaponOffsetX = 0;
		weaponOffsetY = -32;
	}
	
	@Override
	public void useLeft() {
		if(!Game.hero.isAttacking) {
			Game.hero.attack(this);
		}
	}
	
	@Override
	public LongSword createNew() {
		return new LongSword();
	}
}
