package com.madbros.adventurecraft;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
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
	
	//game constants                                       Small  Big    Retina
	public static final int INITIAL_WINDOW_WIDTH = 640;// 640    1280   1440
	public static final int INITIAL_WINDOW_HEIGHT = 480;// 480    800    900
	public static final int RENDER_MARGIN = 1;
	public static final int FRAME_RATE = 60;
	public static final String GAME_TITLE = "Adventure Craft";
	
	public static final int PIXEL_MIN = 0;
	public static final int PIXEL_MAX = 32;
	
	public static final String SAVE_LOC = "saves/";
	public static final String CHUNKS_FOLDER = "chunks/";
	public static final String CHESTS_FOLDER = "chests/";
	public static final String FURNACES_FOLDER = "furnaces/";
	public static final String OVERWORLD_FOLDER = "overworld/";
	public static final String UNDERGROUND_1_FOLDER = "underground1/";
	
	
	public static final Color HIGHLIGHT_COLOR = new Color(0.8f,0.8f,0.8f,0.6f);
	
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
	
	//status effects useable
	public static final int NO_STATUS_EFFECT = 0;
	public static final int HARMING_APPLIED = 1;
	public static final int SLOWNESS_APPLIED = 2;
	
	//status effects timed
	public static final int NO_STATUS_EFFECT_TIMED = 0;
	public static final int SLOWNESS_MOB = 1;
	public static final int SPEED = 2;
	public static final int SLOWNESS = 3;
	
	//Loot probabilities
	//public static final int ALWAYS_LOOT = 0;      //Will always get this..
	public static final int COMMON_LOOT = 1;      //70% chance to get 
	public static final int UNCOMMON_LOOT = 2;    //29% chance to get
	public static final int RARE_LOOT = 3;        //0.9% chance to get
	public static final int ULTRA_RARE_LOOT = 4;  //0.1% chance to get
	
	
	
	
	
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
	
	public static final Rect INV_BACKDROP_RECT = new Rect(INV_MENU_MARGIN.left, INV_MENU_MARGIN.top,INV_SLOT_SIZE*INV_LENGTH+181, INV_SLOT_SIZE*INV_LENGTH+18);
	public static final Rect INV_BAR_RECT = new Rect(2,50, 64, 64);
	public static final Rect INV_BAG_RECT = new Rect(56, 60, INV_SLOT_SIZE*4, INV_SLOT_SIZE*INV_LENGTH);
	public static final Rect INV_CHAR_RECT = new Rect(351, 35,100,100);
	
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
	public static final int CHUNKS_LENGTH_TOTAL = 20; //50
	public static final int CHUNK_BLOOM_MARGIN = 10;
	public static final int TILES_PER_ROW = CHUNK_SIZE*CHUNKS_IN_A_ROW;	//also columns
	
	//Game States
	public static enum State {
		MAIN, INVENTORY, MAIN_MENU, NEW_GAME_MENU, FURNACE, LOADING, CHEST, CAULDRON;
	}
	
	//Tiles
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
	public static final int TABLE_TILE = 22;
	public static final int CHEST_TILE = 23;
	public static final int FURNACE_TILE = 24;
	public static final int FURNACE_TOP = 25;
	public static final int CAULDRON_TILE = 26;
	public static final int DIRT_MOUNTAIN_COAL_BOTTOM = 27;
	public static final int DIRT_MOUNTAIN_COAL_MIDDLE = 28;
	public static final int TREE_RAIN = 29;
	public static final int SPACE = 30;
	public static final int BARREL = 31;
	public static final int BARREL_TOP = 32;
	public static final int STONE_MOUNTAIN_TOP = 33;
	public static final int STONE_MOUNTAIN_BOTTOM = 34;
	public static final int STONE_MOUNTAIN_COAL_TOP = 35;
	public static final int STONE_MOUNTAIN_COAL_BOTTOM = 36;
	public static final int STONE_MOUNTAIN_TIN_TOP = 37;
	public static final int STONE_MOUNTAIN_TIN_BOTTOM = 38;
	public static final int STONE_MOUNTAIN_COPPER_TOP = 39;
	public static final int STONE_MOUNTAIN_COPPER_BOTTOM = 40;
	public static final int CACTUS_SAPLING = 41;
	public static final int CACTUS = 42;
	public static final int CACTUS_LEAF = 43;
	public static final int TALL_GRASS_A_TILE = 44;
	public static final int TALL_GRASS_B_TILE = 45;
	public static final int TALL_GRASS_C_TILE = 46;
	public static final int RED_FLOWERS_TILE = 47;
	public static final int YELLOW_FLOWERS_TILE = 48;
	public static final int RED_MUSHROOM_TILE = 49;
	public static final int BROWN_MUSHROOM_TILE = 50;
	public static final int TORCH_TILE = 51;
	public static final int WHEAT_TILE = 52;
	public static final int TOMATO_TILE = 53;
	public static final int ZUCCHINI_TILE = 54;
	public static final int CORN_TILE = 55;
	public static final int CARROT_TILE = 56;
	public static final int ARTICHOKE_TILE = 57;
	public static final int PEPPER_TILE = 58;
	public static final int WHEAT_TOP = 59;
	public static final int TOMATO_TOP = 60;
	public static final int CORN_TOP = 61;
	public static final int PEPPER_TOP = 62;
	public static final int STAIRS_UP_BOTTOM_TILE = 63;
	public static final int STAIRS_UP_TOP_TILE = 64;
	public static final int STAIRS_DOWN_TILE = 65;
	public static final int FIRE_PIT_TILE = 66;
	public static final int DIRT_MOUNTAIN_MIDDLE = 67;
	public static final int DIRT_MOUNTAIN_COPPER_MIDDLE = 68;
	public static final int DIRT_MOUNTAIN_COPPER_BOTTOM = 69;
	public static final int DIRT_MOUNTAIN_TIN_BOTTOM = 70;
	public static final int DIRT_MOUNTAIN_TIN_MIDDLE = 71;
	public static final int BEEHIVE_TILE = 72;
	
	
	
	

	
	
	
	
	//ore generation markings
	public static final int COAL_MARK = 1001;
	public static final int TIN_MARK = 1002;
	public static final int COPPER_MARK = 1003;
	public static final int GRASS_MARK = 1004;
	
	//Types of WorkSpaces
	public static final int BARE_HANDS_WORKSPACE = 0;
	public static final int TABLE_WORKSPACE = 1;
	
	
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
	public static final int TABLE = 20;
	public static final int CHEST = 21;
	public static final int FURNACE = 22;
	public static final int BAKED_POTATOES = 23;
	public static final int CAULDRON = 24;
	public static final int PICK = 25;
	public static final int SNOW_ITEM = 26;
	public static final int DARK_GRASS_ITEM = 27;
	public static final int DIRT_MOUNTAIN_ITEM = 28;
	public static final int COAL_ITEM = 29;
	public static final int STONE_MOUNTAIN_ITEM = 30;
	public static final int TIN_ITEM = 31;
	public static final int COPPER_ITEM = 32;
	public static final int CACTUS_SEED = 33;
	public static final int TALL_GRASS_A = 34;
	public static final int RED_MUSHROOM = 35;
	public static final int BROWN_MUSHROOM = 36;
	public static final int RED_FLOWERS = 37;
	public static final int YELLOW_FLOWERS = 38;
	public static final int TORCH = 39;
	public static final int WHEAT = 40;
	public static final int WHEAT_SPROUT = 41;
	public static final int TOMATO = 42;
	public static final int TOMATO_SPROUT = 43;
	public static final int CARROT = 44;
	public static final int CARROT_SPROUT = 45;
	public static final int ZUCCHINI = 46;
	public static final int ZUCCHINI_SPROUT = 47;
	public static final int CORN = 48;
	public static final int CORN_SPROUT = 49;
	public static final int ARTICHOKE = 50;
	public static final int ARTICHOKE_SPROUT = 51;
	public static final int PEPPER = 52;
	public static final int PEPPER_SPROUT = 53;
	public static final int COPPER_BAR = 54;
	public static final int TIN_BAR = 55;
	public static final int IRON_BAR = 56;

	public static final int VENOM = 57;
	public static final int BLUE_SLIME = 58;
	public static final int GREEN_SLIME = 59;
	public static final int ECTOPLASM = 60;
	public static final int EYEBALL = 61;
	public static final int IMP_HORN = 62;
	public static final int BEETLE_SHELL = 63;
	public static final int FLOWER_PETAL = 64;
	
	public static final int WOODEN_AXE = 65;
	public static final int WOODEN_HOE = 66;
	public static final int WOODEN_PICK = 67;
	public static final int WOODEN_SHOVEL = 68;
	public static final int WOODEN_SWORD = 69;
	
	public static final int TIN_AXE = 70;
	public static final int TIN_HOE = 71;
	public static final int TIN_PICK = 72;
	public static final int TIN_SHOVEL = 73;
	public static final int TIN_SWORD = 74;
	
	public static final int STONE_AXE = 75;
	public static final int STONE_HOE = 76;
	public static final int STONE_PICK = 77;
	public static final int STONE_SHOVEL = 78;
	public static final int STONE_SWORD = 79;
	
	public static final int COPPER_AXE = 90;
	public static final int COPPER_HOE = 91;
	public static final int COPPER_PICK = 92;
	public static final int COPPER_SHOVEL = 93;
	public static final int COPPER_SWORD = 94;
	
	public static final int IRON_AXE = 95;
	public static final int IRON_HOE = 96;
	public static final int IRON_PICK = 97;
	public static final int IRON_SHOVEL = 98;
	public static final int IRON_SWORD = 99;
	public static final int LEATHER_BOOTS = 100;
	public static final int LEATHER_LEGGINGS = 101;
	public static final int LEATHER_ARMOR = 102;
	public static final int LEATHER_HAT = 103;
	public static final int TIN_BOOTS = 104;
	public static final int TIN_LEGGINGS = 105;
	public static final int TIN_ARMOR = 106;
	public static final int TIN_HELMET = 107;
	public static final int COPPER_BOOTS = 108;
	public static final int COPPER_LEGGINGS = 109;
	public static final int COPPER_ARMOR = 110;
	public static final int COPPER_HELMET = 111;
	public static final int STICK = 112;
	public static final int STAIRS_UP = 113;
	public static final int STAIRS_DOWN = 114;
	public static final int LEATHER = 115;
	public static final int FIRE_PIT = 116;
	public static final int FIRE_STARTER = 117;
	public static final int STEAK = 118;
	public static final int COOKED_STEAK = 119;
	public static final int SLIME_BALL = 120;
	public static final int HONEY = 121;
	public static final int WORM_GUTS = 122;
	public static final int STINGER_SWORD = 123;
	public static final int HEALTH_POTION = 124;
	public static final int SLOWNESS_POTION = 125;
	public static final int HARMING_POTION = 126;
	public static final int SPEED_POTION = 127;
	public static final int GLASS_BOTTLE = 128;
	public static final int DIRT_MOUNTAIN_CLUMP = 129;
	public static final int STONE_MOUNTAIN_CLUMP = 130;
	public static final int STONE = 131;
	public static final int BREAD = 132;
	public static final int BEEHIVE = 133;
	
	
	
	
	//Clothing Types
	public static final int HELMET = 0;
	public static final int ARMOR = 1;
	public static final int LEGGINGS = 2;
	public static final int BOOTS = 3;
	
	//Debugger
	public static final int	BYTES_IN_MEGABYTE = 1048576;
	public static final int DEBUG_MENU_SIZEX = 200;
	public static final int DEBUG_MENU_SIZEY = 40;
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
	public static final int TOP_TILE = 12;
	public static final int TOP_RIGHT_TILE= 24;
	public static final int LEFT_TILE = 2;
	public static final int MIDDLE_TILE = 14;
	public static final int RIGHT_TILE = 26;
	public static final int BOTTOM_LEFT_TILE = 4;
	public static final int BOTTOM_TILE = 16;
	public static final int BOTTOM_RIGHT_TILE = 28;
	
	public static final int MERGE_TILE_TOP_LEFT = 39;
	public static final int MERGE_TILE_TOP_RIGHT = 37;
	public static final int MERGE_TILE_BOTTOM_LEFT = 38;
	public static final int MERGE_TILE_BOTTOM_RIGHT = 36;
	
	public static final int bTOP_LEFT_TILE= 6;
	public static final int bTOP_TILE = 18;
	public static final int bTOP_RIGHT_TILE= 30;
	public static final int bLEFT_TILE = 8;
	public static final int bMIDDLE_TILE = 20;
	public static final int bRIGHT_TILE = 32;
	public static final int bBOTTOM_LEFT_TILE =10;
	public static final int bBOTTOM_TILE = 22;
	public static final int bBOTTOM_RIGHT_TILE = 34;
	
