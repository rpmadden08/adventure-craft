package com.madbros.tileminer.Items;



public abstract class WeaponItem extends Item {
	public int swingSpeed = 30;
	public boolean isRepeatable = false;
	
	@Override
	public abstract WeaponItem createNew();
	
}
