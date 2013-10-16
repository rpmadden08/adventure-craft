package com.madbros.adventurecraft.Items;


public abstract class WeaponItem extends Item {
	public int swingSpeed = 30;

	@Override
	public abstract WeaponItem createNew();
}