//	public static final int bMERGE_TILE_TOP_LEFT = 9;
//	public static final int bMERGE_TILE_TOP_RIGHT = 11;
//	public static final int bMERGE_TILE_BOTTOM_LEFT = 10;
//	public static final int bMERGE_TILE_BOTTOM_RIGHT = 12;
	
	public static final int cTOP_LEFT_TILE= 1;
	public static final int cTOP_TILE = 13;
	public static final int cTOP_RIGHT_TILE= 25;
	public static final int cLEFT_TILE = 3;
	public static final int cMIDDLE_TILE = 15;
	public static final int cRIGHT_TILE = 27;
	public static final int cBOTTOM_LEFT_TILE = 5;
	public static final int cBOTTOM_TILE = 17;
	public static final int cBOTTOM_RIGHT_TILE = 29;
	
//	public static final int cMERGE_TILE_TOP_LEFT = 9;
//	public static final int cMERGE_TILE_TOP_RIGHT = 11;
//	public static final int cMERGE_TILE_BOTTOM_LEFT = 10;
//	public static final int cMERGE_TILE_BOTTOM_RIGHT = 12;
	
	public static final int dTOP_LEFT_TILE= 7;
	public static final int dTOP_TILE = 19;
	public static final int dTOP_RIGHT_TILE= 31;
	public static final int dLEFT_TILE = 9;
	public static final int dMIDDLE_TILE = 21;
	public static final int dRIGHT_TILE = 33;
	public static final int dBOTTOM_LEFT_TILE = 11;
	public static final int dBOTTOM_TILE = 23;
	public static final int dBOTTOM_RIGHT_TILE = 35;
	
