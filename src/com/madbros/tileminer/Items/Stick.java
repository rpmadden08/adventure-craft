package com.madbros.tileminer.Items;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Sprites.Sprites;

public class Stick extends CraftItem{
	public Stick() {
		id = STICK;
		name = "Stick";
		sprite = Sprites.sprites.get(Sprites.STICK);
		swingSprite = sprite;
		swingSprite.setColor(1, 1, 1, 1);
		numberProducedByCrafting = 4;
		itemsPossiblyCraftable = new int[]{BEEHIVE, COPPER_AXE, COPPER_HOE, COPPER_PICK, COPPER_SHOVEL, COPPER_SWORD,IRON_AXE, FIRE_PIT, FIRE_STARTER, IRON_HOE, IRON_PICK, IRON_SHOVEL, IRON_SWORD, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD, TIN_AXE, TIN_HOE, TIN_PICK, TIN_SHOVEL, TIN_SWORD, TORCH, WOODEN_AXE, WOODEN_HOE, WOODEN_PICK, WOODEN_SHOVEL, WOODEN_SWORD};
		craftCost = new int[]{PLANK};
		craftCostAmount = new int[]{2};
		workSpaceNeeded = new int[]{BARE_HANDS_WORKSPACE,TABLE_WORKSPACE};
		//set recipes
	}

	
	@Override
	public Stick createNew() {
		return new Stick();
	}
}