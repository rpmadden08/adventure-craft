package com.madbros.adventurecraft;

import java.util.ArrayList;

import java.util.Random;

import com.madbros.adventurecraft.GameObjects.*;

public class MobController {
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	public void update() {
		Random rand = new Random();
		int num = rand.nextInt(10);//100
		if(num == 1 && mobs.size() < 1) {//57
			mobs.add(new Bat(this));
		}
		
		//for every mob in this list do...
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).updateAI();
			mobs.get(i).update();
			mobs.get(i).checkCollisions();
		}
	}
	
	public void remove(Mob mob) {
		mobs.remove(mob);
	}
}
