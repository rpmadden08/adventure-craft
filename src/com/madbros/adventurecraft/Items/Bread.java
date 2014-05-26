package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Bread extends FoodItem {
	public Bread() {
		id = BREAD;
		name = "Bread";
		sprite = Sprites.sprites.get(Sprites.BREAD);
		craftCost = new int[]{WHEAT};
		craftCostAmount = new int[]{4};
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Bread();
	}
}
