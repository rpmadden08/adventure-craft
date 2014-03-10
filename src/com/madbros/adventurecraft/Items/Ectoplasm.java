package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.*;

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
