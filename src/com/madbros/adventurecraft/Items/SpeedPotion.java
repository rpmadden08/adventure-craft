package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class SpeedPotion extends PotionItem {
	public SpeedPotion() {
		id = SPEED_POTION;
		name = "Speed Potion";
		sprite = Sprites.sprites.get(Sprites.SPEED_POTION);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public PotionItem createNew() {
		return new SpeedPotion();
	}
	
	public boolean canUsePotion() {
		//FIXME  Need to implement a method here...
		return true;
	}
	
	public void applyPotionEffect() {
		//FIXME  Need to implement a method here...
	}
}
