package com.madbros.adventurecraft;

import java.util.ArrayList;
import java.util.Random;

import com.madbros.adventurecraft.GameObjects.Collectible;
import com.madbros.adventurecraft.GameObjects.Notification;
import com.madbros.adventurecraft.Sprites.Sprite;
import com.madbros.adventurecraft.Utils.Rect;

public class NotificationController {
	
	public ArrayList<Notification> notifications = new ArrayList<Notification>();
	
	public void update() {
//		Random rand = new Random();
//		int num = rand.nextInt(100);
//		if(num == 57 && notifications.size() < 10) {
//			//collectibles.add(new Collectible(this, PLANK, Sprites.sprites.get(Sprites.PLANK_ITEM)));
//		}	
		//for every mob in this list do...
		for(int i = 0; i < notifications.size(); i++) {
			notifications.get(i).absRect.y = Game.currentScreenSizeY-32 -(i*32);
			notifications.get(i).updateAI();
			notifications.get(i).update();
			//collectibles.get(i).checkCollisions();
		}
	}
	
	public void add(int iD, Sprite sprite, Rect notificationRect, String notificationText) {
		notifications.add(new Notification(this,sprite, notificationText));
	}

	public void remove(Notification notification) {
		notifications.remove(notification);
	}
}
