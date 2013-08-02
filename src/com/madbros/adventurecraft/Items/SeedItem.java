package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

public abstract class SeedItem extends StackableItem {
	int tileID = GRASS;
	@Override
	public abstract SeedItem createNew();
}
