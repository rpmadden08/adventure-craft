package com.madbros.adventurecraft;

//com.madbros.adventurecraft.Items.*;

public class Constants {
	//game constants
	public static final int INITIAL_WINDOW_WIDTH = 640;
	public static final int INITIAL_WINDOW_HEIGHT = 480;
	public static final int RENDER_MARGIN = 2;
	public static final int FRAME_RATE = 60;
	public static final String GAME_TITLE = "Block Game";
	
	public static final int PIXEL_MIN = 0;
	public static final int PIXEL_MAX = 32;
	
	//character constants
	public static final int CHARACTER_SIZE = 24;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	public static final int WALK_DOWN = 0;
	public static final int WALK_RIGHT = 3;
	public static final int WALK_UP = 6;
	public static final int WALK_LEFT = 9;
	public static final boolean VERTICAL = true;
	public static final boolean HORIZONTAL = false;
	
	
	//block constants
	public static final int TILE_SIZE = 16;
	
	//inventory constants
	public static final int INV_MENU_TILE_SIZE = 32;
	public static final int INV_CELL_SIZE = 25;
	public static final int ITEM_SIZE = 16;
	
	public static final int INV_LENGTH = 8;
	public static final int INV_HEIGHT = 4;
	
	public static final Margin INV_MENU_MARGIN = new Margin(50, 50, 20, 100);
	public static final Margin INV_CELL_MARGIN = new Margin(0, 20, 0, 8);
	
	public static final Rect INV_BACKDROP_RECT = new Rect(INV_MENU_MARGIN.left, INV_MENU_MARGIN.top, 
														  INITIAL_WINDOW_WIDTH - INV_MENU_MARGIN.getHorizontalLength(),
														  INITIAL_WINDOW_HEIGHT - INV_MENU_MARGIN.getVerticalLength());
	public static final Rect INV_BAR_RECT = new Rect((INITIAL_WINDOW_WIDTH - (INV_CELL_SIZE + INV_CELL_MARGIN.right) * INV_LENGTH + INV_CELL_SIZE) / 2, INITIAL_WINDOW_HEIGHT-50);
	public static final Rect INV_BAG_RECT = new Rect((INITIAL_WINDOW_WIDTH - (INV_CELL_SIZE + INV_CELL_MARGIN.right) * INV_LENGTH + INV_CELL_SIZE) / 2, INV_BACKDROP_RECT.y2()-150);
	public static final Rect INV_CHAR_RECT = new Rect(INV_BACKDROP_RECT.x2() - 104, INV_BACKDROP_RECT.y + 30, CHARACTER_SIZE*4, CHARACTER_SIZE*4);
	public static final Rect INV_CRAFTING_RECT = new Rect(130, 50, 2*(INV_CELL_SIZE+2), 2*(INV_CELL_SIZE+2));
	
	//item/cell constants
	public static final int ITEM_OFFSET = 5;
	public static final int BAR = 0;
	public static final int BAG = 1;
	public static final int CRAFTING = 2;
	public static final int CRAFTED = 3;
	
	//level constants
	public static final int CHUNK_SIZE = 16;	//keep between 12 and 16
	public static final int CHUNKS_IN_A_ROW = 5;	//also columns
	public static final int TILES_PER_ROW = CHUNK_SIZE*CHUNKS_IN_A_ROW;	//also columns
	
	//game states
	public static enum State {
		MAIN, INVENTORY, MAIN_MENU, DEBUG_MAIN, DEBUG_INVENTORY;
	}
	
	//Blocks
	public static final int AIR = 0;
	public static final int GRASS = 1;
	public static final int DIRT = 2;
	public static final int SAND = 3;
	public static final int BEDROCK = 4;
	public static final int WATER = 5;
	public static final int TREE = 6;
	public static final int HOLE = 7;
	
	//Collisions
	public static enum Collision {
		X_COLLISION, Y_COLLISION, DOUBLE_COLLISION;
	}
	
	//Mouse
	public static final int LEFT_MOUSE_BUTTON = 0;
	public static final int RIGHT_MOUSE_BUTTON = 1;
	public static final int MIDDLE_MOUSE_BUTTON = 2;
	
	
	//Animations
	public static final int[][] BREAKING_ANIMATION = {{0,1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}};
	
	//Items
	public static final int NONE = 0;
	public static final int EMPTY = 0;
	public static final int GRASS_SEED = 1;
	public static final int EARTH_CLUMP = 2;
	public static final int SAND_CLUMP = 3;
	public static final int LOG = 4;
	public static final int PLANK = 5;
	
	public static final int SHOVEL = 6;
	
	public static final int SWORD = 7;
	
	//Debugger
	public static final int	BYTES_IN_MEGABYTE = 1048576;
	public static final int DEBUG_MENU_SIZEX = 200;
	public static final int DEBUG_MENU_SIZEY = 30;
	public static final float DEBUG_FONT_SIZE = 14f;
	public static final int NUMBER_OF_FRAMES_USED_FOR_SAMPLE_MEAN = 30;
	public static final int DISPLAY_STARTX = 10;
	public static final int DISPLAY_STARTY = 20;
	public static final int DISPLAY_MARGIN = 14;
	
	public static final int BLOCK_COLLISION_BUTTON = 0;
	public static final int RECT_COLLISION_BUTTON = 1;
	public static final int CHUNK_BOUNDARIES_BUTTON = 2;
	public static final int COLLISION_DETECTION_BUTTON = 3;
	
	//Auto Tiling
	public static final int SINGLE_TILE = 0;
	public static final int MERGE_TILE = 1;
	public static final int TOP_LEFT_TILE= 2;
	public static final int TOP_TILE = 3;
	public static final int TOP_RIGHT_TILE= 4;
	public static final int LEFT_TILE = 5;
	public static final int MIDDLE_TILE = 6;
	public static final int RIGHT_TILE = 7;
	public static final int BOTTOM_LEFT_TILE = 8;
	public static final int BOTTOM_TILE = 9;
	public static final int BOTTOM_RIGHT_TILE = 10;
	public static final int UL_TOP_LEFT_CORNER_TILE = 11;
	
	//Generic
	public static final boolean OFF = false;
	public static final boolean ON = true;
}
			
//	public static final int UL_TOP_TILE = 5;
//	public static final int UL_LEFT_TILE = 3;
//	public static final int UL_TOP_LEFT_TILE = 1;
//	public static final int UL_TOP_LEFT_CORNER_TILE = 2;
//	
//	public static final int UR_TOP_TILE = 7;
//	public static final int UR_RIGHT_TILE = 3;

