package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;
import com.madbros.tileminer.Sprites.Sprites;

public class WoodenDoor extends Door {
	public WoodenDoor() {
		id = WOODEN_DOOR;
		name = "Wooden Door";
		tileId = WOOD_DOOR_BOTTOM_TILE;
		tile2Id = WOOD_DOOR_TOP_TILE;
		placeableTileIds = new int[]{WOOD_FLOOR_TILE};
		sprite = Sprites.sprites.get(Sprites.WOODEN_DOOR);
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
		numberProducedByCrafting = 1;
		craftCost = new int[]{PLANK, TIN_BAR};
		craftCostAmount = new int[]{8, 1};
		maxStackSize = 99;
		
		
	}
	
	@Override
	public BlockItem32 createNew() {
		return new WoodenDoor();
	}
	


}
