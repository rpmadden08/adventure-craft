package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Textures;

public class Sword extends WeaponItem {
	public Sword() {
		id = SWORD;
		texture = Textures.swordTexture;
	}
	
	@Override
	public void useLeft() {
		System.out.println("Sword used");
	}
	
	@Override
	public Sword createNew() {
		return new Sword();
	}
}
