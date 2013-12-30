package com.madbros.adventurecraft.Items;


public abstract class ToolItem extends Item {
	protected boolean isInUse = false;
	protected int swingSpeed = 30;
	protected int swingRemaining = swingSpeed;
	@Override
	public abstract ToolItem createNew();
}
