package com.madbros.adventurecraft;

import java.util.HashMap;

import com.madbros.adventurecraft.Items.*;
import com.madbros.adventurecraft.TileTypes.*;
import com.madbros.adventurecraft.Utils.*;

public class Constants {
	public static HashMap<Integer, Item> ITEM_HASH = new HashMap<Integer, Item>();
	public static HashMap<Integer, Tile> TILE_HASH = new HashMap<Integer, Tile>(); 
	
	public static HashMap<Integer, Integer> TOP_LEFT_AUTO_TILE_HASH = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> TOP_RIGHT_AUTO_TILE_HASH = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> BOTTOM_LEFT_AUTO_TILE_HASH = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Integer> BOTTOM_RIGHT_AUTO_TILE_HASH = new HashMap<Integer, Integer>();
	
	//game constants
	public static final int INITIAL_WINDOW_WIDTH = 640;// 640 1280
	public static final int INITIAL_WINDOW_HEIGHT = 480;//480 800
	public static final int RENDER_MARGIN = 1;
	public static final int FRAME_RATE = 60;
	public static final String GAME_TITLE = "Adventure Craft";
	
	public static final int PIXEL_MIN = 0;
	public static final int PIXEL_MAX = 32;
	
	public static final String SAVE_LOC = "saves/";
	public static final String CHUNKS_FOLDER = "chunks/";
	
	//character constants
	public static final int CHARACTER_SIZE = 64;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	public static final int STAND_UP = 0;
	public static final int STAND_DOWN = 1;
	public static final int STAND_RIGHT = 2;
	public static final int STAND_LEFT = 3;
	public static final int WALK_UP = 4;
	public static final int WALK_DOWN = 5;
	public static final int WALK_RIGHT = 6;
	public static final int WALK_LEFT = 7;
	public static final int SLASH_UP = 8;
	public static final int SLASH_DOWN = 9;
	public static final int SLASH_RIGHT = 10;
	public static final int SLASH_LEFT = 11;
	

	public static final boolean VERTICAL = true;
	public static final boolean HORIZONTAL = false;
	
	
	//block constants
	public static final int TILE_SIZE = 32;
	public static final int TEXTURE_SIZE = 32;
	
	//inventory constants
	public static final int INV_MENU_TILE_SIZE = 32;
	public static final int INV_SLOT_SIZE = 41;
	public static final int ITEM_SIZE =32;
	
	public static final int INV_LENGTH = 10;
	public static final int INV_HEIGHT = 4;
	
	public static final Margin INV_MENU_MARGIN = new Margin(46, 50, 50, 100);
	public static final Margin INV_SLOT_MARGIN = new Margin(0, 2, 0, 2);
	public static final Margin INV_MENU_SLOT_MARGIN =new Margin(0, 0, 0, 0);
	
	public static final Rect INV_BACKDROP_RECT = new Rect(INV_MENU_MARGIN.left, INV_MENU_MARGIN.top,INV_SLOT_SIZE*INV_LENGTH, INV_SLOT_SIZE*INV_LENGTH+18);
	//public static final Rect INV_BAR_RECT = new Rect((INITIAL_WINDOW_WIDTH - (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * INV_LENGTH + INV_SLOT_SIZE) / 2, INITIAL_WINDOW_HEIGHT-50, 64, 64);
	public static final Rect INV_BAR_RECT = new Rect(2,50, 64, 64);
	
	//public static final Rect INV_BAG_RECT = new Rect((INITIAL_WINDOW_WIDTH - (INV_SLOT_SIZE + INV_SLOT_MARGIN.right) * INV_LENGTH + INV_SLOT_SIZE) / 2, INV_BACKDROP_RECT.y2() - INV_BAR_RECT.h*4);	//FIXME: this is dumb
	public static final Rect INV_BAG_RECT = new Rect(56, 60, INV_SLOT_SIZE*4, INV_SLOT_SIZE*INV_LENGTH);
	
	//public static final Rect INV_CHAR_RECT = new Rect(INV_BACKDROP_RECT.x2() - CHARACTER_SIZE*4, INV_BACKDROP_RECT.y + 30, CHARACTER_SIZE*4, CHARACTER_SIZE*4);
	public static final Rect INV_CHAR_RECT = new Rect(180, 50,100,100);
	
	public static final Rect INV_CRAFTING_RECT = new Rect(230, 347, 2*(INV_SLOT_SIZE+2), 2*(INV_SLOT_SIZE+2));
	public static final int INV_ANIMATION_CHANGE = 100;
	
	//item/slot constants
	public static final int ITEM_OFFSET = 5;
	public static final int STANDARD_SLOT = 0;
	public static final int CRAFTING_SLOT = 1;
	public static final int CRAFTED_SLOT = 2;
	public static final int CLOTHING_SLOT = 3;
	public static final int HELMET_SLOT = 5;
	public static final int ARMOR_SLOT = 6;
	public static final int LEGGINGS_SLOT = 7;
	public static final int BOOTS_SLOT = 8;
	
	
	//level constants
	public static final int CHUNK_SIZE = 16;
	public static final int CHUNKS_IN_A_ROW = 5;	//also columns
	public static final int CHUNKS_LENGTH_TOTAL = 20; //200
	public static final int CHUNK_BLOOM_MARGIN = 10;
	public static final int TILES_PER_ROW = CHUNK_SIZE*CHUNKS_IN_A_ROW;	//also columns
	
