package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

public abstract class BlockItem extends StackableItem{
	int tileID = DIRT;
	
	@Override
	public abstract BlockItem createNew();
}
