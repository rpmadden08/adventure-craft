package com.madbros.adventurecraft.Items;


public abstract class ToolItem extends Item {
	protected int attackPower = 1;
	protected boolean isInUse = false;
	@Override
	public abstract ToolItem createNew();
}
