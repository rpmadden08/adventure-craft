package com.madbros.adventurecraft;

import java.util.ArrayList;
import com.madbros.adventurecraft.GameObjects.Notification;
import com.madbros.adventurecraft.Sprites.Sprite;
import com.madbros.adventurecraft.Utils.Rect;

public class ParticleEffectController {
	
	public ArrayList<ParticleEffect> particleEffects = new ArrayList<ParticleEffect>();
	
	public void update() {
//		Random rand = new Random();
//		int num = rand.nextInt(100);
//		if(num == 57 && notifications.size() < 10) {
//			//collectibles.add(new Collectible(this, PLANK, Sprites.sprites.get(Sprites.PLANK_ITEM)));
//		}	
		//for every mob in this list do...
//		for(int i = 0; i < notifications.size(); i++) {
//			notifications.get(i).absRect.y = Game.currentScreenSizeY-32 -(i*32);
//			notifications.get(i).updateAI();
//			notifications.get(i).update();
//			//collectibles.get(i).checkCollisions();
//		}
	}
	
//	public void add(int iD, Sprite sprite, Rect notificationRect, String name) {
//		particleEffects.add(new ParticleEffect(name));
//	}
	
	public void add(String name,int x, int y) {
		particleEffects.add(new ParticleEffect(name, x, y));
	}

	public void remove(ParticleEffect particleEffect) {
		particleEffects.remove(particleEffect);
	}
}
