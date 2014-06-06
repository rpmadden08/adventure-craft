package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

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
