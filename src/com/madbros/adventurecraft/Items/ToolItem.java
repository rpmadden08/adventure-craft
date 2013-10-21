package com.madbros.adventurecraft.Items;


public abstract class ToolItem extends Item {
	protected int attackPower = 1;
	protected boolean isInUse = false;
	protected int swingSpeed = 30;
	protected int swingRemaining = swingSpeed;
	@Override
	public abstract ToolItem createNew();
}
