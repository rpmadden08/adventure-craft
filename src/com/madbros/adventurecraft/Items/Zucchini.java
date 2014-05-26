package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class Zucchini extends FoodItem {
	public Zucchini() {
		id = ZUCCHINI;
		name = "Zucchini";
		sprite = Sprites.sprites.get(Sprites.ZUCCHINI);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public FoodItem createNew() {
		return new Zucchini();
	}
}
