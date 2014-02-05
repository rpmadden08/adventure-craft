package com.madbros.adventurecraft;

import java.util.ArrayList;
import static com.madbros.adventurecraft.Constants.*;
import java.util.Random;

import com.madbros.adventurecraft.GameObjects.*;

public class MobController {
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	public void update() {
		Random rand = new Random();
		int x = rand.nextInt(CHUNK_SIZE * 3)+ CHUNK_SIZE;
		int y = rand.nextInt(CHUNK_SIZE * 3)+ CHUNK_SIZE;
		if(x > CHUNK_SIZE*2 && x< CHUNK_SIZE *3+1 && y > CHUNK_SIZE*2 && y< CHUNK_SIZE *3+1 ) {
			
		} else {
			//if(Game.level.activeBlocks[x][y].layers[GRASS_LAYER].id == DARK_GRASS) {
				
				int num = rand.nextInt(1);//100
				if(num == 0 && mobs.size() < 10) {//57
					//mobs.add(new Bat(this, x, y));
				}
			//}
		}
		
//		for(int x = CHUNK_SIZE+1; x < CHUNK_SIZE*4; x++) {
//			for(int y = CHUNK_SIZE+1; y < CHUNK_SIZE*4; y++) {
//				if(Game.level.activeBlocks[x][y].layers[GRASS_LAYER].id == 1) {
//					
//					if(x > CHUNK_SIZE*2 && x< CHUNK_SIZE *3+1 && y > CHUNK_SIZE*2 && y< CHUNK_SIZE *3+1 ) {
//					} else {
//						Random rand = new Random();
//						int num = rand.nextInt(100000);//100
//						if(num == 1 && mobs.size() < 100) {//57
//							mobs.add(new Bat(this, x, y));
//						}
//					}
//				}
//			}
//		}
		
		//for every mob in this list do...
		for(int i = 0; i < mobs.size(); i++) {
			mobs.get(i).updateAI();
			mobs.get(i).update();
			//DO NOT remove the mob before checkCollisions...  Causes a crash
			mobs.get(i).checkCollisions();
		}
	}
	
	public void remove(Mob mob) {
		mobs.remove(mob);
	}
}