//	public static final int dMERGE_TILE_TOP_LEFT = 9;
//	public static final int dMERGE_TILE_TOP_RIGHT = 11;
//	public static final int dMERGE_TILE_BOTTOM_LEFT = 10;
//	public static final int dMERGE_TILE_BOTTOM_RIGHT = 12;
	
	//Main Menu
	public static final int MAIN_MENU_WIDTH = 100;
	public static final int MAIN_MENU_HEIGHT = 30;
	public static int MAIN_MENU_STARTX = Game.currentScreenSizeX/2 - MAIN_MENU_WIDTH / 2;
	public static int MAIN_MENU_STARTY = Game.currentScreenSizeY-230;
	
//	public static final int HOW_TO_PLAY_WIDTH = 640;
//	public static final int HOW_TO_PLAY_HEIGHT = 480;
//	public static int HOW_TO_PLAY_STARTX = Game.currentScreenSizeX /2 - 640 / 2-4;
//	public static int HOW_TO_PLAY_STARTY = Game.currentScreenSizeY /2 - 480 / 2-4;
//	public static Rect HOW_TO_PLAY_RECT = new Rect(HOW_TO_PLAY_STARTX, HOW_TO_PLAY_STARTY,HOW_TO_PLAY_WIDTH,HOW_TO_PLAY_HEIGHT);
//	
	
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
		ITEM_HASH.put(HOE, new Hoe());
		ITEM_HASH.put(PICK, new Pick());
		ITEM_HASH.put(SAPLING_ITEM, new Sapling());
		ITEM_HASH.put(IRON_HELMET, new IronHelmet());
		ITEM_HASH.put(LEATHER_HAT, new LeatherHat());
		ITEM_HASH.put(IRON_ARMOR, new IronArmor());
		ITEM_HASH.put(LEATHER_ARMOR, new LeatherArmor());
		ITEM_HASH.put(IRON_BOOTS, new IronBoots());
		ITEM_HASH.put(LEATHER_BOOTS, new LeatherBoots());
		ITEM_HASH.put(IRON_LEGGINGS, new IronLeggings());
		ITEM_HASH.put(LEATHER_LEGGINGS, new LeatherLeggings());
		
		ITEM_HASH.put(TIN_HELMET, new TinHelmet());
		ITEM_HASH.put(COPPER_HELMET, new CopperHelmet());
		ITEM_HASH.put(TIN_ARMOR, new TinArmor());
		ITEM_HASH.put(COPPER_ARMOR, new CopperArmor());
		ITEM_HASH.put(TIN_BOOTS, new TinBoots());
		ITEM_HASH.put(COPPER_BOOTS, new CopperBoots());
		ITEM_HASH.put(TIN_LEGGINGS, new TinLeggings());
		ITEM_HASH.put(COPPER_LEGGINGS, new CopperLeggings());
		ITEM_HASH.put(STICK, new Stick());
		
		ITEM_HASH.put(STAIRS_UP, new StairsUp());
		ITEM_HASH.put(STAIRS_DOWN, new StairsDown());
		ITEM_HASH.put(FIRE_PIT, new FirePit());
		ITEM_HASH.put(FIRE_STARTER, new FireStarter());
		
		ITEM_HASH.put(CAMPFIRE_ITEM, new Campfire());
		ITEM_HASH.put(BAT_WING, new BatWing());
		ITEM_HASH.put(SEED_POTATO, new SeedPotato());
		ITEM_HASH.put(POTATOES, new Potatoes());
		ITEM_HASH.put(BAKED_POTATOES, new BakedPotatoes());
		ITEM_HASH.put(TABLE, new TableItem());
		ITEM_HASH.put(CHEST, new Chest());
		ITEM_HASH.put(FURNACE, new Furnace());
		ITEM_HASH.put(CAULDRON, new Cauldron());
		ITEM_HASH.put(SNOW_ITEM, new Snow());
		ITEM_HASH.put(DARK_GRASS_ITEM, new DarkGrass());
		ITEM_HASH.put(DIRT_MOUNTAIN_ITEM, new DirtMountain());
		ITEM_HASH.put(STONE_MOUNTAIN_ITEM, new StoneMountain());
		ITEM_HASH.put(COAL_ITEM, new Coal());
		ITEM_HASH.put(TIN_ITEM, new Tin());
		ITEM_HASH.put(COPPER_ITEM, new Copper());
		ITEM_HASH.put(CACTUS_SEED, new CactusSeed());
		ITEM_HASH.put(TALL_GRASS_A, new TallGrassA());
		ITEM_HASH.put(RED_FLOWERS, new RedFlowers());
		ITEM_HASH.put(YELLOW_FLOWERS, new YellowFlowers());
		ITEM_HASH.put(BROWN_MUSHROOM, new BrownMushroom());
		ITEM_HASH.put(RED_MUSHROOM, new RedMushroom());
		ITEM_HASH.put(TORCH, new Torch());
		ITEM_HASH.put(TOMATO, new Tomato());
		ITEM_HASH.put(TOMATO_SPROUT, new TomatoSprout());
		ITEM_HASH.put(CARROT, new Carrot());
		ITEM_HASH.put(CARROT_SPROUT, new CarrotSprout());
		ITEM_HASH.put(WHEAT, new Wheat());
		ITEM_HASH.put(WHEAT_SPROUT, new WheatSprout());
		ITEM_HASH.put(ZUCCHINI, new Zucchini());
		ITEM_HASH.put(ZUCCHINI_SPROUT, new ZucchiniSprout());
		ITEM_HASH.put(CORN, new Corn());
		ITEM_HASH.put(CORN_SPROUT, new CornSprout());
		ITEM_HASH.put(ARTICHOKE, new Artichoke());
		ITEM_HASH.put(ARTICHOKE_SPROUT, new ArtichokeSprout());
		ITEM_HASH.put(PEPPER, new Pepper());
		ITEM_HASH.put(PEPPER_SPROUT, new PepperSprout());
		ITEM_HASH.put(TIN_BAR, new TinBar());
		ITEM_HASH.put(COPPER_BAR, new CopperBar());
		ITEM_HASH.put(IRON_BAR, new IronBar());
		ITEM_HASH.put(VENOM, new Venom());
		ITEM_HASH.put(ECTOPLASM, new Ectoplasm());
		ITEM_HASH.put(FLOWER_PETAL, new FlowerPetal());
		ITEM_HASH.put(BEETLE_SHELL, new BeetleShell());
		ITEM_HASH.put(BLUE_SLIME, new BlueSlime());
		ITEM_HASH.put(GREEN_SLIME, new GreenSlime());
		ITEM_HASH.put(EYEBALL, new Eyeball());
		ITEM_HASH.put(IMP_HORN, new ImpHorn());
		ITEM_HASH.put(LEATHER, new Leather());
		
		ITEM_HASH.put(HEALTH_POTION, new HealthPotion());
		ITEM_HASH.put(SPEED_POTION, new SpeedPotion());
		ITEM_HASH.put(SLOWNESS_POTION, new SlownessPotion());
		ITEM_HASH.put(HARMING_POTION, new HarmingPotion());
		
		ITEM_HASH.put(WOODEN_AXE, new WoodenAxe());
		ITEM_HASH.put(WOODEN_HOE, new WoodenHoe());
		ITEM_HASH.put(WOODEN_PICK, new WoodenPick());
		ITEM_HASH.put(WOODEN_SHOVEL, new WoodenShovel());
		ITEM_HASH.put(WOODEN_SWORD, new WoodenSword());
		
		ITEM_HASH.put(STONE_AXE, new StoneAxe());
		ITEM_HASH.put(STONE_HOE, new StoneHoe());
		ITEM_HASH.put(STONE_PICK, new StonePick());
		ITEM_HASH.put(STONE_SHOVEL, new StoneShovel());
		ITEM_HASH.put(STONE_SWORD, new StoneSword());
		
		ITEM_HASH.put(TIN_AXE, new TinAxe());
		ITEM_HASH.put(TIN_HOE, new TinHoe());
		ITEM_HASH.put(TIN_PICK, new TinPick());
		ITEM_HASH.put(TIN_SHOVEL, new TinShovel());
		ITEM_HASH.put(TIN_SWORD, new TinSword());
		
		ITEM_HASH.put(COPPER_AXE, new CopperAxe());
		ITEM_HASH.put(COPPER_HOE, new CopperHoe());
		ITEM_HASH.put(COPPER_PICK, new CopperPick());
		ITEM_HASH.put(COPPER_SHOVEL, new CopperShovel());
		ITEM_HASH.put(COPPER_SWORD, new CopperSword());
		
		ITEM_HASH.put(IRON_AXE, new IronAxe());
		ITEM_HASH.put(IRON_HOE, new IronHoe());
		ITEM_HASH.put(IRON_PICK, new IronPick());
		ITEM_HASH.put(IRON_SHOVEL, new IronShovel());
		ITEM_HASH.put(IRON_SWORD, new IronSword());
		
		ITEM_HASH.put(STEAK, new Steak());
		ITEM_HASH.put(COOKED_STEAK, new CookedSteak());
		ITEM_HASH.put(HONEY, new Honey());
		ITEM_HASH.put(SLIME_BALL, new SlimeBall());
		ITEM_HASH.put(WORM_GUTS, new WormGuts());
		ITEM_HASH.put(STINGER_SWORD, new StingerSword());
		
		ITEM_HASH.put(GLASS_BOTTLE, new GlassBottle());
		
		ITEM_HASH.put(DIRT_MOUNTAIN_CLUMP, new DirtMountainClump());
		ITEM_HASH.put(STONE_MOUNTAIN_CLUMP, new StoneMountainClump());
		ITEM_HASH.put(STONE, new Stone());
		ITEM_HASH.put(BREAD, new Bread());
		ITEM_HASH.put(BEEHIVE, new Beehive());
		
		
		
		TILE_HASH.put(GRASS, new GrassTile());
		TILE_HASH.put(FIRE_PIT_TILE, new FirePitTile());
		TILE_HASH.put(DIRT, new DirtTile());
		TILE_HASH.put(WATER, new WaterTile());
		TILE_HASH.put(TREE, new TreeTile());
		TILE_HASH.put(TREE_PINE, new TreePineTile());
		TILE_HASH.put(TREE_RAIN, new TreeRainTile());
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
		TILE_HASH.put(SPACE, new SpaceTile());
		TILE_HASH.put(DIRT_MOUNTAIN_BOTTOM, new DirtMountainBottomTile());
		TILE_HASH.put(DIRT_MOUNTAIN_MIDDLE, new DirtMountainMiddleTile());
		TILE_HASH.put(DIRT_MOUNTAIN_COPPER_BOTTOM, new DirtMountainCopperBottomTile());
		TILE_HASH.put(DIRT_MOUNTAIN_COPPER_MIDDLE, new DirtMountainCopperMiddleTile());
		TILE_HASH.put(DIRT_MOUNTAIN_TIN_BOTTOM, new DirtMountainTinBottomTile());
		TILE_HASH.put(DIRT_MOUNTAIN_TIN_MIDDLE, new DirtMountainTinMiddleTile());
		TILE_HASH.put(DIRT_MOUNTAIN_TOP, new DirtMountainTopTile());
		TILE_HASH.put(STONE_MOUNTAIN_BOTTOM, new StoneMountainBottomTile());
		TILE_HASH.put(STONE_MOUNTAIN_TOP, new StoneMountainTopTile());
		TILE_HASH.put(STONE_MOUNTAIN_COAL_BOTTOM, new StoneMountainCoalBottomTile());
		TILE_HASH.put(STONE_MOUNTAIN_COAL_TOP, new StoneMountainCoalTopTile());
		TILE_HASH.put(STONE_MOUNTAIN_TIN_BOTTOM, new StoneMountainTinBottomTile());
		TILE_HASH.put(STONE_MOUNTAIN_TIN_TOP, new StoneMountainTinTopTile());
		TILE_HASH.put(STONE_MOUNTAIN_COPPER_BOTTOM, new StoneMountainCopperBottomTile());
		TILE_HASH.put(STONE_MOUNTAIN_COPPER_TOP, new StoneMountainCopperTopTile());
		TILE_HASH.put(DIRT_MOUNTAIN_COAL_BOTTOM, new DirtMountainCoalBottomTile());
		TILE_HASH.put(DIRT_MOUNTAIN_COAL_MIDDLE, new DirtMountainCoalMiddleTile());
		TILE_HASH.put(CAMPFIRE, new CampfireTile());
		TILE_HASH.put(DARK_GRASS, new DarkGrassTile());
		TILE_HASH.put(SNOW, new SnowTile());
		TILE_HASH.put(TABLE_TILE, new TableTile());
		TILE_HASH.put(CHEST_TILE, new ChestTile());
		TILE_HASH.put(FURNACE_TILE, new FurnaceTile());
		TILE_HASH.put(FURNACE_TOP, new FurnaceTopTile());
		TILE_HASH.put(CAULDRON_TILE, new CauldronTile());
		TILE_HASH.put(BARREL, new BarrelTile());
		TILE_HASH.put(BARREL_TOP, new BarrelTopTile());
		TILE_HASH.put(CACTUS, new CactusTile());
		TILE_HASH.put(CACTUS_SAPLING, new CactusSaplingTile());
		TILE_HASH.put(CACTUS_LEAF, new CactusLeafTile());
		TILE_HASH.put(TALL_GRASS_A_TILE, new TallGrassATile());
		TILE_HASH.put(TALL_GRASS_B_TILE, new TallGrassBTile());
		TILE_HASH.put(TALL_GRASS_C_TILE, new TallGrassCTile());
		
		TILE_HASH.put(RED_MUSHROOM_TILE, new RedMushroomTile());
		TILE_HASH.put(BROWN_MUSHROOM_TILE, new BrownMushroomTile());
		TILE_HASH.put(RED_FLOWERS_TILE, new RedFlowersTile());
		TILE_HASH.put(YELLOW_FLOWERS_TILE, new YellowFlowersTile());
		TILE_HASH.put(TORCH_TILE, new TorchTile());
		
		TILE_HASH.put(WHEAT_TILE, new WheatTile());
		TILE_HASH.put(TOMATO_TILE, new TomatoTile());
		TILE_HASH.put(CORN_TILE, new CornTile());
		TILE_HASH.put(ZUCCHINI_TILE, new ZucchiniTile());
		TILE_HASH.put(CARROT_TILE, new CarrotTile());
		TILE_HASH.put(PEPPER_TILE, new PepperTile());
		TILE_HASH.put(ARTICHOKE_TILE, new ArtichokeTile());
		TILE_HASH.put(BEEHIVE_TILE, new BeehiveTile());
		
		TILE_HASH.put(STAIRS_UP_TOP_TILE, new StairsUpTopTile());
		TILE_HASH.put(STAIRS_UP_BOTTOM_TILE, new StairsUpBottomTile());
		TILE_HASH.put(STAIRS_DOWN_TILE, new StairsDownTile());
			
