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
	public Item item;
	public Boolean bounced = false;
	public Boolean bounced2 = false;
	int framesNum = 0;
	int length = 18;
	int length2 = 8;
	int gravity = 1;
	int speed = 10;
	float speed2 = 0;
	//int currentSpeed = 3;
	int direction = 0;
	int direction2 = 0;
	int stackSize = 1;
	float damping = 1;
	int range = 100;
	int uses;
	String plopSound = "sounds/plop.wav";

	public Collectible(CollectibleController collectibleController, int iD, Sprite spriteID, Rect collectible, int stackSize, int uses) {
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
		this.uses = uses;
		
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
		} else {
			checkCollisions();
		}
		
	}
	
	public void checkCollisions() {
		Rect charCRect1 = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect rangeRect = new Rect(absRect.x - range, absRect.y - range, absRect.w+ (range*2), absRect.h+(range*2));
		if(rangeRect.detectCollision(charCRect1) && !Game.hero.isDead) {
			if(speed2 < 5) {
				speed2 = speed2 + 0.2f;
			}
			chase();
		} else {
			speed2 = 0;
		}
		
		Rect charCRect = new Rect(Game.hero.absRect, Game.hero.margin);
		Rect cRect = new Rect(absRect, margin);
		if(cRect.detectCollision(charCRect)) {
			didCollide();
		}
	}
	
	public void chase() {
		double p1x = (double) Game.hero.absRect.x+ (Game.hero.absRect.w /2);
		double p1y = (double) Game.hero.absRect.y+ (Game.hero.absRect.h/2);
		double p2x = (double) absRect.x +(absRect.w /2);
		double p2y = (double) absRect.y +(absRect.h /2);
		double xDiff = p2x - p1x;
		double yDiff = p2y - p1y;
		double degrees = Math.atan2(yDiff,  xDiff);
		degrees = degrees * 180 /(int) Math.PI;
		if(degrees < 0) {
			degrees += 360;
		}
		degrees = degrees +180;
		
		double moveX = 0;
		double moveY = 0;
		
		
		double newX = ((int) absRect.x) + 1 * Math.cos(Math.toRadians(degrees));
		double newY = ((int) absRect.y) + 1 * Math.sin(Math.toRadians(degrees));
		
		double vX = newX - absRect.x;
		double vY = newY - absRect.y;
		
		double length2 = Math.sqrt((vX*vX)+(vY*vY));
		
		vX = vX/length2;
		vY = vY/length2;
		
		moveX = (vX*speed2);
		moveY = (vY*speed2);
		
		absRect.x += moveX;
		absRect.y += moveY;
		
	}
	
	public void didCollide() {
		Game.soundController.create(plopSound, 0.3f);
		collectibleController.remove(this);
		Game.inventory.add(item, stackSize, uses);
		Game.notificationController.addCollectible(sprite, absRect, item.name, stackSize, item.id);
		
	}
}


