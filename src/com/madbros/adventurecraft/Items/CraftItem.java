package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.PLANK;

public abstract class CraftItem extends BaseItem {
	
	@Override
	public abstract CraftItem createNew();
}
