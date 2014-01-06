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
	Boolean bounced2 = false;
	int framesNum = 0;
	int length = 18;
	int length2 = 8;
	int gravity = 1;
	int speed = 10;
	int currentSpeed = 3;
	int direction = 0;
	int direction2 = 0;
	int stackSize = 1;
	float damping = 1;
	int range = 100;

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
		int ud2 = rand.nextInt(4);
		length2 = length2 +ud2 -2;
		
	}
	
	public void updateAI() {
		
	}
	
	public void update() {
		if(bounced == false) {
			if(framesNum > length) {
				bounced = true;
				framesNum = 0;
				speed = 5;
			} else {
				speed = speed - gravity;
				absRect.y -= speed;
				absRect.x += direction;
			}
			framesNum++;
		} else if(bounced2 == false) {
			if(framesNum > length2) {
				bounced2 = true;
			} else {
				speed = speed - gravity;
				absRect.y -= speed;
				absRect.x += direction;
			}
			framesNum++;
		}
		
	}
	
	public void checkCollisions() {
		Rect charCRect1 = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect rangeRect = new Rect(absRect.x - range, absRect.y - range, absRect.w+ (range*2), absRect.h+(range*2));
		if(rangeRect.detectCollision(charCRect1)) {
			chase();
		}
		
		Rect charCRect = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect cRect = new Rect(absRect, margin);
		if(cRect.detectCollision(charCRect)) {
			didCollide();
		}
	}
	
	public void chase() {
		int speedX = Game.hero.absRect.x - absRect.x+(absRect.w/2);
		int speedY = Game.hero.absRect.y - absRect.y+(absRect.h/2+6);
		
		float maxSpeed = currentSpeed * damping;
		
		if(speedX > maxSpeed) {
			speedX = (int)maxSpeed;
		}
		if(speedX < -maxSpeed) {
			speedX = (int)-maxSpeed;
		}
		
		if(speedY > maxSpeed) {
			speedY = (int)maxSpeed;
		}
		if(speedY < -maxSpeed) {
			speedY = (int)-maxSpeed;
		}
		
		
		absRect.x = absRect.x + speedX;
		absRect.y = absRect.y + speedY;
		
	}
	
	public void didCollide() {
		collectibleController.remove(this);
		Game.inventory.add(item, stackSize);
		
	}
}


