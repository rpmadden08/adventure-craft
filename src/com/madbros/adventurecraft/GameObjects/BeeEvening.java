package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.madbros.adventurecraft.Block;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MobController;
import com.madbros.adventurecraft.Items.Item;
import com.madbros.adventurecraft.Sprites.CompoundAnimatedSprite;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class BeeEvening extends Bee {
	//Rect detectRect;
	
	public BeeEvening(MobController mobController, int x, int y) {
		super(mobController, x, y);
		detectRange = 25;
	}
	
	public void updateAI() {
		checkForChasing();
			super.updateAI();
		
	}
}
