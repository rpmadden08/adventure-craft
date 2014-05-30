package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Corn extends FoodItem {
	public Corn() {
		id = CORN;
		name = "Corn";
		sprite = Sprites.sprites.get(Sprites.CORN);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Corn();
	}
}
