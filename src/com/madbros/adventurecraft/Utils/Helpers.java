package com.madbros.adventurecraft.Utils;

import org.lwjgl.input.Mouse;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Cells.*;

public class Helpers {		
	//wrappers for mouse stuff since display thinks y starts at the bottom...
	public static int getX() {
		return Mouse.getX();
	}
	
	public static int getY() {
		return Game.currentScreenSizeY - Mouse.getY();
	}
	
	public static Rect getMouseRect() {
		return new Rect(getX(), getY(), 1, 1);
	}
	
	public static boolean containsXNumberOfItemsInCells(int x, int itemId, Cell[] cells) {
		int count = 0;
		for(int i = 0; i < cells.length; i++) {
			if(cells[i].item.id == itemId) count++;
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
		Textures.pixel.draw(r.x, r.y, zLayer, r.w, 1);
		Textures.pixel.draw(r.x, r.y, zLayer, 1, r.h);
		Textures.pixel.draw(r.x2()-1, r.y, zLayer, 1, r.h);
		Textures.pixel.draw(r.x, r.y2()-1, zLayer, r.w, 1);
	}
	
	public static void println(String s) {
		Game.debugger.displayedExtra = s;
	}
//	public Rect sRectToARect(Rect sRect) {
//		return new Rect(Game.hero.aRect.x - (Game.hero.sRect.x - sRect.x), Game.hero.aRect.y - (Game.hero.sRect.y - sRect.y), sRect.w, sRect.h);
//	}
}
