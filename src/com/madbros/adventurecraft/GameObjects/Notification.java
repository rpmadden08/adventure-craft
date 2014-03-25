package com.madbros.adventurecraft.GameObjects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.NotificationController;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Notification extends GameObject {
	NotificationController notificationController;
	public int life = 300;
	public String notificationText;
	public int id;
	public int notificationStackSize;
	public float size;
	
	
	public Notification(NotificationController notificationController, Sprite spriteID, String notificationText, int notificationStackSize, int id) {
		this.notificationController = notificationController;
		absRect = new Rect(Game.currentScreenSizeX, Game.currentScreenSizeY-32, 32,32);
		margin = new Margin(0, 0, 0, 0);
		sprite = (com.madbros.adventurecraft.Sprites.Sprite) spriteID;
		this.notificationText = notificationText;
		this.notificationStackSize = notificationStackSize;
		this.id = id;
		//item = ITEM_HASH.get(iD).createNew();
		
		
	}
	public void updateAI() {
		
	}
	
	public void update() {
		life --;
		//System.out.println(life);
		if(life < 1) {
			notificationController.remove(this);
		}
		
		
	}
	
	public void renderFont(int x, int y, SpriteBatch batch) {
		Color c = new Color(1.0f, 1.0f, 1.0f, 0.8f);
		Sprites.pixel.setColor(c);
		size = Sprites.font.getBounds(notificationText+" ("+Integer.toString(notificationStackSize)+")").width;
		Sprites.font.setColor(Color.WHITE);
		Sprites.font.draw(batch, notificationText + " ("+Integer.toString(notificationStackSize)+")", x-size-3, y+10);
		
		Sprites.pixel.setColor(Color.WHITE);
	}
}
