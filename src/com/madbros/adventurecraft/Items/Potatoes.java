package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.Sprites.Sprites;

public class Potatoes extends FoodItem {
	public Potatoes() {
		id = POTATOES;
		sprite = Sprites.sprites.get(Sprites.POTATOES);
		
	}
	@Override
	public FoodItem createNew() {
		return new Potatoes();
	}
}
