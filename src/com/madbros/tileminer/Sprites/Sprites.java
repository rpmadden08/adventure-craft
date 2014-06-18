package com.madbros.tileminer.Sprites;

//import java.awt.Font;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import org.newdawn.slick.TrueTypeFont;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;




import com.madbros.tileminer.Game;

import static com.madbros.tileminer.Constants.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Sprites {
	public static Texture atlas;
	public static Texture lightAtlas;
	
	public static final String HUMAN_BASE = "1";
	
	//singles
	public static final String DARK_DIRT = "darkDirtSingle";
	public static final String COLLISION_DETECTION = "collisionDetection";
	public static final String INVENTORY_MENU_SELECTOR = "inventoryMenuSelector";
	public static final String INVENTORY_MENU_SLOT = "inventoryMenuSlot";
	public static final String BUTTON = "button";
	public static final String BUTTON_EDGE = "buttonCorner";
	public static final String BUTTON_SELECT = "buttonSelect";
	public static final String BUTTON_SELECT_EDGE = "buttonSelectCorner";
	public static final String HEALTH_BAR_EDGE = "healthBarEdge";
	public static final String MON_HEALTH_EDGE = "monHealthEdge";
	public static final String TILLED_SOIL = "tilledSoil";
	
	public static final String PIXEL = "pixel";
	public static final String SAPLING = "sapling";
	public static final String SEED_POTATO = "potatoPlant0";
	public static final String STUMP = "stump";
	public static final String GRASS_ITEM = "grassItem";
	public static final String SNOW_ITEM = "snowItem";
	public static final String DARK_GRASS_ITEM = "darkGrassItem";
	public static final String DIRT_ITEM = "dirtItem";
	public static final String DIRT_MOUNTAIN_ITEM = "dirtMountainItem";
	public static final String STONE_MOUNTAIN_ITEM = "stoneMountainItem";
	public static final String SAND_ITEM = "sandItem";
	public static final String LOG_ITEM = "logItem";
	public static final String PLANK_ITEM = "plankItem";
	public static final String PLATE_HELMET_ITEM = "plateHelmetItem";
	public static final String PLATE_TORSO_ITEM = "plateTorsoItem";
	public static final String PLATE_LEGS_ITEM = "plateLegsItem";
	public static final String PLATE_FEET_ITEM = "plateFeetItem";
	
	public static final String LEATHER_HELMET_ITEM = "leatherHatItem";
	public static final String LEATHER_TORSO_ITEM = "leatherTorsoItem";
	public static final String LEATHER_LEGS_ITEM = "leatherLeggingsItem";
	public static final String LEATHER_FEET_ITEM = "leatherFeetItem";
	public static final String TIN_HELMET_ITEM = "tinHelmetItem";
	public static final String TIN_TORSO_ITEM = "tinTorsoItem";
	public static final String TIN_LEGS_ITEM = "tinLeggingsItem";
	public static final String TIN_FEET_ITEM = "tinFeetItem";
	public static final String COPPER_HELMET_ITEM = "copperHelmetItem";
	public static final String COPPER_TORSO_ITEM = "copperTorsoItem";
	public static final String COPPER_LEGS_ITEM = "copperLeggingsItem";
	public static final String COPPER_FEET_ITEM = "copperFeetItem";
	
	public static final String BAT = "bat";
	public static final String BEE = "bee";
	public static final String BEE_MINI = "beeMini";
	public static final String SLIME = "slime";
	public static final String WORM = "small_worm";
	public static final String COW = "cow_walk";
	public static final String SHEEP = "sheep_walk";
	public static final String SWORD_ITEM = "swordItem";
	public static final String SWORD = "sword";
	public static final String SHOVEL_ITEM = "shovelItem";
	public static final String AXE_ITEM = "stoneAxe";
	public static final String BAT_WING = "batWing";
	public static final String HOE = "ironHoe";
	public static final String TABLE_ITEM = "table";
	public static final String CHEST_ITEM = "chest";
	public static final String PICK = "ironPick";
	public static final String COAL_ITEM = "coalItem";
	public static final String TIN_ITEM = "tinItem";
	public static final String COPPER_ITEM = "copperItem";
	public static final String CACTUS_ITEM = "cactusSeed";
	public static final String TALL_GRASS_A_ITEM = "tallGrass1";
	public static final String TALL_GRASS_B_ITEM = "tallGrass2";
	public static final String TALL_GRASS_C_ITEM = "tallGrass3";
	public static final String RED_FLOWERS = "flowersItem";
	public static final String YELLOW_FLOWERS = "flowersItemYellow";
	public static final String BROWN_MUSHROOM = "mushroom1";
	public static final String RED_MUSHROOM = "mushroom2";
	public static final String TORCH= "torch0";
	public static final String WHEAT = "wheatItem";
	public static final String WHEAT_SPROUT = "wheat0";
	public static final String CARROT = "carrot5";
	public static final String CARROT_SPROUT = "carrot0";
	public static final String TOMATO= "tomatoItem";
	public static final String TOMATO_SPROUT= "tomato0";
	public static final String ZUCCHINI= "zucchini5";
	public static final String ZUCCHINI_SPROUT= "zucchini0";
	public static final String CORN= "corn9";
	public static final String CORN_SPROUT= "corn0";
	public static final String ARTICHOKE= "artichoke5";
	public static final String ARTICHOKE_SPROUT= "artichoke0";
	public static final String PEPPER= "pepper8";
	public static final String PEPPER_SPROUT= "pepper0";
	public static final String TIN_BAR= "tinBar";
	public static final String COPPER_BAR= "copperBar";
	public static final String IRON_BAR= "ironBarItem";
	public static final String VENOM= "venom";
	public static final String GREEN_SLIME= "slimeItem";
	public static final String BLUE_SLIME= "slimeBlueItem";
	public static final String ECTOPLASM= "ectoplasm";
	public static final String EYEBALL= "eyeballItem";
	public static final String FLOWER_PETAL= "flowerPetalItem";
	public static final String BEETLE_SHELL= "beetleShell";
	public static final String IMP_HORN= "impHorn";
	public static final String LEATHER= "leather";
	public static final String BEEHIVE= "beehive";
	public static final String BEEHIVE_TILE= "beehiveTile";
	
	public static final String STEAK= "rawSteak";
	public static final String COOKED_STEAK= "cookedSteak";
	public static final String HONEY= "honey";
	public static final String SLIME_BALL= "slimeItem";
	public static final String WORM_GUTS= "wormGuts";
	public static final String STINGER_SWORD= "stingerSword";
	
	public static final String HEALTH_POTION= "healthPotion";
	public static final String SPEED_POTION= "speedPotion";
	public static final String SLOWNESS_POTION= "slownessPotion";
	public static final String HARMING_POTION= "harmingPotion";
	public static final String GLASS_BOTTLE= "glassBottle";
	
	public static final String WOODEN_AXE= "woodenAxe";
	public static final String WOODEN_HOE= "woodenHoe";
	public static final String WOODEN_PICK= "woodenPick";
	public static final String WOODEN_SHOVEL= "woodenShovel";
	public static final String WOODEN_SWORD= "woodenSword";
	public static final String COPPER_AXE= "copperAxe";
	public static final String COPPER_HOE= "copperHoe";
	public static final String COPPER_PICK= "copperPick";
	public static final String COPPER_SHOVEL= "copperShovel";
	public static final String COPPER_SWORD= "copperSword";
	public static final String TIN_AXE= "tinAxe";
	public static final String TIN_HOE= "tinHoe";
	public static final String TIN_PICK= "tinPick";
	public static final String TIN_SHOVEL= "tinShovel";
	public static final String TIN_SWORD= "tinSword";
	public static final String STONE_AXE= "stoneAxe";
	public static final String STONE_HOE= "stoneHoe";
	public static final String STONE_PICK= "stonePick";
	public static final String STONE_SHOVEL= "stoneShovel";
	public static final String STONE_SWORD= "stoneSword";
	public static final String IRON_AXE= "ironAxe";
	public static final String IRON_HOE= "ironHoe";
	public static final String IRON_PICK= "ironPick";
	public static final String IRON_SHOVEL= "ironShovel";
	public static final String IRON_SWORD= "ironSword";
	public static final String STAIRS_UP = "stairsUp";
	public static final String STAIRS_DOWN = "stairsDown";
	public static final String DIRT_MOUNTAIN_CLUMP = "dirtMountainClump";
	public static final String STONE_MOUNTAIN_CLUMP = "stoneMountainClump";
	public static final String STONE = "stone";
	public static final String BREAD = "bread";
	
	
	
	
	//static collections
	public static final String DESERT = "desert";
	public static final String GRASS = "grass";
	public static final String GRASS_NEW = "grassNew";
	public static final String DIRT_NEW = "dirtNew";
	public static final String HOLE_NEW = "holeNew";
	public static final String WATER_NEW = "waterNew";
	public static final String SAND_NEW = "sandNew";
	public static final String DIRT_MOUNTAIN_BOTTOM_NEW = "dirtMountainBottomNew";
	public static final String DIRT_MOUNTAIN_MIDDLE_NEW = "dirtMountainMiddleNew";
	public static final String DIRT_MOUNTAIN_TOP_NEW = "dirtMountainTopNew";
	public static final String DIRT_MOUNTAIN_COPPER_BOTTOM_NEW = "dirtMountainCopperBottomNew";
	public static final String DIRT_MOUNTAIN_COPPER_MIDDLE_NEW = "dirtMountainCopperMiddleNew";
	public static final String DIRT_MOUNTAIN_TIN_BOTTOM_NEW = "dirtMountainTinBottomNew";
	public static final String DIRT_MOUNTAIN_TIN_MIDDLE_NEW = "dirtMountainTinMiddleNew";
	public static final String DIRT_MOUNTAIN_COAL_BOTTOM_NEW = "dirtMountainCoalBottomNew";
	public static final String DIRT_MOUNTAIN_COAL_MIDDLE_NEW = "dirtMountainCoalMiddleNew";
	public static final String SPACE = "space";
	public static final String HOLE = "hole";
	public static final String LIGHT_DIRT = "lightDirt";
	public static final String MOUNTAIN_BOTTOM = "mountainBottom";
	public static final String MOUNTAIN_TOP = "mountainTop";
	public static final String MOUNTAIN_COAL_BOTTOM = "mountainCoalBottom";
	public static final String MOUNTAIN_COAL_TOP = "mountainCoalTop";
	public static final String STONE_MOUNTAIN_TOP = "stoneMountainTop";
	public static final String STONE_MOUNTAIN_BOTTOM = "stoneMountainBottom";
	public static final String STONE_MOUNTAIN_COAL_TOP = "stoneMountainCoalTop";
	public static final String STONE_MOUNTAIN_COAL_BOTTOM = "stoneMountainCoalBottom";
	public static final String STONE_MOUNTAIN_TIN_TOP = "stoneMountainTinTop";
	public static final String STONE_MOUNTAIN_TIN_BOTTOM = "stoneMountainTinBottom";
	public static final String STONE_MOUNTAIN_COPPER_TOP = "stoneMountainCopperTop";
	public static final String STONE_MOUNTAIN_COPPER_BOTTOM = "stoneMountainCopperBottom";
	public static final String TREE_TWO = "treeTwo";
	public static final String TREE_THREE = "greentree";
	public static final String WATER1 = "water";
	public static final String WATER2 = "waterTwo";
	public static final String INVENTORY_MENU = "inventoryMenu";
	public static final String CAMPFIRE_SINGLE = "campfire0";
	public static final String FURNACE_SINGLE = "furnace0";
	public static final String CAULDRON = "cauldron";
	public static final String CAULDRON_SINGLE = "cauldronStatic";
	public static final String FURNACE_TOP = "furnaceTopTile";
	
	public static final String SAPLING_COLLECTION = "saplingCollection";
	public static final String SNOW = "snow";
	public static final String TREE_FOUR = "pineTree";
	public static final String DARK_GRASS = "darkGrass";
	public static final String POTATO_PLANT = "potatoPlant";
	public static final String POTATOES = "potatoes";
	public static final String BAKED_POTATOES = "bakedPotatoes";
	public static final String TABLE = "tableItem";
	public static final String CHEST = "chestItem";
	public static final String BARREL = "barrel";
	public static final String BARREL_TOP = "barrelTop";
	public static final String TALL_GRASS_A = "tallGrassA";
	public static final String TALL_GRASS_B = "tallGrassB";
	public static final String TALL_GRASS_C = "tallGrassC";
	public static final String RED_FLOWERS_TILE = "redFlowers";
	public static final String YELLOW_FLOWERS_TILE = "yellowFlowers";
	public static final String BROWN_MUSHROOM_TILE = "brownMushroom";
	public static final String RED_MUSHROOM_TILE = "redMushroom";
	public static final String TORCH_TILE = "torch";
	public static final String WHEAT_TILE = "wheat";
	public static final String TOMATO_TILE = "tomato";
	public static final String CARROT_TILE = "carrot";
	public static final String ZUCCHINI_TILE = "zucchini";
	public static final String CORN_TILE = "corn";
	public static final String ARTICHOKE_TILE = "artichoke";
	public static final String PEPPER_TILE = "pepper";
	
	public static final String STAIRS_UP_TILE = "stairsUpTile";
	public static final String STAIRS_DOWN_TILE = "stairsDownTile";
	public static final String FIRE_PIT_TILE = "firePitTile";
	
	
	
	public static final String ROCK = "rockA";
	public static final String FLOWERS = "flowersA";
	
	public static final String STICK = "stick";
	public static final String FIRE_STARTER = "fireStarter";
	public static final String FIRE_PIT = "firePit";
	public static final String CACTUS_SAPLING = "cactusSapling";
	public static final String CACTUS = "cactus";
	
	//Status effects
	public static final String SPEED_STATUS = "speedStatus";
	public static final String SLOWNESS_APPLIED = "slownessApplied";
	public static final String HARMING_APPLIED = "harmingApplied";
	public static final String SLOWNESS_STATUS = "slowStatus";
	
	//animated collections
	public static final String CAMPFIRE_ANIMATION = "campfire";
	public static final String TORCH_ANIMATION = "torch";
	public static final String FURNACE_ANIMATION = "furnaceA";
	public static final String CAULDRON_ANIMATION = "boilingCauldronA";
//	public static final String FURNACE_STATIC = "furnaceStatic";
	public static final String MALE_WALK_DOWN = "maleWalkDown";
	public static final String MALE_WALK_LEFT = "maleWalkLeft";
	public static final String MALE_WALK_RIGHT = "maleWalkRight";
	public static final String MALE_WALK_UP = "maleWalkUp";
	public static final String MALE_STAND_DOWN = "maleStandDown";
	public static final String MALE_STAND_LEFT = "maleStandLeft";
	public static final String MALE_STAND_RIGHT = "maleStandRight";
	public static final String MALE_STAND_UP = "maleStandUp";
	
	public static final String MALE_SLASH_UP = "maleSlashUp";
	public static final String MALE_SLASH_DOWN = "maleSlashDown";
	public static final String MALE_SLASH_LEFT = "maleSlashLeft";
	public static final String MALE_SLASH_RIGHT = "maleSlashRight";
	
	
	public static final String PLATE_FEET_WALK_DOWN = "plateFeetWalkDown";
	public static final String PLATE_FEET_WALK_LEFT = "plateFeetWalkLeft";
	public static final String PLATE_FEET_WALK_RIGHT = "plateFeetWalkRight";
	public static final String PLATE_FEET_WALK_UP = "plateFeetWalkUp";
	public static final String PLATE_FEET_STAND_DOWN = "plateFeetStandDown";
	public static final String PLATE_FEET_STAND_LEFT = "plateFeetStandLeft";
	public static final String PLATE_FEET_STAND_RIGHT = "plateFeetStandRight";
	public static final String PLATE_FEET_STAND_UP = "plateFeetStandUp";
	public static final String PLATE_FEET_SLASH_DOWN = "plateFeetSlashDown";
	public static final String PLATE_FEET_SLASH_LEFT = "plateFeetSlashLeft";
	public static final String PLATE_FEET_SLASH_RIGHT = "plateFeetSlashRight";
	public static final String PLATE_FEET_SLASH_UP = "plateFeetSlashUp";
	
	public static final String LEATHER_FEET_WALK_DOWN = "leatherFeetWalkDown";
	public static final String LEATHER_FEET_WALK_LEFT = "leatherFeetWalkLeft";
	public static final String LEATHER_FEET_WALK_RIGHT = "leatherFeetWalkRight";
	public static final String LEATHER_FEET_WALK_UP = "leatherFeetWalkUp";
	public static final String LEATHER_FEET_STAND_DOWN = "leatherFeetStandDown";
	public static final String LEATHER_FEET_STAND_LEFT = "leatherFeetStandLeft";
	public static final String LEATHER_FEET_STAND_RIGHT = "leatherFeetStandRight";
	public static final String LEATHER_FEET_STAND_UP = "leatherFeetStandUp";
	public static final String LEATHER_FEET_SLASH_DOWN = "leatherFeetSlashDown";
	public static final String LEATHER_FEET_SLASH_LEFT = "leatherFeetSlashLeft";
	public static final String LEATHER_FEET_SLASH_RIGHT = "leatherFeetSlashRight";
	public static final String LEATHER_FEET_SLASH_UP = "leatherFeetSlashUp";
	
	public static final String TIN_FEET_WALK_DOWN = "tinFeetWalkDown";
	public static final String TIN_FEET_WALK_LEFT = "tinFeetWalkLeft";
	public static final String TIN_FEET_WALK_RIGHT = "tinFeetWalkRight";
	public static final String TIN_FEET_WALK_UP = "tinFeetWalkUp";
	public static final String TIN_FEET_STAND_DOWN = "tinFeetStandDown";
	public static final String TIN_FEET_STAND_LEFT = "tinFeetStandLeft";
	public static final String TIN_FEET_STAND_RIGHT = "tinFeetStandRight";
	public static final String TIN_FEET_STAND_UP = "tinFeetStandUp";
	public static final String TIN_FEET_SLASH_DOWN = "tinFeetSlashDown";
	public static final String TIN_FEET_SLASH_LEFT = "tinFeetSlashLeft";
	public static final String TIN_FEET_SLASH_RIGHT = "tinFeetSlashRight";
	public static final String TIN_FEET_SLASH_UP = "tinFeetSlashUp";
	
	public static final String COPPER_FEET_WALK_DOWN = "copperFeetWalkDown";
	public static final String COPPER_FEET_WALK_LEFT = "copperFeetWalkLeft";
	public static final String COPPER_FEET_WALK_RIGHT = "copperFeetWalkRight";
	public static final String COPPER_FEET_WALK_UP = "copperFeetWalkUp";
	public static final String COPPER_FEET_STAND_DOWN = "copperFeetStandDown";
	public static final String COPPER_FEET_STAND_LEFT = "copperFeetStandLeft";
	public static final String COPPER_FEET_STAND_RIGHT = "copperFeetStandRight";
	public static final String COPPER_FEET_STAND_UP = "copperFeetStandUp";
	public static final String COPPER_FEET_SLASH_DOWN = "copperFeetSlashDown";
	public static final String COPPER_FEET_SLASH_LEFT = "copperFeetSlashLeft";
	public static final String COPPER_FEET_SLASH_RIGHT = "copperFeetSlashRight";
	public static final String COPPER_FEET_SLASH_UP = "copperFeetSlashUp";
	
	public static final String PLATE_LEGS_WALK_DOWN = "plateLegsWalkDown";
	public static final String PLATE_LEGS_WALK_LEFT = "plateLegsWalkLeft";
	public static final String PLATE_LEGS_WALK_RIGHT = "plateLegsWalkRight";
	public static final String PLATE_LEGS_WALK_UP = "plateLegsWalkUp";
	public static final String PLATE_LEGS_STAND_DOWN = "plateLegsStandDown";
	public static final String PLATE_LEGS_STAND_LEFT = "plateLegsStandLeft";
	public static final String PLATE_LEGS_STAND_RIGHT = "plateLegsStandRight";
	public static final String PLATE_LEGS_STAND_UP = "plateLegsStandUp";
	public static final String PLATE_LEGS_SLASH_DOWN = "plateLegsSlashDown";
	public static final String PLATE_LEGS_SLASH_LEFT = "plateLegsSlashLeft";
	public static final String PLATE_LEGS_SLASH_RIGHT = "plateLegsSlashRight";
	public static final String PLATE_LEGS_SLASH_UP = "plateLegsSlashUp";
	
	public static final String LEATHER_LEGS_WALK_DOWN = "leatherLeggingsWalkDown";
	public static final String LEATHER_LEGS_WALK_LEFT = "leatherLeggingsWalkLeft";
	public static final String LEATHER_LEGS_WALK_RIGHT = "leatherLeggingsWalkRight";
	public static final String LEATHER_LEGS_WALK_UP = "leatherLeggingsWalkUp";
	public static final String LEATHER_LEGS_STAND_DOWN = "leatherLeggingsStandDown";
	public static final String LEATHER_LEGS_STAND_LEFT = "leatherLeggingsStandLeft";
	public static final String LEATHER_LEGS_STAND_RIGHT = "leatherLeggingsStandRight";
	public static final String LEATHER_LEGS_STAND_UP = "leatherLeggingsStandUp";
	public static final String LEATHER_LEGS_SLASH_DOWN = "leatherLeggingsSlashDown";
	public static final String LEATHER_LEGS_SLASH_LEFT = "leatherLeggingsSlashLeft";
	public static final String LEATHER_LEGS_SLASH_RIGHT = "leatherLeggingsSlashRight";
	public static final String LEATHER_LEGS_SLASH_UP = "leatherLeggingsSlashUp";
	
	public static final String TIN_LEGS_WALK_DOWN = "tinLeggingsWalkDown";
	public static final String TIN_LEGS_WALK_LEFT = "tinLeggingsWalkLeft";
	public static final String TIN_LEGS_WALK_RIGHT = "tinLeggingsWalkRight";
	public static final String TIN_LEGS_WALK_UP = "tinLeggingsWalkUp";
	public static final String TIN_LEGS_STAND_DOWN = "tinLeggingsStandDown";
	public static final String TIN_LEGS_STAND_LEFT = "tinLeggingsStandLeft";
	public static final String TIN_LEGS_STAND_RIGHT = "tinLeggingsStandRight";
	public static final String TIN_LEGS_STAND_UP = "tinLeggingsStandUp";
	public static final String TIN_LEGS_SLASH_DOWN = "tinLeggingsSlashDown";
	public static final String TIN_LEGS_SLASH_LEFT = "tinLeggingsSlashLeft";
	public static final String TIN_LEGS_SLASH_RIGHT = "tinLeggingsSlashRight";
	public static final String TIN_LEGS_SLASH_UP = "tinLeggingsSlashUp";
	
	public static final String COPPER_LEGS_WALK_DOWN = "copperLeggingsWalkDown";
	public static final String COPPER_LEGS_WALK_LEFT = "copperLeggingsWalkLeft";
	public static final String COPPER_LEGS_WALK_RIGHT = "copperLeggingsWalkRight";
	public static final String COPPER_LEGS_WALK_UP = "copperLeggingsWalkUp";
	public static final String COPPER_LEGS_STAND_DOWN = "copperLeggingsStandDown";
	public static final String COPPER_LEGS_STAND_LEFT = "copperLeggingsStandLeft";
	public static final String COPPER_LEGS_STAND_RIGHT = "copperLeggingsStandRight";
	public static final String COPPER_LEGS_STAND_UP = "copperLeggingsStandUp";
	public static final String COPPER_LEGS_SLASH_DOWN = "copperLeggingsSlashDown";
	public static final String COPPER_LEGS_SLASH_LEFT = "copperLeggingsSlashLeft";
	public static final String COPPER_LEGS_SLASH_RIGHT = "copperLeggingsSlashRight";
	public static final String COPPER_LEGS_SLASH_UP = "copperLeggingsSlashUp";
	
	public static final String PLATE_HELMET_WALK_DOWN = "plateHelmetWalkDown";
	public static final String PLATE_HELMET_WALK_LEFT = "plateHelmetWalkLeft";
	public static final String PLATE_HELMET_WALK_RIGHT = "plateHelmetWalkRight";
	public static final String PLATE_HELMET_WALK_UP = "plateHelmetWalkUp";
	public static final String PLATE_HELMET_STAND_DOWN = "plateHelmetStandDown";
	public static final String PLATE_HELMET_STAND_LEFT = "plateHelmetStandLeft";
	public static final String PLATE_HELMET_STAND_RIGHT = "plateHelmetStandRight";
	public static final String PLATE_HELMET_STAND_UP = "plateHelmetStandUp";
	public static final String PLATE_HELMET_SLASH_DOWN = "plateHelmetSlashDown";
	public static final String PLATE_HELMET_SLASH_LEFT = "plateHelmetSlashLeft";
	public static final String PLATE_HELMET_SLASH_RIGHT = "plateHelmetSlashRight";
	public static final String PLATE_HELMET_SLASH_UP = "plateHelmetSlashUp";
	
	public static final String LEATHER_HELMET_WALK_DOWN = "leatherHatWalkDown";
	public static final String LEATHER_HELMET_WALK_LEFT = "leatherHatWalkLeft";
	public static final String LEATHER_HELMET_WALK_RIGHT = "leatherHatWalkRight";
	public static final String LEATHER_HELMET_WALK_UP = "leatherHatWalkUp";
	public static final String LEATHER_HELMET_STAND_DOWN = "leatherHatStandDown";
	public static final String LEATHER_HELMET_STAND_LEFT = "leatherHatStandLeft";
	public static final String LEATHER_HELMET_STAND_RIGHT = "leatherHatStandRight";
	public static final String LEATHER_HELMET_STAND_UP = "leatherHatStandUp";
	public static final String LEATHER_HELMET_SLASH_DOWN = "leatherHatSlashDown";
	public static final String LEATHER_HELMET_SLASH_LEFT = "leatherHatSlashLeft";
	public static final String LEATHER_HELMET_SLASH_RIGHT = "leatherHatSlashRight";
	public static final String LEATHER_HELMET_SLASH_UP = "leatherHatSlashUp";
	
	public static final String TIN_HELMET_WALK_DOWN = "tinHelmetWalkDown";
	public static final String TIN_HELMET_WALK_LEFT = "tinHelmetWalkLeft";
	public static final String TIN_HELMET_WALK_RIGHT = "tinHelmetWalkRight";
	public static final String TIN_HELMET_WALK_UP = "tinHelmetWalkUp";
	public static final String TIN_HELMET_STAND_DOWN = "tinHelmetStandDown";
	public static final String TIN_HELMET_STAND_LEFT = "tinHelmetStandLeft";
	public static final String TIN_HELMET_STAND_RIGHT = "tinHelmetStandRight";
	public static final String TIN_HELMET_STAND_UP = "tinHelmetStandUp";
	public static final String TIN_HELMET_SLASH_DOWN = "tinHelmetSlashDown";
	public static final String TIN_HELMET_SLASH_LEFT = "tinHelmetSlashLeft";
	public static final String TIN_HELMET_SLASH_RIGHT = "tinHelmetSlashRight";
	public static final String TIN_HELMET_SLASH_UP = "tinHelmetSlashUp";
	
	public static final String COPPER_HELMET_WALK_DOWN = "copperHelmetWalkDown";
	public static final String COPPER_HELMET_WALK_LEFT = "copperHelmetWalkLeft";
	public static final String COPPER_HELMET_WALK_RIGHT = "copperHelmetWalkRight";
	public static final String COPPER_HELMET_WALK_UP = "copperHelmetWalkUp";
	public static final String COPPER_HELMET_STAND_DOWN = "copperHelmetStandDown";
	public static final String COPPER_HELMET_STAND_LEFT = "copperHelmetStandLeft";
	public static final String COPPER_HELMET_STAND_RIGHT = "copperHelmetStandRight";
	public static final String COPPER_HELMET_STAND_UP = "copperHelmetStandUp";
	public static final String COPPER_HELMET_SLASH_DOWN = "copperHelmetSlashDown";
	public static final String COPPER_HELMET_SLASH_LEFT = "copperHelmetSlashLeft";
	public static final String COPPER_HELMET_SLASH_RIGHT = "copperHelmetSlashRight";
	public static final String COPPER_HELMET_SLASH_UP = "copperHelmetSlashUp";
	
	public static final String PLATE_TORSO_WALK_DOWN = "plateTorsoWalkDown";
	public static final String PLATE_TORSO_WALK_LEFT = "plateTorsoWalkLeft";
	public static final String PLATE_TORSO_WALK_RIGHT = "plateTorsoWalkRight";
	public static final String PLATE_TORSO_WALK_UP = "plateTorsoWalkUp";
	public static final String PLATE_TORSO_STAND_DOWN = "plateTorsoStandDown";
	public static final String PLATE_TORSO_STAND_LEFT = "plateTorsoStandLeft";
	public static final String PLATE_TORSO_STAND_RIGHT = "plateTorsoStandRight";
	public static final String PLATE_TORSO_STAND_UP = "plateTorsoStandUp";
	public static final String PLATE_TORSO_SLASH_DOWN = "plateTorsoSlashDown";
	public static final String PLATE_TORSO_SLASH_LEFT = "plateTorsoSlashLeft";
	public static final String PLATE_TORSO_SLASH_RIGHT = "plateTorsoSlashRight";
	public static final String PLATE_TORSO_SLASH_UP = "plateTorsoSlashUp";
	
	public static final String LEATHER_TORSO_WALK_DOWN = "leatherTorsoWalkDown";
	public static final String LEATHER_TORSO_WALK_LEFT = "leatherTorsoWalkLeft";
	public static final String LEATHER_TORSO_WALK_RIGHT = "leatherTorsoWalkRight";
	public static final String LEATHER_TORSO_WALK_UP = "leatherTorsoWalkUp";
	public static final String LEATHER_TORSO_STAND_DOWN = "leatherTorsoStandDown";
	public static final String LEATHER_TORSO_STAND_LEFT = "leatherTorsoStandLeft";
	public static final String LEATHER_TORSO_STAND_RIGHT = "leatherTorsoStandRight";
	public static final String LEATHER_TORSO_STAND_UP = "leatherTorsoStandUp";
	public static final String LEATHER_TORSO_SLASH_DOWN = "leatherTorsoSlashDown";
	public static final String LEATHER_TORSO_SLASH_LEFT = "leatherTorsoSlashLeft";
	public static final String LEATHER_TORSO_SLASH_RIGHT = "leatherTorsoSlashRight";
	public static final String LEATHER_TORSO_SLASH_UP = "leatherTorsoSlashUp";
	
	public static final String TIN_TORSO_WALK_DOWN = "tinTorsoWalkDown";
	public static final String TIN_TORSO_WALK_LEFT = "tinTorsoWalkLeft";
	public static final String TIN_TORSO_WALK_RIGHT = "tinTorsoWalkRight";
	public static final String TIN_TORSO_WALK_UP = "tinTorsoWalkUp";
	public static final String TIN_TORSO_STAND_DOWN = "tinTorsoStandDown";
	public static final String TIN_TORSO_STAND_LEFT = "tinTorsoStandLeft";
	public static final String TIN_TORSO_STAND_RIGHT = "tinTorsoStandRight";
	public static final String TIN_TORSO_STAND_UP = "tinTorsoStandUp";
	public static final String TIN_TORSO_SLASH_DOWN = "tinTorsoSlashDown";
	public static final String TIN_TORSO_SLASH_LEFT = "tinTorsoSlashLeft";
	public static final String TIN_TORSO_SLASH_RIGHT = "tinTorsoSlashRight";
	public static final String TIN_TORSO_SLASH_UP = "tinTorsoSlashUp";
	
	public static final String COPPER_TORSO_WALK_DOWN = "copperTorsoWalkDown";
	public static final String COPPER_TORSO_WALK_LEFT = "copperTorsoWalkLeft";
	public static final String COPPER_TORSO_WALK_RIGHT = "copperTorsoWalkRight";
	public static final String COPPER_TORSO_WALK_UP = "copperTorsoWalkUp";
	public static final String COPPER_TORSO_STAND_DOWN = "copperTorsoStandDown";
	public static final String COPPER_TORSO_STAND_LEFT = "copperTorsoStandLeft";
	public static final String COPPER_TORSO_STAND_RIGHT = "copperTorsoStandRight";
	public static final String COPPER_TORSO_STAND_UP = "copperTorsoStandUp";
	public static final String COPPER_TORSO_SLASH_DOWN = "copperTorsoSlashDown";
	public static final String COPPER_TORSO_SLASH_LEFT = "copperTorsoSlashLeft";
	public static final String COPPER_TORSO_SLASH_RIGHT = "copperTorsoSlashRight";
	public static final String COPPER_TORSO_SLASH_UP = "copperTorsoSlashUp";
	
	public static final String SKELETON_WALK_DOWN = "skeletonWalkDown";
	public static final String SKELETON_WALK_RIGHT = "skeletonWalkRight";
	public static final String SKELETON_WALK_LEFT = "skeletonWalkLeft";
	public static final String SKELETON_WALK_UP = "skeletonWalkUp";
	public static final String SKELETON_STAND_DOWN = "skeletonStandDown";
	public static final String SKELETON_STAND_RIGHT = "skeletonStandRight";
	public static final String SKELETON_STAND_LEFT = "skeletonStandLeft";
	public static final String SKELETON_STAND_UP = "skeletonStandUp";

	public static final String BAT_WALK_DOWN = "batWalkDown";
	public static final String BAT_WALK_RIGHT = "batWalkRight";
	public static final String BAT_WALK_LEFT = "batWalkLeft";
	public static final String BAT_WALK_UP = "batWalkUp";
	public static final String BAT_STAND_DOWN = "batStandDown";
	public static final String BAT_STAND_RIGHT = "batStandRight";
	public static final String BAT_STAND_LEFT = "batStandLeft";
	public static final String BAT_STAND_UP = "batStandUp";
	
	public static final String COW_WALK_DOWN = "cow_walkDown";
	public static final String COW_WALK_RIGHT = "cow_walkRight";
	public static final String COW_WALK_LEFT = "cow_walkLeft";
	public static final String COW_WALK_UP = "cow_walkUp";
	public static final String COW_STAND_DOWN = "cow_eatStandDown";
	public static final String COW_STAND_RIGHT = "cow_eatStandRight";
	public static final String COW_STAND_LEFT = "cow_eatStandLeft";
	public static final String COW_STAND_UP = "cow_eatStandUp";
	
	public static final String WORM_WALK_DOWN = "small_wormWalkDown";
	public static final String WORM_WALK_RIGHT = "small_wormWalkRight";
	public static final String WORM_WALK_LEFT = "small_wormWalkLeft";
	public static final String WORM_WALK_UP = "small_wormWalkUp";
	public static final String WORM_STAND_DOWN = "small_wormStandDown";
	public static final String WORM_STAND_RIGHT = "small_wormStandRight";
	public static final String WORM_STAND_LEFT = "small_wormStandLeft";
	public static final String WORM_STAND_UP = "small_wormStandUp";
	
	public static final String SLIME_WALK_DOWN = "slimeWalkDown";
	public static final String SLIME_WALK_RIGHT = "slimeWalkRight";
	public static final String SLIME_WALK_LEFT = "slimeWalkLeft";
	public static final String SLIME_WALK_UP = "slimeWalkUp";
	public static final String SLIME_STAND_DOWN = "slimeStandDown";
	public static final String SLIME_STAND_RIGHT = "slimeStandRight";
	public static final String SLIME_STAND_LEFT = "slimeStandLeft";
	public static final String SLIME_STAND_UP = "slimeStandUp";
	
	public static final String BEE_WALK_DOWN = "beeWalkDown";
	public static final String BEE_WALK_RIGHT = "beeWalkRight";
	public static final String BEE_WALK_LEFT = "beeWalkLeft";
	public static final String BEE_WALK_UP = "beeWalkUp";
	public static final String BEE_STAND_DOWN = "beeStandDown";
	public static final String BEE_STAND_RIGHT = "beeStandRight";
	public static final String BEE_STAND_LEFT = "beeStandLeft";
	public static final String BEE_STAND_UP = "beeStandUp";
	
	public static final String BEE_MINI_WALK_DOWN = "beeMiniWalkDown";
	public static final String BEE_MINI_WALK_RIGHT = "beeMiniWalkRight";
	public static final String BEE_MINI_WALK_LEFT = "beeMiniWalkLeft";
	public static final String BEE_MINI_WALK_UP = "beeMiniWalkUp";
	public static final String BEE_MINI_STAND_DOWN = "beeMiniStandDown";
	public static final String BEE_MINI_STAND_RIGHT = "beeMiniStandRight";
	public static final String BEE_MINI_STAND_LEFT = "beeMiniStandLeft";
	public static final String BEE_MINI_STAND_UP = "beeMiniStandUp";
	
	public static final String SHEEP_WALK_DOWN = "sheep_walkDown";
	public static final String SHEEP_WALK_RIGHT = "sheep_walkRight";
	public static final String SHEEP_WALK_LEFT = "sheep_walkLeft";
	public static final String SHEEP_WALK_UP = "sheep_walkUp";
	public static final String SHEEP_STAND_DOWN = "sheep_eatDown";
	public static final String SHEEP_STAND_RIGHT = "sheep_eatRight";
	public static final String SHEEP_STAND_LEFT = "sheep_eatLeft";
	public static final String SHEEP_STAND_UP = "sheep_eatUp";
	
	
	public static StaticSprite[] fireAnimationSprites;
	
	public static StaticSprite buttonSprite;
	public static StaticSprite pressedButtonSprite;
	
	public static AnimatedSprite[] waterSprites;
	public static StaticSprite[] saplingSprite;
	public static StaticSprite[] treeSprites;
	public static StaticSprite[] treeLeafSprites;
	public static AnimatedSprite[] campfireAnimation;
	public static AnimatedSprite[] torchAnimation;
	public static AnimatedSprite[] furnaceAnimation;
	public static StaticSprite[] furnaceStatic;
	public static AnimatedSprite[] cauldronAnimation;
	public static StaticSprite[] cauldronStatic;
	
	public static final String LIGHT = "light";
	public static final String CAMPFIRE_LIGHT = "CampfireLight";
	
	//for debugging
	public static StaticSprite collisionDebugger;
	public static StaticSprite pixel;
	public static StaticSprite healthBar;
	public static StaticSprite healthBarMon;
	
	public static BitmapFont font;
	//public static BitmapFont font2;
	public static BitmapFont arial24;
	public static BitmapFont arial10;
	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"«`'<>";
	public static HashMap<String, StaticSprite> sprites = new HashMap<String, StaticSprite>();
	public static HashMap<String, StaticSprite[]> spriteCollections = new HashMap<String, StaticSprite[]>();
	public static HashMap<String, Animation> animations = new HashMap<String, Animation>();
	
	public static HashMap<String, AnimatedSprite> animatedSprites = new HashMap<String, AnimatedSprite>();
	public static HashMap<String, AnimatedSprite[]> animatedSpriteCollections = new HashMap<String, AnimatedSprite[]>();
//	public static HashMap<Integer, CompoundAnimatedSprite> compoundAnimatedSprite = new HashMap<Integer, CompoundAnimatedSprite>();
	
	//Sprite hash (sprites)
	//ArrayList<Sprite> hash (sprite collections)
	
	public int getInteger(Object o) {
		return Integer.valueOf((String) o); 
	}
	
	public Sprites() {
			atlas = new Texture("res/atlas.png"); //TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"), GL11.GL_NEAREST);
			lightAtlas = new Texture("res/lightAtlas.png");
			//atlas2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas2.png"), GL11.GL_NEAREST);
			Texture[] atlasArray = {atlas,lightAtlas};
			String[] atlasStringArray = {"atlas.json", "lightAtlas.json"};
			
			String[] names = {DARK_DIRT, INVENTORY_MENU, INVENTORY_MENU_SELECTOR, 
					INVENTORY_MENU_SLOT, PIXEL, SHOVEL_ITEM, LOG_ITEM, PLANK_ITEM,
					GRASS, LIGHT_DIRT, MOUNTAIN_BOTTOM, MOUNTAIN_TOP, WATER1, WATER2, HOLE,
					MALE_WALK_DOWN, MALE_WALK_LEFT, MALE_WALK_RIGHT, MALE_WALK_UP,
					MALE_STAND_DOWN, MALE_STAND_LEFT, MALE_STAND_RIGHT, MALE_STAND_UP,
					PLATE_FEET_ITEM, PLATE_HELMET_ITEM, PLATE_TORSO_ITEM, PLATE_LEGS_ITEM,
					GRASS_ITEM, DIRT_ITEM, SAND_ITEM, SWORD_ITEM, SAPLING,
					PLATE_HELMET_WALK_DOWN, PLATE_HELMET_WALK_LEFT, PLATE_HELMET_WALK_RIGHT,
					PLATE_HELMET_WALK_UP, PLATE_HELMET_STAND_DOWN, PLATE_HELMET_STAND_LEFT,
					PLATE_HELMET_STAND_RIGHT, PLATE_HELMET_STAND_UP, PLATE_FEET_WALK_DOWN,
					PLATE_FEET_WALK_LEFT, PLATE_FEET_WALK_RIGHT, PLATE_FEET_WALK_UP,
					PLATE_FEET_STAND_DOWN, PLATE_FEET_STAND_LEFT, PLATE_FEET_STAND_RIGHT,
					PLATE_FEET_STAND_UP, PLATE_LEGS_WALK_DOWN, PLATE_LEGS_WALK_LEFT,
					PLATE_LEGS_WALK_RIGHT, PLATE_LEGS_WALK_UP, PLATE_LEGS_STAND_DOWN,
					PLATE_LEGS_STAND_LEFT, PLATE_LEGS_STAND_RIGHT, PLATE_LEGS_STAND_UP,
					PLATE_TORSO_WALK_DOWN, PLATE_TORSO_WALK_LEFT, PLATE_TORSO_WALK_RIGHT,
					PLATE_TORSO_WALK_UP, PLATE_TORSO_STAND_DOWN, PLATE_TORSO_STAND_LEFT,
					PLATE_TORSO_STAND_RIGHT, PLATE_TORSO_STAND_UP, TREE_TWO, TREE_THREE, STUMP,
					CAMPFIRE_ANIMATION, CAMPFIRE_SINGLE, SAPLING_COLLECTION, DESERT, SNOW, TREE_FOUR, 
					DARK_GRASS, AXE_ITEM, BAT_WALK_DOWN, BAT_WALK_UP, BAT_WALK_LEFT, BAT_WALK_RIGHT,
					BAT_STAND_UP, BAT_STAND_DOWN, BAT_STAND_LEFT, BAT_STAND_RIGHT,MALE_SLASH_UP,
					MALE_SLASH_DOWN, MALE_SLASH_LEFT, MALE_SLASH_RIGHT, SWORD, HEALTH_BAR_EDGE,
					MON_HEALTH_EDGE, BAT_WING, HOE, SEED_POTATO, POTATO_PLANT, POTATOES, TILLED_SOIL,
					TABLE, TABLE_ITEM, CHEST, CHEST_ITEM, FURNACE_ANIMATION, FURNACE_SINGLE,
					BAKED_POTATOES, FURNACE_TOP, CAULDRON_ANIMATION, CAULDRON, CAULDRON_SINGLE,
					PLATE_TORSO_SLASH_UP, PLATE_TORSO_SLASH_DOWN, PLATE_TORSO_SLASH_LEFT, 
					PLATE_TORSO_SLASH_RIGHT,PLATE_HELMET_SLASH_UP, PLATE_HELMET_SLASH_DOWN, PLATE_HELMET_SLASH_LEFT, 
					PLATE_HELMET_SLASH_RIGHT, PLATE_LEGS_SLASH_UP, PLATE_LEGS_SLASH_DOWN, PLATE_LEGS_SLASH_LEFT, 
					PLATE_LEGS_SLASH_RIGHT, PLATE_FEET_SLASH_UP, PLATE_FEET_SLASH_DOWN, PLATE_FEET_SLASH_LEFT, 
					PLATE_FEET_SLASH_RIGHT, PICK, MOUNTAIN_COAL_TOP, MOUNTAIN_COAL_BOTTOM, SPACE, SNOW_ITEM,
					DARK_GRASS_ITEM, DIRT_MOUNTAIN_ITEM, BARREL, BARREL_TOP, TALL_GRASS_A, TALL_GRASS_B, TALL_GRASS_C,
					ROCK, FLOWERS, WHEAT_SPROUT, WHEAT, FIRE_STARTER, FIRE_PIT, STICK,
					BEE_WALK_DOWN, BEE_WALK_UP, BEE_WALK_RIGHT, BEE_WALK_LEFT,
					BEE_STAND_DOWN, BEE_STAND_UP, BEE_STAND_RIGHT, BEE_STAND_LEFT,
					BEE_MINI_WALK_DOWN, BEE_MINI_WALK_UP, BEE_MINI_WALK_RIGHT, BEE_MINI_WALK_LEFT,
					BEE_MINI_STAND_DOWN, BEE_MINI_STAND_UP, BEE_MINI_STAND_RIGHT, BEE_MINI_STAND_LEFT,
					SHEEP_WALK_DOWN, SHEEP_WALK_UP, SHEEP_WALK_RIGHT, SHEEP_WALK_LEFT,
					SHEEP_STAND_DOWN, SHEEP_STAND_UP, SHEEP_STAND_RIGHT, SHEEP_STAND_LEFT,
					COAL_ITEM, STONE_MOUNTAIN_TOP, STONE_MOUNTAIN_BOTTOM, STONE_MOUNTAIN_ITEM,
					STONE_MOUNTAIN_COAL_TOP, STONE_MOUNTAIN_COAL_BOTTOM, STONE_MOUNTAIN_TIN_TOP, STONE_MOUNTAIN_TIN_BOTTOM,
					STONE_MOUNTAIN_COPPER_TOP, STONE_MOUNTAIN_COPPER_BOTTOM, TIN_ITEM, COPPER_ITEM, CACTUS, CACTUS_SAPLING,
					CACTUS_ITEM,TALL_GRASS_A_ITEM, TALL_GRASS_B_ITEM, TALL_GRASS_C_ITEM, RED_FLOWERS_TILE, YELLOW_FLOWERS_TILE,
					RED_MUSHROOM_TILE, BROWN_MUSHROOM_TILE, RED_FLOWERS, YELLOW_FLOWERS, RED_MUSHROOM, BROWN_MUSHROOM,
					TORCH, TORCH_TILE, WHEAT_TILE, TOMATO, TOMATO_SPROUT, TOMATO_TILE, CARROT, CARROT_SPROUT, CARROT_TILE,
					ZUCCHINI, ZUCCHINI_SPROUT, ZUCCHINI_TILE, CORN, CORN_SPROUT, CORN_TILE, ARTICHOKE, ARTICHOKE_SPROUT,
					ARTICHOKE_TILE, PEPPER, PEPPER_SPROUT, PEPPER_TILE, TIN_BAR, COPPER_BAR, IRON_BAR, VENOM, GREEN_SLIME,
					BLUE_SLIME, ECTOPLASM, EYEBALL, FLOWER_PETAL, BEETLE_SHELL, IMP_HORN, WOODEN_SWORD, WOODEN_SHOVEL, WOODEN_PICK,
					WOODEN_HOE, WOODEN_AXE, STONE_AXE, STONE_HOE, STONE_PICK, STONE_SHOVEL, STONE_SWORD, TIN_AXE, TIN_HOE, TIN_PICK,
					TIN_SHOVEL, TIN_SWORD, COPPER_AXE, COPPER_HOE, COPPER_PICK, COPPER_SHOVEL, COPPER_SWORD, IRON_AXE, IRON_HOE,
					IRON_PICK, IRON_SHOVEL, IRON_SWORD,
					COPPER_FEET_ITEM, COPPER_HELMET_ITEM, COPPER_TORSO_ITEM, COPPER_LEGS_ITEM, 
					LEATHER_FEET_ITEM, LEATHER_HELMET_ITEM, LEATHER_TORSO_ITEM, LEATHER_LEGS_ITEM, 
					TIN_FEET_ITEM, TIN_HELMET_ITEM, TIN_TORSO_ITEM, TIN_LEGS_ITEM, 
					LEATHER_FEET_SLASH_UP, LEATHER_FEET_SLASH_DOWN, LEATHER_FEET_SLASH_LEFT, LEATHER_FEET_SLASH_RIGHT,
					LEATHER_FEET_WALK_UP, LEATHER_FEET_WALK_DOWN, LEATHER_FEET_WALK_LEFT, LEATHER_FEET_WALK_RIGHT,
					LEATHER_FEET_STAND_UP, LEATHER_FEET_STAND_DOWN, LEATHER_FEET_STAND_LEFT, LEATHER_FEET_STAND_RIGHT,
					LEATHER_LEGS_SLASH_UP, LEATHER_LEGS_SLASH_DOWN, LEATHER_LEGS_SLASH_LEFT, LEATHER_LEGS_SLASH_RIGHT,
					LEATHER_LEGS_WALK_UP, LEATHER_LEGS_WALK_DOWN, LEATHER_LEGS_WALK_LEFT, LEATHER_LEGS_WALK_RIGHT,
					LEATHER_LEGS_STAND_UP, LEATHER_LEGS_STAND_DOWN, LEATHER_LEGS_STAND_LEFT, LEATHER_LEGS_STAND_RIGHT,
					LEATHER_TORSO_SLASH_UP, LEATHER_TORSO_SLASH_DOWN, LEATHER_TORSO_SLASH_LEFT, LEATHER_TORSO_SLASH_RIGHT,
					LEATHER_TORSO_WALK_UP, LEATHER_TORSO_WALK_DOWN, LEATHER_TORSO_WALK_LEFT, LEATHER_TORSO_WALK_RIGHT,
					LEATHER_TORSO_STAND_UP, LEATHER_TORSO_STAND_DOWN, LEATHER_TORSO_STAND_LEFT, LEATHER_TORSO_STAND_RIGHT,
					LEATHER_HELMET_SLASH_UP, LEATHER_HELMET_SLASH_DOWN, LEATHER_HELMET_SLASH_LEFT, LEATHER_HELMET_SLASH_RIGHT,
					LEATHER_HELMET_WALK_UP, LEATHER_HELMET_WALK_DOWN, LEATHER_HELMET_WALK_LEFT, LEATHER_HELMET_WALK_RIGHT,
					LEATHER_HELMET_STAND_UP, LEATHER_HELMET_STAND_DOWN, LEATHER_HELMET_STAND_LEFT, LEATHER_HELMET_STAND_RIGHT,
					
					TIN_FEET_SLASH_UP, TIN_FEET_SLASH_DOWN, TIN_FEET_SLASH_LEFT, TIN_FEET_SLASH_RIGHT,
					TIN_FEET_WALK_UP, TIN_FEET_WALK_DOWN, TIN_FEET_WALK_LEFT, TIN_FEET_WALK_RIGHT,
					TIN_FEET_STAND_UP, TIN_FEET_STAND_DOWN, TIN_FEET_STAND_LEFT, TIN_FEET_STAND_RIGHT,
					TIN_LEGS_SLASH_UP, TIN_LEGS_SLASH_DOWN, TIN_LEGS_SLASH_LEFT, TIN_LEGS_SLASH_RIGHT,
					TIN_LEGS_WALK_UP, TIN_LEGS_WALK_DOWN, TIN_LEGS_WALK_LEFT, TIN_LEGS_WALK_RIGHT,
					TIN_LEGS_STAND_UP, TIN_LEGS_STAND_DOWN, TIN_LEGS_STAND_LEFT, TIN_LEGS_STAND_RIGHT,
					TIN_TORSO_SLASH_UP, TIN_TORSO_SLASH_DOWN, TIN_TORSO_SLASH_LEFT, TIN_TORSO_SLASH_RIGHT,
					TIN_TORSO_WALK_UP, TIN_TORSO_WALK_DOWN, TIN_TORSO_WALK_LEFT, TIN_TORSO_WALK_RIGHT,
					TIN_TORSO_STAND_UP, TIN_TORSO_STAND_DOWN, TIN_TORSO_STAND_LEFT, TIN_TORSO_STAND_RIGHT,
					TIN_HELMET_SLASH_UP, TIN_HELMET_SLASH_DOWN, TIN_HELMET_SLASH_LEFT, TIN_HELMET_SLASH_RIGHT,
					TIN_HELMET_WALK_UP, TIN_HELMET_WALK_DOWN, TIN_HELMET_WALK_LEFT, TIN_HELMET_WALK_RIGHT,
					TIN_HELMET_STAND_UP, TIN_HELMET_STAND_DOWN, TIN_HELMET_STAND_LEFT, TIN_HELMET_STAND_RIGHT,
					
					COPPER_FEET_SLASH_UP, COPPER_FEET_SLASH_DOWN, COPPER_FEET_SLASH_LEFT, COPPER_FEET_SLASH_RIGHT,
					COPPER_FEET_WALK_UP, COPPER_FEET_WALK_DOWN, COPPER_FEET_WALK_LEFT, COPPER_FEET_WALK_RIGHT,
					COPPER_FEET_STAND_UP, COPPER_FEET_STAND_DOWN, COPPER_FEET_STAND_LEFT, COPPER_FEET_STAND_RIGHT,
					COPPER_LEGS_SLASH_UP, COPPER_LEGS_SLASH_DOWN, COPPER_LEGS_SLASH_LEFT, COPPER_LEGS_SLASH_RIGHT,
					COPPER_LEGS_WALK_UP, COPPER_LEGS_WALK_DOWN, COPPER_LEGS_WALK_LEFT, COPPER_LEGS_WALK_RIGHT,
					COPPER_LEGS_STAND_UP, COPPER_LEGS_STAND_DOWN, COPPER_LEGS_STAND_LEFT, COPPER_LEGS_STAND_RIGHT,
					COPPER_TORSO_SLASH_UP, COPPER_TORSO_SLASH_DOWN, COPPER_TORSO_SLASH_LEFT, COPPER_TORSO_SLASH_RIGHT,
					COPPER_TORSO_WALK_UP, COPPER_TORSO_WALK_DOWN, COPPER_TORSO_WALK_LEFT, COPPER_TORSO_WALK_RIGHT,
					COPPER_TORSO_STAND_UP, COPPER_TORSO_STAND_DOWN, COPPER_TORSO_STAND_LEFT, COPPER_TORSO_STAND_RIGHT,
					COPPER_HELMET_SLASH_UP, COPPER_HELMET_SLASH_DOWN, COPPER_HELMET_SLASH_LEFT, COPPER_HELMET_SLASH_RIGHT,
					COPPER_HELMET_WALK_UP, COPPER_HELMET_WALK_DOWN, COPPER_HELMET_WALK_LEFT, COPPER_HELMET_WALK_RIGHT,
					COPPER_HELMET_STAND_UP, COPPER_HELMET_STAND_DOWN, COPPER_HELMET_STAND_LEFT, COPPER_HELMET_STAND_RIGHT,
					STAIRS_UP, STAIRS_DOWN, STAIRS_DOWN_TILE, LEATHER, STAIRS_UP, STAIRS_UP_TILE,
					FIRE_PIT_TILE,
					
					SLIME, SLIME_WALK_DOWN, SLIME_WALK_UP, SLIME_WALK_RIGHT, SLIME_WALK_LEFT,
					SLIME_STAND_DOWN, SLIME_STAND_UP, SLIME_STAND_RIGHT, SLIME_STAND_LEFT,
					
					WORM, WORM_WALK_DOWN, WORM_WALK_UP, WORM_WALK_RIGHT, WORM_WALK_LEFT,
					WORM_STAND_DOWN, WORM_STAND_UP, WORM_STAND_RIGHT, WORM_STAND_LEFT,
					
					COW, COW_WALK_DOWN, COW_WALK_UP, COW_WALK_RIGHT, COW_WALK_LEFT,
					COW_STAND_DOWN, COW_STAND_UP, COW_STAND_RIGHT, COW_STAND_LEFT,
					
					STINGER_SWORD, STEAK, COOKED_STEAK, HONEY, SLIME_BALL, WORM_GUTS,
					HEALTH_POTION, SPEED_POTION, HARMING_POTION, SLOWNESS_POTION, GLASS_BOTTLE,
					
					GRASS_NEW, DIRT_NEW, HOLE_NEW, SAND_NEW, DIRT_MOUNTAIN_BOTTOM_NEW,DIRT_MOUNTAIN_TOP_NEW,
					DIRT_MOUNTAIN_MIDDLE_NEW, WATER_NEW, DIRT_MOUNTAIN_COPPER_BOTTOM_NEW,
					DIRT_MOUNTAIN_COPPER_MIDDLE_NEW, DIRT_MOUNTAIN_TIN_MIDDLE_NEW, DIRT_MOUNTAIN_TIN_BOTTOM_NEW,
					DIRT_MOUNTAIN_COAL_MIDDLE_NEW, DIRT_MOUNTAIN_COAL_BOTTOM_NEW, STONE, DIRT_MOUNTAIN_CLUMP,
					STONE_MOUNTAIN_CLUMP,BREAD, SLOWNESS_APPLIED, SLOWNESS_STATUS, SPEED_STATUS, HARMING_APPLIED,
					BEEHIVE, BEEHIVE_TILE, BUTTON, BUTTON_SELECT, BUTTON_EDGE, BUTTON_SELECT_EDGE
					
			};
			
			String[] lightNames = {LIGHT, CAMPFIRE_LIGHT};
			String[][] nameArrays = {names, lightNames};
			for(int r = 0; r<atlasArray.length; r++) {
				
		
				try {
					JSONParser parser = new JSONParser();
					Object f = parser.parse(new FileReader("res/"+atlasStringArray[r]));
					JSONObject obj = (JSONObject) f;
					
					for(String name : nameArrays[r]) {
						JSONObject sprite = (JSONObject) obj.get(name);
						if(sprite.get("type").equals("single")) {
							StaticSprite s = new StaticSprite(atlasArray[r],
									getInteger(sprite.get("x")), getInteger(sprite.get("y")),
									getInteger(sprite.get("width")), getInteger(sprite.get("height")),
									getInteger(sprite.get("offsetX")), getInteger(sprite.get("offsetY")), Game.batch
								);
							
							sprites.put(name, s);
						}
						
						if(sprite.get("type").equals("static")) {
							JSONArray spts = (JSONArray) sprite.get("sprites");
							StaticSprite[] sList = new StaticSprite[spts.size()];
							
							for(int i = 0; i < sList.length; i++) {
								JSONObject s = (JSONObject) spts.get(i);
								sList[i] = new StaticSprite(atlasArray[r],
										getInteger(s.get("x")), getInteger(s.get("y")),
										getInteger(s.get("width")), getInteger(s.get("height")),
										getInteger(s.get("offsetX")), getInteger(s.get("offsetY")), Game.batch
									);
							}
							
							spriteCollections.put(name, sList);
						}
						
						if(sprite.get("type").equals("animated")) {
							JSONArray spts = (JSONArray) sprite.get("sprites");
							StaticSprite[] sList = new StaticSprite[spts.size()];
							int[] frameTimes = new int[spts.size()];
							
							for(int i = 0; i < sList.length; i++) {
								JSONObject s = (JSONObject) spts.get(i);
								sList[i] = new StaticSprite(atlasArray[r],
										getInteger(s.get("x")), getInteger(s.get("y")),
										getInteger(s.get("width")), getInteger(s.get("height")),
										getInteger(s.get("offsetX")), getInteger(s.get("offsetY")), Game.batch
									);
								frameTimes[i] = getInteger(sprite.get("frameTime"));
							}
							
							
							animations.put(name, new Animation(sList, frameTimes, 0));
							spriteCollections.put(name, sList);
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			waterSprites = new AnimatedSprite[13];
			for(int i = 0; i < waterSprites.length; i++) {
				Animation waterAnimation = new Animation(new StaticSprite[]{spriteCollections.get(WATER1)[i], spriteCollections.get(WATER2)[i]}, 800, i);
				waterSprites[i] = new AnimatedSprite(waterAnimation);
			}
			
			Animation animation = animations.get(CAMPFIRE_ANIMATION);
			animation.id = 0;
			campfireAnimation = new AnimatedSprite[]{new AnimatedSprite(animation)};
			
			animation = animations.get(TORCH_ANIMATION);
			animation.id = 0;
			torchAnimation = new AnimatedSprite[]{new AnimatedSprite(animation)};
			
			animation = animations.get(FURNACE_ANIMATION);
			animation.id = 0;
			furnaceAnimation = new AnimatedSprite[]{new AnimatedSprite(animation)};
			
			animation = animations.get(CAULDRON_ANIMATION);
			animation.id = 0;
			cauldronAnimation = new AnimatedSprite[]{new AnimatedSprite(animation)};
			
//			animation = animations.get(FURNACE_STATIC);
//			animation.id = 0;
			furnaceStatic = new StaticSprite[]{Sprites.sprites.get(FURNACE_SINGLE)};
			cauldronStatic = new StaticSprite[]{Sprites.sprites.get(CAULDRON_SINGLE)};
			
			pixel = sprites.get(PIXEL);
			healthBar = sprites.get(HEALTH_BAR_EDGE);
			healthBarMon = sprites.get(MON_HEALTH_EDGE);
			
//			sprites.put(0, waterSprites);
			
			saplingSprite = new StaticSprite[]{Sprites.sprites.get(STUMP)};
			
			treeLeafSprites = new StaticSprite[6];
			treeLeafSprites[0] = new StaticSprite(atlas, 16, 608, 64, 31, Game.batch);
			treeLeafSprites[1] = new StaticSprite(atlas, 1, 608, 15, 25, 49, 0, Game.batch);
			treeLeafSprites[2] = new StaticSprite(atlas, 2, 560, 14, 48, 50, 16, Game.batch);
			treeLeafSprites[3] = new StaticSprite(atlas, 16, 560, 64, 48, 0, 16, Game.batch);
			treeLeafSprites[4] = new StaticSprite(atlas, 80, 560, 13, 48, 0, 16, Game.batch); 
			treeLeafSprites[5] = new StaticSprite(atlas, 80, 608, 14, 26, Game.batch);
			
			treeSprites = new StaticSprite[1];
			treeSprites[0] = new StaticSprite(atlas, 16, 672, 64, 64, Game.batch);
			
			buttonSprite = new StaticSprite(atlas, 1024-189, 1024-64, 66, 32, Game.batch);
			pressedButtonSprite = new StaticSprite(atlas, 1024-189, 1024-32, 66, 32, Game.batch);
			
			collisionDebugger = new StaticSprite(atlas, 0, 32, TEXTURE_SIZE, TEXTURE_SIZE, Game.batch);
			
			int[] animationIds = {STAND_DOWN, STAND_RIGHT, STAND_UP, STAND_LEFT, WALK_DOWN, WALK_RIGHT, WALK_UP, WALK_LEFT, SLASH_DOWN, SLASH_RIGHT, SLASH_UP, SLASH_LEFT};
			int[] animationIdsMob = {STAND_DOWN, STAND_RIGHT, STAND_UP, STAND_LEFT, WALK_DOWN, WALK_RIGHT, WALK_UP, WALK_LEFT};
			Animation[] animations = new Animation[animationIds.length];
			Animation[] helmet = new Animation[animationIds.length];
			Animation[] chest = new Animation[animationIds.length];
			Animation[] pants = new Animation[animationIds.length];
			Animation[] boots = new Animation[animationIds.length];
			
			Animation[] leatherHelmet = new Animation[animationIds.length];
			Animation[] leatherChest = new Animation[animationIds.length];
			Animation[] leatherPants = new Animation[animationIds.length];
			Animation[] leatherBoots = new Animation[animationIds.length];
			
			Animation[] tinHelmet = new Animation[animationIds.length];
			Animation[] tinChest = new Animation[animationIds.length];
			Animation[] tinPants = new Animation[animationIds.length];
			Animation[] tinBoots = new Animation[animationIds.length];
			
			Animation[] copperHelmet = new Animation[animationIds.length];
			Animation[] copperChest = new Animation[animationIds.length];
			Animation[] copperPants = new Animation[animationIds.length];
			Animation[] copperBoots = new Animation[animationIds.length];
			
			Animation[] animationsMob = new Animation[animationIdsMob.length];
			Animation[] bat = new Animation[animationIdsMob.length];
			Animation[] bee = new Animation[animationIdsMob.length];
			Animation[] beeMini = new Animation[animationIdsMob.length];
			Animation[] sheep = new Animation[animationIdsMob.length];
			Animation[] slime = new Animation[animationIdsMob.length];
			Animation[] worm = new Animation[animationIdsMob.length];
			Animation[] cow = new Animation[animationIdsMob.length];
			
			animations = new Animation[]{
					Sprites.animations.get(MALE_STAND_UP),
					Sprites.animations.get(MALE_STAND_DOWN),
					Sprites.animations.get(MALE_STAND_RIGHT),
					Sprites.animations.get(MALE_STAND_LEFT),
					Sprites.animations.get(MALE_WALK_UP),
					Sprites.animations.get(MALE_WALK_DOWN),
					Sprites.animations.get(MALE_WALK_RIGHT),
					Sprites.animations.get(MALE_WALK_LEFT),
					
					Sprites.animations.get(MALE_SLASH_UP),
					Sprites.animations.get(MALE_SLASH_DOWN),
					Sprites.animations.get(MALE_SLASH_RIGHT),
					Sprites.animations.get(MALE_SLASH_LEFT)
			};
			
			helmet = new Animation[] {
					Sprites.animations.get(PLATE_HELMET_STAND_UP),
					Sprites.animations.get(PLATE_HELMET_STAND_DOWN),
					Sprites.animations.get(PLATE_HELMET_STAND_RIGHT),
					Sprites.animations.get(PLATE_HELMET_STAND_LEFT),
					Sprites.animations.get(PLATE_HELMET_WALK_UP),
					Sprites.animations.get(PLATE_HELMET_WALK_DOWN),
					Sprites.animations.get(PLATE_HELMET_WALK_RIGHT),
					Sprites.animations.get(PLATE_HELMET_WALK_LEFT),
					
					Sprites.animations.get(PLATE_HELMET_SLASH_UP),
					Sprites.animations.get(PLATE_HELMET_SLASH_DOWN),
					Sprites.animations.get(PLATE_HELMET_SLASH_RIGHT),
					Sprites.animations.get(PLATE_HELMET_SLASH_LEFT)
			};
			
			leatherHelmet = new Animation[] {
					Sprites.animations.get(LEATHER_HELMET_STAND_UP),
					Sprites.animations.get(LEATHER_HELMET_STAND_DOWN),
					Sprites.animations.get(LEATHER_HELMET_STAND_RIGHT),
					Sprites.animations.get(LEATHER_HELMET_STAND_LEFT),
					Sprites.animations.get(LEATHER_HELMET_WALK_UP),
					Sprites.animations.get(LEATHER_HELMET_WALK_DOWN),
					Sprites.animations.get(LEATHER_HELMET_WALK_RIGHT),
					Sprites.animations.get(LEATHER_HELMET_WALK_LEFT),
					
					Sprites.animations.get(LEATHER_HELMET_SLASH_UP),
					Sprites.animations.get(LEATHER_HELMET_SLASH_DOWN),
					Sprites.animations.get(LEATHER_HELMET_SLASH_RIGHT),
					Sprites.animations.get(LEATHER_HELMET_SLASH_LEFT)
			};
			
			tinHelmet = new Animation[] {
					Sprites.animations.get(TIN_HELMET_STAND_UP),
					Sprites.animations.get(TIN_HELMET_STAND_DOWN),
					Sprites.animations.get(TIN_HELMET_STAND_RIGHT),
					Sprites.animations.get(TIN_HELMET_STAND_LEFT),
					Sprites.animations.get(TIN_HELMET_WALK_UP),
					Sprites.animations.get(TIN_HELMET_WALK_DOWN),
					Sprites.animations.get(TIN_HELMET_WALK_RIGHT),
					Sprites.animations.get(TIN_HELMET_WALK_LEFT),
					
					Sprites.animations.get(TIN_HELMET_SLASH_UP),
					Sprites.animations.get(TIN_HELMET_SLASH_DOWN),
					Sprites.animations.get(TIN_HELMET_SLASH_RIGHT),
					Sprites.animations.get(TIN_HELMET_SLASH_LEFT)
			};
			
			copperHelmet = new Animation[] {
					Sprites.animations.get(COPPER_HELMET_STAND_UP),
					Sprites.animations.get(COPPER_HELMET_STAND_DOWN),
					Sprites.animations.get(COPPER_HELMET_STAND_RIGHT),
					Sprites.animations.get(COPPER_HELMET_STAND_LEFT),
					Sprites.animations.get(COPPER_HELMET_WALK_UP),
					Sprites.animations.get(COPPER_HELMET_WALK_DOWN),
					Sprites.animations.get(COPPER_HELMET_WALK_RIGHT),
					Sprites.animations.get(COPPER_HELMET_WALK_LEFT),
					
					Sprites.animations.get(COPPER_HELMET_SLASH_UP),
					Sprites.animations.get(COPPER_HELMET_SLASH_DOWN),
					Sprites.animations.get(COPPER_HELMET_SLASH_RIGHT),
					Sprites.animations.get(COPPER_HELMET_SLASH_LEFT)
			};
			
			chest = new Animation[] {
					Sprites.animations.get(PLATE_TORSO_STAND_UP),
					Sprites.animations.get(PLATE_TORSO_STAND_DOWN),
					Sprites.animations.get(PLATE_TORSO_STAND_RIGHT),
					Sprites.animations.get(PLATE_TORSO_STAND_LEFT),
					Sprites.animations.get(PLATE_TORSO_WALK_UP),
					Sprites.animations.get(PLATE_TORSO_WALK_DOWN),
					Sprites.animations.get(PLATE_TORSO_WALK_RIGHT),
					Sprites.animations.get(PLATE_TORSO_WALK_LEFT),
					
					Sprites.animations.get(PLATE_TORSO_SLASH_UP),
					Sprites.animations.get(PLATE_TORSO_SLASH_DOWN),
					Sprites.animations.get(PLATE_TORSO_SLASH_RIGHT),
					Sprites.animations.get(PLATE_TORSO_SLASH_LEFT)
			};
			
			leatherChest = new Animation[] {
					Sprites.animations.get(LEATHER_TORSO_STAND_UP),
					Sprites.animations.get(LEATHER_TORSO_STAND_DOWN),
					Sprites.animations.get(LEATHER_TORSO_STAND_RIGHT),
					Sprites.animations.get(LEATHER_TORSO_STAND_LEFT),
					Sprites.animations.get(LEATHER_TORSO_WALK_UP),
					Sprites.animations.get(LEATHER_TORSO_WALK_DOWN),
					Sprites.animations.get(LEATHER_TORSO_WALK_RIGHT),
					Sprites.animations.get(LEATHER_TORSO_WALK_LEFT),
					
					Sprites.animations.get(LEATHER_TORSO_SLASH_UP),
					Sprites.animations.get(LEATHER_TORSO_SLASH_DOWN),
					Sprites.animations.get(LEATHER_TORSO_SLASH_RIGHT),
					Sprites.animations.get(LEATHER_TORSO_SLASH_LEFT)
			};
			
			tinChest = new Animation[] {
					Sprites.animations.get(TIN_TORSO_STAND_UP),
					Sprites.animations.get(TIN_TORSO_STAND_DOWN),
					Sprites.animations.get(TIN_TORSO_STAND_RIGHT),
					Sprites.animations.get(TIN_TORSO_STAND_LEFT),
					Sprites.animations.get(TIN_TORSO_WALK_UP),
					Sprites.animations.get(TIN_TORSO_WALK_DOWN),
					Sprites.animations.get(TIN_TORSO_WALK_RIGHT),
					Sprites.animations.get(TIN_TORSO_WALK_LEFT),
					
					Sprites.animations.get(TIN_TORSO_SLASH_UP),
					Sprites.animations.get(TIN_TORSO_SLASH_DOWN),
					Sprites.animations.get(TIN_TORSO_SLASH_RIGHT),
					Sprites.animations.get(TIN_TORSO_SLASH_LEFT)
			};
			
			copperChest = new Animation[] {
					Sprites.animations.get(COPPER_TORSO_STAND_UP),
					Sprites.animations.get(COPPER_TORSO_STAND_DOWN),
					Sprites.animations.get(COPPER_TORSO_STAND_RIGHT),
					Sprites.animations.get(COPPER_TORSO_STAND_LEFT),
					Sprites.animations.get(COPPER_TORSO_WALK_UP),
					Sprites.animations.get(COPPER_TORSO_WALK_DOWN),
					Sprites.animations.get(COPPER_TORSO_WALK_RIGHT),
					Sprites.animations.get(COPPER_TORSO_WALK_LEFT),
					
					Sprites.animations.get(COPPER_TORSO_SLASH_UP),
					Sprites.animations.get(COPPER_TORSO_SLASH_DOWN),
					Sprites.animations.get(COPPER_TORSO_SLASH_RIGHT),
					Sprites.animations.get(COPPER_TORSO_SLASH_LEFT)
			};
			
			pants = new Animation[] {
					Sprites.animations.get(PLATE_LEGS_STAND_UP),
					Sprites.animations.get(PLATE_LEGS_STAND_DOWN),
					Sprites.animations.get(PLATE_LEGS_STAND_RIGHT),
					Sprites.animations.get(PLATE_LEGS_STAND_LEFT),
					Sprites.animations.get(PLATE_LEGS_WALK_UP),
					Sprites.animations.get(PLATE_LEGS_WALK_DOWN),
					Sprites.animations.get(PLATE_LEGS_WALK_RIGHT),
					Sprites.animations.get(PLATE_LEGS_WALK_LEFT),

					Sprites.animations.get(PLATE_LEGS_SLASH_UP),
					Sprites.animations.get(PLATE_LEGS_SLASH_DOWN),
					Sprites.animations.get(PLATE_LEGS_SLASH_RIGHT),
					Sprites.animations.get(PLATE_LEGS_SLASH_LEFT)
			};
			
			leatherPants = new Animation[] {
					Sprites.animations.get(LEATHER_LEGS_STAND_UP),
					Sprites.animations.get(LEATHER_LEGS_STAND_DOWN),
					Sprites.animations.get(LEATHER_LEGS_STAND_RIGHT),
					Sprites.animations.get(LEATHER_LEGS_STAND_LEFT),
					Sprites.animations.get(LEATHER_LEGS_WALK_UP),
					Sprites.animations.get(LEATHER_LEGS_WALK_DOWN),
					Sprites.animations.get(LEATHER_LEGS_WALK_RIGHT),
					Sprites.animations.get(LEATHER_LEGS_WALK_LEFT),

					Sprites.animations.get(LEATHER_LEGS_SLASH_UP),
					Sprites.animations.get(LEATHER_LEGS_SLASH_DOWN),
					Sprites.animations.get(LEATHER_LEGS_SLASH_RIGHT),
					Sprites.animations.get(LEATHER_LEGS_SLASH_LEFT)
			};
			
			tinPants = new Animation[] {
					Sprites.animations.get(TIN_LEGS_STAND_UP),
					Sprites.animations.get(TIN_LEGS_STAND_DOWN),
					Sprites.animations.get(TIN_LEGS_STAND_RIGHT),
					Sprites.animations.get(TIN_LEGS_STAND_LEFT),
					Sprites.animations.get(TIN_LEGS_WALK_UP),
					Sprites.animations.get(TIN_LEGS_WALK_DOWN),
					Sprites.animations.get(TIN_LEGS_WALK_RIGHT),
					Sprites.animations.get(TIN_LEGS_WALK_LEFT),

					Sprites.animations.get(TIN_LEGS_SLASH_UP),
					Sprites.animations.get(TIN_LEGS_SLASH_DOWN),
					Sprites.animations.get(TIN_LEGS_SLASH_RIGHT),
					Sprites.animations.get(TIN_LEGS_SLASH_LEFT)
			};
			
			copperPants = new Animation[] {
					Sprites.animations.get(COPPER_LEGS_STAND_UP),
					Sprites.animations.get(COPPER_LEGS_STAND_DOWN),
					Sprites.animations.get(COPPER_LEGS_STAND_RIGHT),
					Sprites.animations.get(COPPER_LEGS_STAND_LEFT),
					Sprites.animations.get(COPPER_LEGS_WALK_UP),
					Sprites.animations.get(COPPER_LEGS_WALK_DOWN),
					Sprites.animations.get(COPPER_LEGS_WALK_RIGHT),
					Sprites.animations.get(COPPER_LEGS_WALK_LEFT),

					Sprites.animations.get(COPPER_LEGS_SLASH_UP),
					Sprites.animations.get(COPPER_LEGS_SLASH_DOWN),
					Sprites.animations.get(COPPER_LEGS_SLASH_RIGHT),
					Sprites.animations.get(COPPER_LEGS_SLASH_LEFT)
			};
			
			boots = new Animation[] {
					Sprites.animations.get(PLATE_FEET_STAND_UP),
					Sprites.animations.get(PLATE_FEET_STAND_DOWN),
					Sprites.animations.get(PLATE_FEET_STAND_RIGHT),
					Sprites.animations.get(PLATE_FEET_STAND_LEFT),
					Sprites.animations.get(PLATE_FEET_WALK_UP),
					Sprites.animations.get(PLATE_FEET_WALK_DOWN),
					Sprites.animations.get(PLATE_FEET_WALK_RIGHT),
					Sprites.animations.get(PLATE_FEET_WALK_LEFT),
					
					Sprites.animations.get(PLATE_FEET_SLASH_UP),
					Sprites.animations.get(PLATE_FEET_SLASH_DOWN),
					Sprites.animations.get(PLATE_FEET_SLASH_RIGHT),
					Sprites.animations.get(PLATE_FEET_SLASH_LEFT)
			};
			
			leatherBoots = new Animation[] {
					Sprites.animations.get(LEATHER_FEET_STAND_UP),
					Sprites.animations.get(LEATHER_FEET_STAND_DOWN),
					Sprites.animations.get(LEATHER_FEET_STAND_RIGHT),
					Sprites.animations.get(LEATHER_FEET_STAND_LEFT),
					Sprites.animations.get(LEATHER_FEET_WALK_UP),
					Sprites.animations.get(LEATHER_FEET_WALK_DOWN),
					Sprites.animations.get(LEATHER_FEET_WALK_RIGHT),
					Sprites.animations.get(LEATHER_FEET_WALK_LEFT),
					
					Sprites.animations.get(LEATHER_FEET_SLASH_UP),
					Sprites.animations.get(LEATHER_FEET_SLASH_DOWN),
					Sprites.animations.get(LEATHER_FEET_SLASH_RIGHT),
					Sprites.animations.get(LEATHER_FEET_SLASH_LEFT)
			};
			
			tinBoots = new Animation[] {
					Sprites.animations.get(TIN_FEET_STAND_UP),
					Sprites.animations.get(TIN_FEET_STAND_DOWN),
					Sprites.animations.get(TIN_FEET_STAND_RIGHT),
					Sprites.animations.get(TIN_FEET_STAND_LEFT),
					Sprites.animations.get(TIN_FEET_WALK_UP),
					Sprites.animations.get(TIN_FEET_WALK_DOWN),
					Sprites.animations.get(TIN_FEET_WALK_RIGHT),
					Sprites.animations.get(TIN_FEET_WALK_LEFT),
					
					Sprites.animations.get(TIN_FEET_SLASH_UP),
					Sprites.animations.get(TIN_FEET_SLASH_DOWN),
					Sprites.animations.get(TIN_FEET_SLASH_RIGHT),
					Sprites.animations.get(TIN_FEET_SLASH_LEFT)
			};
			
			copperBoots = new Animation[] {
					Sprites.animations.get(COPPER_FEET_STAND_UP),
					Sprites.animations.get(COPPER_FEET_STAND_DOWN),
					Sprites.animations.get(COPPER_FEET_STAND_RIGHT),
					Sprites.animations.get(COPPER_FEET_STAND_LEFT),
					Sprites.animations.get(COPPER_FEET_WALK_UP),
					Sprites.animations.get(COPPER_FEET_WALK_DOWN),
					Sprites.animations.get(COPPER_FEET_WALK_RIGHT),
					Sprites.animations.get(COPPER_FEET_WALK_LEFT),
					
					Sprites.animations.get(COPPER_FEET_SLASH_UP),
					Sprites.animations.get(COPPER_FEET_SLASH_DOWN),
					Sprites.animations.get(COPPER_FEET_SLASH_RIGHT),
					Sprites.animations.get(COPPER_FEET_SLASH_LEFT)
			};
			
			bat = new Animation[] {
					Sprites.animations.get(BAT_STAND_UP),
					Sprites.animations.get(BAT_STAND_DOWN),
					Sprites.animations.get(BAT_STAND_RIGHT),
					Sprites.animations.get(BAT_STAND_LEFT),
					Sprites.animations.get(BAT_WALK_UP),
					Sprites.animations.get(BAT_WALK_DOWN),
					Sprites.animations.get(BAT_WALK_RIGHT),
					Sprites.animations.get(BAT_WALK_LEFT)
			};
			
			sheep = new Animation[] {
					Sprites.animations.get(SHEEP_STAND_UP),
					Sprites.animations.get(SHEEP_STAND_DOWN),
					Sprites.animations.get(SHEEP_STAND_RIGHT),
					Sprites.animations.get(SHEEP_STAND_LEFT),
					Sprites.animations.get(SHEEP_WALK_UP),
					Sprites.animations.get(SHEEP_WALK_DOWN),
					Sprites.animations.get(SHEEP_WALK_RIGHT),
					Sprites.animations.get(SHEEP_WALK_LEFT)
			};
			
			bee = new Animation[] {
					Sprites.animations.get(BEE_STAND_UP),
					Sprites.animations.get(BEE_STAND_DOWN),
					Sprites.animations.get(BEE_STAND_RIGHT),
					Sprites.animations.get(BEE_STAND_LEFT),
					Sprites.animations.get(BEE_WALK_UP),
					Sprites.animations.get(BEE_WALK_DOWN),
					Sprites.animations.get(BEE_WALK_RIGHT),
					Sprites.animations.get(BEE_WALK_LEFT)
			};
			
			cow = new Animation[] {
					Sprites.animations.get(COW_STAND_UP),
					Sprites.animations.get(COW_STAND_DOWN),
					Sprites.animations.get(COW_STAND_RIGHT),
					Sprites.animations.get(COW_STAND_LEFT),
					Sprites.animations.get(COW_WALK_UP),
					Sprites.animations.get(COW_WALK_DOWN),
					Sprites.animations.get(COW_WALK_RIGHT),
					Sprites.animations.get(COW_WALK_LEFT)
			};
			
			worm = new Animation[] {
					Sprites.animations.get(WORM_STAND_UP),
					Sprites.animations.get(WORM_STAND_DOWN),
					Sprites.animations.get(WORM_STAND_RIGHT),
					Sprites.animations.get(WORM_STAND_LEFT),
					Sprites.animations.get(WORM_WALK_UP),
					Sprites.animations.get(WORM_WALK_DOWN),
					Sprites.animations.get(WORM_WALK_RIGHT),
					Sprites.animations.get(WORM_WALK_LEFT)
			};
			
			slime = new Animation[] {
					Sprites.animations.get(SLIME_STAND_UP),
					Sprites.animations.get(SLIME_STAND_DOWN),
					Sprites.animations.get(SLIME_STAND_RIGHT),
					Sprites.animations.get(SLIME_STAND_LEFT),
					Sprites.animations.get(SLIME_WALK_UP),
					Sprites.animations.get(SLIME_WALK_DOWN),
					Sprites.animations.get(SLIME_WALK_RIGHT),
					Sprites.animations.get(SLIME_WALK_LEFT)
			};
			
			beeMini = new Animation[] {
					Sprites.animations.get(BEE_MINI_STAND_UP),
					Sprites.animations.get(BEE_MINI_STAND_DOWN),
					Sprites.animations.get(BEE_MINI_STAND_RIGHT),
					Sprites.animations.get(BEE_MINI_STAND_LEFT),
					Sprites.animations.get(BEE_MINI_WALK_UP),
					Sprites.animations.get(BEE_MINI_WALK_DOWN),
					Sprites.animations.get(BEE_MINI_WALK_RIGHT),
					Sprites.animations.get(BEE_MINI_WALK_LEFT)
			};
			
			for(int i = 0; i < animations.length; i++) {
				animations[i].id = i;
				helmet[i].id = i;
				leatherHelmet[i].id = i;
				tinHelmet[i].id = i;
				copperHelmet[i].id = i;
				chest[i].id = i;
				leatherChest[i].id = i;
				tinChest[i].id = i;
				copperChest[i].id = i;
				pants[i].id = i;
				leatherPants[i].id = i;
				tinPants[i].id = i;
				copperPants[i].id = i;
				boots[i].id = i;
				leatherBoots[i].id = i;
				tinBoots[i].id = i;
				copperBoots[i].id = i;
			}
			
			for(int i = 0; i < animationsMob.length; i++) {
				bat[i].id = i;
				bee[i].id = i;
				beeMini[i].id = i;
				sheep[i].id = i;
				slime[i].id = i;
				worm[i].id = i;
				cow[i].id = i;
			}
			
			AnimatedSprite animatedSprite = new AnimatedSprite(animations);
			AnimatedSprite animatedHelmet = new AnimatedSprite(helmet);
			animatedHelmet.stackPosition = 5;
			AnimatedSprite animatedLeatherHelmet = new AnimatedSprite(leatherHelmet);
			animatedLeatherHelmet.stackPosition = 5;
			AnimatedSprite animatedTinHelmet = new AnimatedSprite(tinHelmet);
			animatedTinHelmet.stackPosition = 5;
			AnimatedSprite animatedCopperHelmet = new AnimatedSprite(copperHelmet);
			animatedCopperHelmet.stackPosition = 5;
			
			AnimatedSprite animatedChest = new AnimatedSprite(chest);
			animatedChest.stackPosition = 4;
			AnimatedSprite animatedLeatherChest = new AnimatedSprite(leatherChest);
			animatedLeatherChest.stackPosition = 4;
			AnimatedSprite animatedTinChest = new AnimatedSprite(tinChest);
			animatedTinChest.stackPosition = 4;
			AnimatedSprite animatedCopperChest = new AnimatedSprite(copperChest);
			animatedCopperChest.stackPosition = 4;
			
			AnimatedSprite animatedPants = new AnimatedSprite(pants);
			animatedPants.stackPosition = 3;
			AnimatedSprite animatedLeatherPants = new AnimatedSprite(leatherPants);
			animatedLeatherPants.stackPosition = 3;
			AnimatedSprite animatedTinPants = new AnimatedSprite(tinPants);
			animatedTinPants.stackPosition = 3;
			AnimatedSprite animatedCopperPants = new AnimatedSprite(copperPants);
			animatedCopperPants.stackPosition = 3;
			
			AnimatedSprite animatedBoots = new AnimatedSprite(boots);
			animatedBoots.stackPosition = 2;
			AnimatedSprite animatedLeatherBoots = new AnimatedSprite(leatherBoots);
			animatedLeatherBoots.stackPosition = 2;
			AnimatedSprite animatedTinBoots = new AnimatedSprite(tinBoots);
			animatedTinBoots.stackPosition = 2;
			AnimatedSprite animatedCopperBoots = new AnimatedSprite(copperBoots);
			animatedCopperBoots.stackPosition = 2;
			
			AnimatedSprite animatedBat = new AnimatedSprite(bat);
			AnimatedSprite animatedSheep = new AnimatedSprite(sheep);
			AnimatedSprite animatedBee = new AnimatedSprite(bee);
			AnimatedSprite animatedWorm = new AnimatedSprite(worm);
			AnimatedSprite animatedSlime = new AnimatedSprite(slime);
			AnimatedSprite animatedCow = new AnimatedSprite(cow);
			AnimatedSprite animatedBeeMini = new AnimatedSprite(beeMini);
			
			animatedSprites.put(HUMAN_BASE, animatedSprite);
			animatedSprites.put(PLATE_HELMET_ITEM, animatedHelmet);
			animatedSprites.put(LEATHER_HELMET_ITEM, animatedLeatherHelmet);
			animatedSprites.put(TIN_HELMET_ITEM, animatedTinHelmet);
			animatedSprites.put(COPPER_HELMET_ITEM, animatedCopperHelmet);
			
			animatedSprites.put(PLATE_TORSO_ITEM, animatedChest);
			animatedSprites.put(LEATHER_TORSO_ITEM, animatedLeatherChest);
			animatedSprites.put(TIN_TORSO_ITEM, animatedTinChest);
			animatedSprites.put(COPPER_TORSO_ITEM, animatedCopperChest);
			
			animatedSprites.put(PLATE_LEGS_ITEM, animatedPants);
			animatedSprites.put(LEATHER_LEGS_ITEM, animatedLeatherPants);
			animatedSprites.put(TIN_LEGS_ITEM, animatedTinPants);
			animatedSprites.put(COPPER_LEGS_ITEM, animatedCopperPants);
			
			animatedSprites.put(PLATE_FEET_ITEM, animatedBoots);
			animatedSprites.put(LEATHER_FEET_ITEM, animatedLeatherBoots);
			animatedSprites.put(TIN_FEET_ITEM, animatedTinBoots);
			animatedSprites.put(COPPER_FEET_ITEM, animatedCopperBoots);
			
			animatedSprites.put(BAT, animatedBat);
			animatedSprites.put(BEE, animatedBee);
			animatedSprites.put(SLIME, animatedSlime);
			animatedSprites.put(BEE_MINI, animatedBeeMini);
			animatedSprites.put(SHEEP, animatedSheep);
			animatedSprites.put(WORM, animatedWorm);
			animatedSprites.put(COW, animatedCow);
			
			fireAnimationSprites = new StaticSprite[4];
			fireAnimationSprites[0] = new StaticSprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[1] = new StaticSprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[2] = new StaticSprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[3] = new StaticSprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
		
			
			//font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("res/arial.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			Texture texture = new Texture(Gdx.files.internal("res/Arial.png"));
			font = new BitmapFont(Gdx.files.internal("res/Arial.fnt"),new TextureRegion(texture), false);
			
			texture = new Texture(Gdx.files.internal("res/Arial24.png"));
			arial24 = new BitmapFont(Gdx.files.internal("res/Arial24.fnt"),new TextureRegion(texture), false);
			
			texture = new Texture(Gdx.files.internal("res/Arial10.png"));
			arial10 = new BitmapFont(Gdx.files.internal("res/Arial10.fnt"),new TextureRegion(texture), false);
//			font = new BitmapFont();
			
			font.setColor(1f, 1f, 1f, 1f);
			font.setScale(1f, -1f);
			arial24.setScale(1f, -1f);
			arial10.setScale(1f, -1f);
			font.setColor(1f, 1f, 1f, 1f);
	}
}