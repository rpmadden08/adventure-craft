package com.madbros.tileminer.Notifications;


import static com.madbros.tileminer.Constants.Z_CHARACTER;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.madbros.tileminer.NotificationController;
import com.madbros.tileminer.Notifications.Notification;
import com.madbros.tileminer.Utils.RectInt;

public class CollectibleNotification extends Notification {
	
	
	public CollectibleNotification(NotificationController notificationController, Sprite spriteID, String notificationText, int notificationStackSize, int id) {
		super(notificationController, notificationText);
		sprite = (com.madbros.tileminer.Sprites.Sprite) spriteID;
		this.notificationStackSize = notificationStackSize;
		this.id = id;
	}

	public void render() {
		RectInt absRect2 = absRect.getRectInt();
		sprite.draw(absRect2.x-(int)size-38, absRect2.y, Z_CHARACTER);
	}

	
}
