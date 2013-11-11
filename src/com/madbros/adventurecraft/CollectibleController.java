package com.madbros.adventurecraft;

import java.util.ArrayList;
import java.util.Random;

import com.madbros.adventurecraft.GameObjects.*;
import com.madbros.adventurecraft.Sprites.Sprite;
import com.madbros.adventurecraft.Utils.Rect;

public class CollectibleController {
	public ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
	
	public void update() {
		Random rand = new Random();
		int num = rand.nextInt(100);
		if(num == 57 && collectibles.size() < 10) {
			//collectibles.add(new Collectible(this, PLANK, Sprites.sprites.get(Sprites.PLANK_ITEM)));
		}	
		//for every mob in this list do...
		for(int i = 0; i < collectibles.size(); i++) {
			collectibles.get(i).updateAI();
			collectibles.get(i).update();
			collectibles.get(i).checkCollisions();
		}
	}
	
	public void add(int iD, Sprite sprite, Rect collectibleRect, int stackSize) {
		collectibles.add(new Collectible(this, iD, sprite, collectibleRect, stackSize));
	}

	public void remove(Collectible collectible) {
		collectibles.remove(collectible);
	}
}
