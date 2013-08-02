package com.madbros.adventurecraft.Items;

import com.madbros.adventurecraft.Item;

public abstract class ToolItem extends Item {
	protected int attackPower = 1;
	@Override
	public abstract ToolItem createNew();
}
