package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Game;


public abstract class WeaponItem extends Item {
	public int swingSpeed = 30;
	public boolean isRepeatable = false;
	
	@Override
	public abstract WeaponItem createNew();
	
}
