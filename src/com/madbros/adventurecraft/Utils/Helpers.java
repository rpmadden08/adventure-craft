package com.madbros.adventurecraft.Utils;

import static com.madbros.adventurecraft.Constants.CHUNK_SIZE;
import static com.madbros.adventurecraft.Constants.TILE_HASH;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Slots.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.TileTypes.Tile;

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
	
	public static Block[][] chunkToBlockArray(Chunk chunk) {
		Block[][] blockArray = new Block[CHUNK_SIZE][CHUNK_SIZE];
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Tile[] t = new Tile[23];
				for(int i = 0; i < 23; i++) {
					int id = chunk.ids[x][y][i];	
					t[i] = TILE_HASH.get(id).createNew();
					t[i].currentSpriteId = chunk.currentTextures[x][y][i];
				}
				
				long absX = chunk.absX[x][y];
				long absY = chunk.absY[x][y]; 
				boolean isUnfinished = chunk.isUnfinished[x][y];
				blockArray[x][y] = new Block(t, (int)absX, (int)absY, isUnfinished);
				blockArray[x][y].isUnfinished = isUnfinished;
			}
		}
		return blockArray;
	}
	
	public static void println(String s) {
		Game.debugger.displayedExtra = s;
	}
//	public Rect sRectToARect(Rect sRect) {
//		return new Rect(Game.hero.aRect.x - (Game.hero.sRect.x - sRect.x), Game.hero.aRect.y - (Game.hero.sRect.y - sRect.y), sRect.w, sRect.h);
//	}
}
