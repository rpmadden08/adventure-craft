package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Wheat extends FoodItem {
	public Wheat() {
		id = WHEAT;
		name = "Wheat";
		sprite = Sprites.sprites.get(Sprites.WHEAT);
		
		itemsPossiblyCraftable = new int[]{BREAD};
		
	}
	@Override
	public FoodItem createNew() {
		return new Wheat();
	}
}
