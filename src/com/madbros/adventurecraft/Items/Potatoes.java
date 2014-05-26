package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Potatoes extends FoodItem {
	public Potatoes() {
		id = POTATOES;
		name = "Potatoes";
		sprite = Sprites.sprites.get(Sprites.POTATOES);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Potatoes();
	}
}