	//game states
	public static enum State {
		MAIN, INVENTORY, MAIN_MENU, NEW_GAME_MENU;
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
	public static final int DARK_DIRT = 8;
	public static final int SAPLING = 9;
	public static final int TREE_LEAF = 10;
	public static final int DIRT_MOUNTAIN_BOTTOM = 11;
	public static final int DIRT_MOUNTAIN_TOP = 12;
	public static final int CAMPFIRE = 13;
	public static final int TREE_LEAF_RAIN = 14;
	public static final int SAPLING_RAIN = 15;
	public static final int DARK_GRASS = 16;
	public static final int SNOW = 17;
	public static final int TREE_LEAF_PINE = 18;
	public static final int SAPLING_PINE = 19;
	public static final int TREE_PINE = 20;
	public static final int POTATO_TILE = 21;
	
	
	//Layers
	public static final int DARK_DIRT_LAYER = 0;
	public static final int LIGHT_DIRT_LAYER = 1;
	public static final int GRASS_LAYER = 2;
	public static final int WATER_LAYER = 3;
	public static final int OBJECT_LAYER = 4;
	public static final int TREE_LEFT_0 = 5;
	public static final int TREE_CENTER_0 = 6;
	public static final int TREE_RIGHT_0 = 7;
	public static final int ABOVE_LAYER_1 = 8;
	public static final int TREE_LEFT_1 = 9;
	public static final int TREE_CENTER_1 = 10;
	public static final int TREE_RIGHT_1 = 11;
	public static final int ABOVE_LAYER_2 = 12;
	public static final int TREE_LEFT_2 = 13;
	public static final int TREE_CENTER_2 = 14;
	public static final int TREE_RIGHT_2 = 15;
	public static final int ABOVE_LAYER_3 = 16;
	public static final int TREE_LEFT_3 = 17;
	public static final int TREE_CENTER_3 = 18;
	public static final int TREE_RIGHT_3 = 19;
	public static final int ABOVE_LAYER_4 = 20;
	public static final int ABOVE_LAYER_5 = 21;
	public static final int ABOVE_LAYER_6 = 22;

	//z-depth
	public static final float Z_DARK_DIRT = -0.9f;
	public static final float Z_LIGHT_DIRT = -0.89f;
	public static final float Z_GRASS = -0.88f;
	public static final float Z_WATER = -0.87f;
	public static final float Z_OBJECT = -0.86f;
	
	public static final float Z_COLLISION_TILES = -0.85f;
	public static final float Z_TILE_HIGHLIGHT = -0.7f;

	
	public static final float Z_CHARACTER = -0.65f;
	public static final float Z_CHARACTER_PANTS = -0.64f;
	public static final float Z_CHARACTER_ARMOR = -0.63f;
	
	public static final float Z_COLLISION_RECTS = -0.57f;
	public static final float Z_ABOVE_LAYER = -0.55f;
	public static final float Z_ABOVE_LAYER2 = -0.54f;
	public static final float Z_BOUNDARIES = -0.0f;
	
	public static final float Z_HEALTHBAR = -0.52f;
	
	public static final float Z_INV_BACKDROP = -0.5f;
	public static final float Z_INV_SLOTS = -0.4f;

	public static final float Z_INV_HIGHLIGHT = -0.35f;
	public static final float Z_INV_SELECT = -0.3f;
	public static final float Z_INV_ITEMS = -0.25f;
	public static final float Z_INV_CHARACTER = -0.23f;
	public static final float Z_BUTTONS = -0.2f;
	public static final float Z_BUTTONS_HIGHLIGHT = -0.1f;
	public static final float Z_MINIMAP = 0f;
	
	
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
	
	public static final int SAPLING_ITEM = 8;
	
	public static final int IRON_HELMET = 9;
	public static final int IRON_ARMOR = 10;
	public static final int IRON_LEGGINGS = 11;
	public static final int IRON_BOOTS = 12;
	public static final int CAMPFIRE_ITEM = 13;
	public static final int AXE = 14;
	public static final int LONG_SWORD = 15;
	public static final int BAT_WING = 16;
	public static final int HOE = 17;
	public static final int SEED_POTATO = 18;
	public static final int POTATOES = 19;
	
	
	//Clothing Types
	public static final int HELMET = 0;
	public static final int ARMOR = 1;
	public static final int LEGGINGS = 2;
	public static final int BOOTS = 3;
	
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
	public static final int TOP_LEFT_TILE= 0;
	public static final int TOP_TILE = 3;
	public static final int TOP_RIGHT_TILE= 6;
	public static final int LEFT_TILE = 1;
	public static final int MIDDLE_TILE = 4;
	public static final int RIGHT_TILE = 7;
	public static final int BOTTOM_LEFT_TILE = 2;
	public static final int BOTTOM_TILE = 5;
	public static final int BOTTOM_RIGHT_TILE = 8;
	
