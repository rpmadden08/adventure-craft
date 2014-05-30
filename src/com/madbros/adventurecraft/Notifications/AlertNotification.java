package com.madbros.adventurecraft.Notifications;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.NotificationController;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Notifications.Notification;

public class AlertNotification extends Notification {
	
	
	public AlertNotification(NotificationController notificationController, String notificationText) {
		super(notificationController, notificationText);
	}

	public void render() {
		
	}
	
	public void renderFont(int x, int y, SpriteBatch batch) {
		Color c = new Color(1.0f, 1.0f, 1.0f, 0.8f);
		Sprites.pixel.setColor(c);
		size = Sprites.font.getBounds(notificationText).width;
		Sprites.font.setColor(Color.RED);
		Sprites.font.draw(batch, notificationText, x-size-3, y+10);
		
		Sprites.pixel.setColor(Color.RED);
	}
	
}
