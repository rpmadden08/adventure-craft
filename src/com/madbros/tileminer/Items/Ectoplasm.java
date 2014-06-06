package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.*;

public class Ectoplasm extends CraftItem {
	public Ectoplasm() {
		id = ECTOPLASM;
		name = "Ectoplasm";
		sprite = Sprites.sprites.get(Sprites.ECTOPLASM);
		itemsPossiblyCraftable = new int[]{};
	}
	
	@Override
	public Ectoplasm createNew() {
		return new Ectoplasm();
	}
}
