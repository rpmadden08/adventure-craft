package com.madbros.adventurecraft.GameObjects;

import static com.madbros.adventurecraft.Constants.*;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.madbros.adventurecraft.CollectibleController;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.Utils.Margin;
import com.madbros.adventurecraft.Utils.Rect;

public class Collectible extends GameObject{
	CollectibleController collectibleController;
	Item item;
	Boolean bounced = false;
	int framesNum = 0;
	int length = 18;
	int gravity = 1;
	int speed = 10;
	int direction = 0;
	int stackSize = 1;

	public Collectible(CollectibleController collectibleController, int iD, Sprite spriteID, Rect collectible, int stackSize) {
		this.collectibleController = collectibleController;
		absRect = collectible;
		margin = new Margin(0, 0, 0, 0);
		sprite = (com.madbros.adventurecraft.Sprites.Sprite) spriteID;
		item = ITEM_HASH.get(iD).createNew();
		Random rand3 = new Random();
		int lr = rand3.nextInt(4);
		direction = lr-2;
		this.stackSize = stackSize;
		Random rand = new Random();
		int ud = rand.nextInt(4);
		length = length +ud -2;
		
	}
	
	public void updateAI() {
		
	}
	
	public void update() {
		if(bounced == false) {
			if(framesNum > length) {
				bounced = true;
			} else {
				//framesNum = 0;
				//Random rand2 = new Random();
				
				
				//length = rand2.nextInt(10);
				//length = 5;
				//Random rand = new Random();
				//int speed = rand.nextInt(10);
				speed = speed - gravity;
				absRect.y -= speed;
				absRect.x += direction;
				//absRect.y += gravity;
			}
			framesNum++;
		}
		
	}
	
	public void checkCollisions() {
		Rect charCRect = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect cRect = new Rect(absRect, margin);
		if(cRect.detectCollision(charCRect)) {
			didCollide();
		}
	}
	public void didCollide() {
		collectibleController.remove(this);
		Game.inventory.add(item, stackSize);
		
	}
}


