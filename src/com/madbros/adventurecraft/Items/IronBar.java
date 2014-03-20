package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class IronBar extends CraftItem {
	public IronBar() {
		id = IRON_BAR;
		name = "Iron Bar";
		sprite = Sprites.sprites.get(Sprites.IRON_BAR);
		itemsPossiblyCraftable = new int[]{CAULDRON, IRON_ARMOR, IRON_AXE, IRON_BOOTS, IRON_HELMET, IRON_HOE, IRON_LEGGINGS, IRON_PICK, IRON_SHOVEL, IRON_SWORD};
	}
	
	@Override
	public IronBar createNew() {
		return new IronBar();
	}
}
