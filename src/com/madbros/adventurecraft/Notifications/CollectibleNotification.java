package com.madbros.adventurecraft.Notifications;


import static com.madbros.adventurecraft.Constants.Z_CHARACTER;

import com.badlogic.gdx.graphics.g2d.Sprite;

import com.madbros.adventurecraft.NotificationController;

import com.madbros.adventurecraft.Notifications.Notification;

public class CollectibleNotification extends Notification {
	
	
	public CollectibleNotification(NotificationController notificationController, Sprite spriteID, String notificationText, int notificationStackSize, int id) {
		super(notificationController, notificationText);
		sprite = (com.madbros.adventurecraft.Sprites.Sprite) spriteID;
		this.notificationStackSize = notificationStackSize;
		this.id = id;
	}

	public void render() {
		sprite.draw(absRect.x-(int)size-38, absRect.y, Z_CHARACTER);
	}

	
}
