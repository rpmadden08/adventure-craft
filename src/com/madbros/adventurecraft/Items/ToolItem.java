package com.madbros.adventurecraft.Items;


public abstract class ToolItem extends Item {
	protected int attackPower = 1;
	@Override
	public abstract ToolItem createNew();
}
