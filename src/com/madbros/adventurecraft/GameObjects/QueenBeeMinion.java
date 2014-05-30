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

public class QueenBeeMinion extends Bee {
	int length = 0;
	int framesNum = 0;
	MobController mobController;
	//Rect detectRect;
	
	public QueenBeeMinion(MobController mobController, int x, int y) {
		super(mobController, x, y);
		moveSpeed = 0.06f;
	}
	public void updateAI() {
			if(framesNum > 200) {
				super.updateAI();
				framesNum = 201;
			} else if(framesNum > 50) {
				chaseHero(Game.hero.absRect, this.absRect);
				
			} else {
				isChasing = false;
				moveInRandomDirection(50);
			}
			framesNum++;
			
		
	}
}
