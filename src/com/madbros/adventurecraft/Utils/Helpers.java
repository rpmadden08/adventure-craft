package com.madbros.adventurecraft.Utils;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.Sprites;

public class Helpers {		
	//wrappers for mouse stuff since display thinks y starts at the bottom...
	public static int getX() {
		return Game.currentState.input.mouseX;
	}
	
	public static int getY() {
		return Game.currentState.input.mouseY; //Game.currentScreenSizeY - Mouse.getY();
	}
	
	public static Rect getMouseRect() {
		return new Rect(getX(), getY(), 1, 1);
	}
	
	public static boolean containsXNumberOfItemsInSlots(int x, int itemId, Slot[] slots) {
		int count = 0;
		for(int i = 0; i < slots.length; i++) {
			if(slots[i].item.id == itemId) count++;
		}
		
		if(count == x) {
			return true;
		}
		return false;
	}
	
	public static boolean arrayDoesContainInt(int[] a, int b) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] == b) return true;
		}
		return false;
	}

	public static void drawRect(Rect r, float zLayer) {
		Sprites.pixel.draw(r.x, r.y, zLayer, r.w, 1);
		Sprites.pixel.draw(r.x, r.y, zLayer, 1, r.h);
		Sprites.pixel.draw(r.x2()-1, r.y, zLayer, 1, r.h);
		Sprites.pixel.draw(r.x, r.y2()-1, zLayer, r.w, 1);
	}
	
	public static void println(String s) {
		Game.debugger.displayedExtra = s;
	}
//	public Rect sRectToARect(Rect sRect) {
//		return new Rect(Game.hero.aRect.x - (Game.hero.sRect.x - sRect.x), Game.hero.aRect.y - (Game.hero.sRect.y - sRect.y), sRect.w, sRect.h);
//	}
}
