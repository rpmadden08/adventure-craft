package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Artichoke extends FoodItem {
	public Artichoke() {
		id = ARTICHOKE;
		name = "Artichoke";
		sprite = Sprites.sprites.get(Sprites.ARTICHOKE);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Artichoke();
	}
}
