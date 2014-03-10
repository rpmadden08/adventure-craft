package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

public class ImpHorn extends CraftItem {
	public ImpHorn() {
		id = IMP_HORN;
		name = "Imp Horn";
		sprite = Sprites.sprites.get(Sprites.IMP_HORN);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public ImpHorn createNew() {
		return new ImpHorn();
	}
}
