package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Sword extends WeaponItem {
	public Sword() {
		id = SWORD;
		sprite = Sprites.sprites.get(Sprites.SWORD);
		originX = 2;
		originY = 30;
	}
	
	@Override
	public void useLeft() {
		//System.out.println("Sword used");
		if(!Game.hero.isAttacking) {
			Game.hero.attack(this);
		}
	}
	
	@Override
	public Sword createNew() {
		return new Sword();
	}
}