//		if(blocks[x-1][y-1].layers[i].autoTileID == block.layers[i].autoTileID) topLeft = 1;
//		if(blocks[x][y-1].layers[i].autoTileID == block.layers[i].autoTileID) top = 2;
//		if(blocks[x+1][y-1].layers[i].autoTileID == block.layers[i].autoTileID) topRight = 4;
//		if(blocks[x-1][y].layers[i].autoTileID == block.layers[i].autoTileID) left = 8;
//		if(blocks[x+1][y].layers[i].autoTileID == block.layers[i].autoTileID) right = 16;
//		if(blocks[x-1][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottomLeft = 32;
//		if(blocks[x][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottom = 64;
//		if(blocks[x+1][y+1].layers[i].autoTileID == block.layers[i].autoTileID) bottomRight = 128;
		
		
		TOP_LEFT_AUTO_TILE_HASH.put(0, TOP_LEFT_TILE);  //No connections
		TOP_LEFT_AUTO_TILE_HASH.put(1, TOP_LEFT_TILE);  //Corner is true
		TOP_LEFT_AUTO_TILE_HASH.put(2, LEFT_TILE);		//Top is true
		TOP_LEFT_AUTO_TILE_HASH.put(3, LEFT_TILE);		//Top and Corner is true
		TOP_LEFT_AUTO_TILE_HASH.put(8, TOP_TILE);		//Left is true
		TOP_LEFT_AUTO_TILE_HASH.put(9, TOP_TILE);		//Left and Corner is true
		TOP_LEFT_AUTO_TILE_HASH.put(10, MERGE_TILE_BOTTOM_RIGHT);	//Left and Top is true            MERGE_TILE_TOP_LEFT);
		TOP_LEFT_AUTO_TILE_HASH.put(11, MIDDLE_TILE);     //All are true
		
		TOP_RIGHT_AUTO_TILE_HASH.put(0, bTOP_RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(2, bRIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(4, bTOP_RIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(6, bRIGHT_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(16, bTOP_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(18, MERGE_TILE_BOTTOM_LEFT);	//MERGE_TILE_TOP_RIGHT);
		TOP_RIGHT_AUTO_TILE_HASH.put(20, bTOP_TILE);
		TOP_RIGHT_AUTO_TILE_HASH.put(22, bMIDDLE_TILE);

		BOTTOM_LEFT_AUTO_TILE_HASH.put(0, cBOTTOM_LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(8, cBOTTOM_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(32, cBOTTOM_LEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(40, cBOTTOM_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(64, cLEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(72, MERGE_TILE_TOP_RIGHT);	//MERGE_TILE_BOTTOM_LEFT);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(96, cLEFT_TILE);
		BOTTOM_LEFT_AUTO_TILE_HASH.put(104, cMIDDLE_TILE);

		BOTTOM_RIGHT_AUTO_TILE_HASH.put(0, dBOTTOM_RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(16, dBOTTOM_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(64, dRIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(80, MERGE_TILE_TOP_LEFT);	//MERGE_TILE_BOTTOM_RIGHT);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(128, dBOTTOM_RIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(144, dBOTTOM_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(192, dRIGHT_TILE);
		BOTTOM_RIGHT_AUTO_TILE_HASH.put(208, dMIDDLE_TILE);
	}
}