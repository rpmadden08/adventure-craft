package com.madbros.adventurecraft;

import java.util.HashMap;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.TileTypes.*;

import static com.madbros.adventurecraft.Constants.*;

public class Helpers {	
	public static HashMap<Integer, Item> itemHash = new HashMap<Integer, Item>();
	public static HashMap<Integer, Tile> tileHash = new HashMap<Integer, Tile>(); 

	public static HashMap<Integer, Integer> topLeftAutoTileHash = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> topRightAutoTileHash = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> bottomLeftAutoTileHash = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> bottomRightAutoTileHash = new HashMap<Integer, Integer>();
	
	public Helpers() {
		itemHash.put(NONE, new NoItem());
		itemHash.put(GRASS_SEED, new GrassSeed());
		itemHash.put(EARTH_CLUMP, new EarthClump());
		itemHash.put(SAND_CLUMP, new SandClump());
		itemHash.put(LOG, new Log());
		itemHash.put(PLANK, new Plank());
		itemHash.put(SHOVEL, new Shovel());
		itemHash.put(SWORD, new Sword());
		
		tileHash.put(GRASS, new GrassTile());
		tileHash.put(DIRT, new DirtTile());
		tileHash.put(WATER, new WaterTile());
		tileHash.put(TREE, new TreeTile());
//		public static final int AIR = 0;
//		public static final int GRASS = 1;
//		public static final int DIRT = 2;
//		public static final int SAND = 3;
//		public static final int BEDROCK = 4;
//		public static final int WATER = 5;
//		public static final int TREE = 6;
//		public static final int HOLE = 7;
		
		topLeftAutoTileHash.put(0, SINGLE_TILE);
		topLeftAutoTileHash.put(1, SINGLE_TILE);
		topLeftAutoTileHash.put(2, LEFT_TILE);
		topLeftAutoTileHash.put(3, LEFT_TILE);
		topLeftAutoTileHash.put(8, TOP_TILE);
		topLeftAutoTileHash.put(9, TOP_TILE);
		topLeftAutoTileHash.put(10, MERGE_TILE);
		topLeftAutoTileHash.put(11, MIDDLE_TILE);
		
		topRightAutoTileHash.put(0, SINGLE_TILE);
		topRightAutoTileHash.put(2, RIGHT_TILE);
		topRightAutoTileHash.put(4, SINGLE_TILE);
		topRightAutoTileHash.put(6, RIGHT_TILE);
		topRightAutoTileHash.put(16, TOP_TILE);
		topRightAutoTileHash.put(18, MERGE_TILE);
		topRightAutoTileHash.put(20, TOP_TILE);
		topRightAutoTileHash.put(22, MIDDLE_TILE);

		bottomLeftAutoTileHash.put(0, SINGLE_TILE);
		bottomLeftAutoTileHash.put(8, BOTTOM_TILE);
		bottomLeftAutoTileHash.put(32, SINGLE_TILE);
		bottomLeftAutoTileHash.put(40, BOTTOM_TILE);
		bottomLeftAutoTileHash.put(64, LEFT_TILE);
		bottomLeftAutoTileHash.put(72, MERGE_TILE);
		bottomLeftAutoTileHash.put(96, LEFT_TILE);
		bottomLeftAutoTileHash.put(104, MIDDLE_TILE);

		bottomRightAutoTileHash.put(0, SINGLE_TILE);
		bottomRightAutoTileHash.put(16, BOTTOM_TILE);
		bottomRightAutoTileHash.put(64, RIGHT_TILE);
		bottomRightAutoTileHash.put(80, MERGE_TILE);
		bottomRightAutoTileHash.put(128, SINGLE_TILE);
		bottomRightAutoTileHash.put(144, BOTTOM_TILE);
		bottomRightAutoTileHash.put(192, RIGHT_TILE);
		bottomRightAutoTileHash.put(208, MIDDLE_TILE);
	}
	
	public static void drawRect(int x, int y, int w, int h) {
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x, y); 
			GL11.glVertex2f(x, y+h);         
			GL11.glVertex2f(x+w, y+h);
			GL11.glVertex2f(x+w, y);
		GL11.glEnd();
	}
	
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
			if(a[i] == b) {
				return true;
			}
		}
		return false;
	}
}
