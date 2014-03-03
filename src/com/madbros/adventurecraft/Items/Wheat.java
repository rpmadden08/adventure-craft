package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Wheat extends FoodItem {
	public Wheat() {
		id = WHEAT;
		name = "Wheat";
		sprite = Sprites.sprites.get(Sprites.WHEAT);
		
		itemsPossiblyCraftable = new int[]{BAKED_POTATOES};
		
	}
	@Override
	public FoodItem createNew() {
		return new Wheat();
	}
}
