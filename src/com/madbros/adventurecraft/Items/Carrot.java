package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Carrot extends FoodItem {
	public Carrot() {
		id = CARROT;
		name = "Carrot";
		sprite = Sprites.sprites.get(Sprites.CARROT);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Carrot();
	}
}
