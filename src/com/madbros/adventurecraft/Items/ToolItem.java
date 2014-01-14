package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Game;


public abstract class ToolItem extends WeaponItem {
	protected boolean isInUse = false;
	protected int swingSpeed = 30;
	protected int swingRemaining = swingSpeed;
	
	public ToolItem() {
		super();
		isRepeatable = true;
	}
	
	public void impact() {
		
	}
	
	public void calculateUsage() {
		isInUse = false;
		uses = uses -1;
		if(uses <= 0) {
			stackSize = stackSize - 1;
			Game.inventory.deleteItemIfNecessary();
		}
	}
	public void swing() {
		if(isInUse == false) {
			swingRemaining = 0;
			isInUse = true;	
		} else {
			swingRemaining = swingRemaining -1;
			if (swingRemaining <= 0) {
				swingRemaining = swingSpeed;
				impact();

			}
		}
	}
	
	@Override
	public void useLeft() {

	}
	@Override
	public abstract ToolItem createNew();
}