	public static final int MERGE_TILE_TOP_LEFT = 9;
	public static final int MERGE_TILE_TOP_RIGHT = 11;
	public static final int MERGE_TILE_BOTTOM_LEFT = 10;
	public static final int MERGE_TILE_BOTTOM_RIGHT = 12;
	
	//Main Menu
	public static final int MAIN_MENU_WIDTH = 100;
	public static final int MAIN_MENU_HEIGHT = 30;
	public static final int MAIN_MENU_STARTX = INITIAL_WINDOW_WIDTH/2 - MAIN_MENU_WIDTH / 2;
	public static final int MAIN_MENU_STARTY = 40;
	
	public Constants() {
		ITEM_HASH.put(NONE, new NoItem());
		ITEM_HASH.put(GRASS_SEED, new GrassSeed());
		ITEM_HASH.put(EARTH_CLUMP, new EarthClump());
		ITEM_HASH.put(SAND_CLUMP, new SandClump());
		ITEM_HASH.put(LOG, new Log());
		ITEM_HASH.put(PLANK, new Plank());
		ITEM_HASH.put(SHOVEL, new Shovel());
		ITEM_HASH.put(AXE, new Axe());
		ITEM_HASH.put(SWORD, new Sword());
		ITEM_HASH.put(LONG_SWORD, new LongSword());
		ITEM_HASH.put(SAPLING_ITEM, new Sapling());
		ITEM_HASH.put(IRON_HELMET, new IronHelmet());
		ITEM_HASH.put(IRON_ARMOR, new IronArmor());
		ITEM_HASH.put(IRON_BOOTS, new IronBoots());
		ITEM_HASH.put(IRON_LEGGINGS, new IronLeggings());
		ITEM_HASH.put(CAMPFIRE_ITEM, new CampfireItem());
		ITEM_HASH.put(BAT_WING, new BatWing());
		ITEM_HASH.put(SEED_POTATO, new SeedPotato());
		ITEM_HASH.put(POTATOES, new Potatoes());
		
		TILE_HASH.put(GRASS, new GrassTile());
		TILE_HASH.put(DIRT, new DirtTile());
		TILE_HASH.put(WATER, new WaterTile());
		TILE_HASH.put(TREE, new TreeTile());
		TILE_HASH.put(TREE_PINE, new TreePineTile());
		TILE_HASH.put(DARK_DIRT, new DarkDirtTile());
		TILE_HASH.put(SAND, new SandTile());
		TILE_HASH.put(AIR, new NoTile());
		TILE_HASH.put(SAPLING, new SaplingTile());
		TILE_HASH.put(POTATO_TILE, new PotatoTile());
		TILE_HASH.put(SAPLING_RAIN, new SaplingRainTile());
		TILE_HASH.put(TREE_LEAF, new TreeLeafTile());
		TILE_HASH.put(TREE_LEAF_RAIN, new TreeLeafRainTile());
		TILE_HASH.put(TREE_LEAF_PINE, new TreeLeafPineTile());
		TILE_HASH.put(HOLE, new HoleTile());
		
		TILE_HASH.put(DIRT_MOUNTAIN_BOTTOM, new DirtMountainBottomTile());
		TILE_HASH.put(DIRT_MOUNTAIN_TOP, new DirtMountainTopTile());
		TILE_HASH.put(CAMPFIRE, new Campfire());
		TILE_HASH.put(DARK_GRASS, new DarkGrassTile());
		TILE_HASH.put(SNOW, new SnowTile());
		
		
		TOP_LEFT_AUTO_TILE_HASH.put(0, TOP_LEFT_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(1, TOP_LEFT_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(2, LEFT_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(3, LEFT_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(8, TOP_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(9, TOP_TILE);
		TOP_LEFT_AUTO_TILE_HASH.put(10, MERGE_TILE_BOTTOM_RIGHT);	//MERGE_TILE_TOP_LEFT);
		TOP_LEFT_AUTO_TILE_HASH.put(11, MIDDLE_TILE);
		
		TOP_RIGHT_AUTO_TILE_HASH.put(0, TOP_RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(2, RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(4, TOP_RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(6, RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(16, TOP_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(18, MERGE_TILE_BOTTOM_LEFT);	//MERGE_TILE_TOP_RIGHT);
		TOP_RIGHT_AUTO_TILE_HASH.put(20, TOP_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(22, MIDDLE_TILE);

		BOTTOM_LEFT_AUTO_TILE_HASH.put(0, BOTTOM_LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(8, BOTTOM_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(32, BOTTOM_LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(40, BOTTOM_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(64, LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(72, MERGE_TILE_TOP_RIGHT);	//MERGE_TILE_BOTTOM_LEFT);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(96, LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(104, MIDDLE_TILE);

		BOTTOM_RIGHT_AUTO_TILE_HASH.put(0, BOTTOM_RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(16, BOTTOM_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(64, RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(80, MERGE_TILE_TOP_LEFT);	//MERGE_TILE_BOTTOM_RIGHT);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(128, BOTTOM_RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(144, BOTTOM_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(192, RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(208, MIDDLE_TILE);
	}
}