package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

//import com.badlogic.gdx.Gdx;
import com.madbros.adventurecraft.Sprites.Sprites;

public class StonePick extends Pick {
	public StonePick() {
		id = STONE_PICK;
		name = "Stone Pickaxe";
		sprite = Sprites.sprites.get(Sprites.STONE_PICK);
		swingSprite = sprite;
		attackPower = 3;
		itemPower = 5;
		isRepeatable = true;
		craftCost = new int[]{STONE, STICK};
		craftCostAmount = new int[]{2, 3};
		maxUses = 40;
		uses = 40;
	}
	
	@Override
	public StonePick createNew() {
		return new StonePick();
	}
}
