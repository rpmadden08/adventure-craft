package com.madbros.tileminer.Notifications;


import static com.madbros.tileminer.Constants.Z_CHARACTER;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.madbros.tileminer.NotificationController;
import com.madbros.tileminer.Notifications.Notification;

public class CollectibleNotification extends Notification {
	
	
	public CollectibleNotification(NotificationController notificationController, Sprite spriteID, String notificationText, int notificationStackSize, int id) {
		super(notificationController, notificationText);
		sprite = (com.madbros.tileminer.Sprites.Sprite) spriteID;
		this.notificationStackSize = notificationStackSize;
		this.id = id;
	}

	public void render() {
		sprite.draw((int)absRect.x-(int)size-38, (int)absRect.y, Z_CHARACTER);
	}

	
}
