package com.madbros.adventurecraft.Items;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.Color;
import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Helpers;

public class SlownessPotion extends PotionItem {
	public SlownessPotion() {
		id = SLOWNESS_POTION;
		name = "Slowness Potion";
		sprite = Sprites.sprites.get(Sprites.SLOWNESS_POTION);
		
		itemsPossiblyCraftable = new int[]{};
		
	}
	@Override
	public PotionItem createNew() {
		return new SlownessPotion();
	}
	
	public boolean canUsePotion() {
		//FIXME  Need to implement a method here...
		return true;
	}
	
	public void applyPotionEffect() {
		//FIXME  Need to implement a method here...
	}
}
