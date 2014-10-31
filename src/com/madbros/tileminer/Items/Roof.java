package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;
import com.madbros.tileminer.Sprites.Sprites;

public class Roof extends RoofBase {
	public Roof() {
		id = ROOF;
		name = "Roof";
		tileId = ROOF_TILE;
		//placeableTileIds = new int[]{WALL_BORDER};
		itemsPossiblyCraftable = new int[]{};
		sprite = Sprites.sprites.get(Sprites.ROOF);
		numberProducedByCrafting = 4;
		craftCost = new int[]{PLANK, STONE};
		craftCostAmount = new int[]{8,1};
		maxStackSize = 99;
	}
	
	@Override
	public Roof createNew() {
		return new Roof();
	}
}
