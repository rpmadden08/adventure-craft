package com.madbros.adventurecraft.Sprites;

//import java.awt.Font;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.madbros.adventurecraft.Game;
//import org.newdawn.slick.TrueTypeFont;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;

import static com.madbros.adventurecraft.Constants.*;

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
	public static final String HEALTH_BAR_EDGE = "healthBarEdge";
	public static final String MON_HEALTH_EDGE = "monHealthEdge";
	public static final String TILLED_SOIL = "tilledSoil";
	
	public static final String PIXEL = "pixel";
	public static final String SAPLING = "sapling";
	public static final String SEED_POTATO = "seeds";
	public static final String STUMP = "stump";
	public static final String GRASS_ITEM = "grassItem";
	public static final String SNOW_ITEM = "snowItem";
	public static final String DARK_GRASS_ITEM = "darkGrassItem";
	public static final String DIRT_ITEM = "dirtItem";
	public static final String DIRT_MOUNTAIN_ITEM = "dirtMountainItem";
	public static final String SAND_ITEM = "sandItem";
	public static final String LOG_ITEM = "logItem";
	public static final String PLANK_ITEM = "plankItem";
	public static final String PLATE_HELMET_ITEM = "plateHelmetItem";
	public static final String PLATE_TORSO_ITEM = "plateTorsoItem";
	public static final String PLATE_LEGS_ITEM = "plateLegsItem";
	public static final String PLATE_FEET_ITEM = "plateFeetItem";
	public static final String BAT = "bat";
	public static final String SWORD_ITEM = "swordItem";
	public static final String SWORD = "sword";
	public static final String LONG_SWORD = "longsword";
	public static final String SHOVEL_ITEM = "shovelItem";
	public static final String AXE_ITEM = "stoneAxe";
	public static final String BAT_WING = "batWing";
	public static final String HOE = "ironHoe";
	public static final String TABLE_ITEM = "table";
	public static final String CHEST_ITEM = "chest";
	public static final String PICK = "ironPick";
	
	//static collections
//	public static final String GEM = "Gem Blue";
	public static final String DESERT = "desert";
	public static final String GRASS = "grass";
	public static final String SPACE = "space";
	public static final String HOLE = "hole";
	public static final String LIGHT_DIRT = "lightDirt";
	public static final String MOUNTAIN_BOTTOM = "mountainBottom";
	public static final String MOUNTAIN_TOP = "mountainTop";
	public static final String MOUNTAIN_COAL_BOTTOM = "mountainCoalBottom";
	public static final String MOUNTAIN_COAL_TOP = "mountainCoalTop";
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
	
	
	
	//animated collections
	public static final String CAMPFIRE_ANIMATION = "campfire";
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
	
	
	public static StaticSprite[] fireAnimationSprites;
	
	public static StaticSprite buttonSprite;
	public static StaticSprite pressedButtonSprite;
	
	public static AnimatedSprite[] waterSprites;
	public static StaticSprite[] saplingSprite;
	public static StaticSprite[] treeSprites;
	public static StaticSprite[] treeLeafSprites;
	public static AnimatedSprite[] campfireAnimation;
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
					MALE_SLASH_DOWN, MALE_SLASH_LEFT, MALE_SLASH_RIGHT, SWORD, LONG_SWORD, HEALTH_BAR_EDGE,
					MON_HEALTH_EDGE, BAT_WING, HOE, SEED_POTATO, POTATO_PLANT, POTATOES, TILLED_SOIL,
					TABLE, TABLE_ITEM, CHEST, CHEST_ITEM, FURNACE_ANIMATION, FURNACE_SINGLE,
					BAKED_POTATOES, FURNACE_TOP, CAULDRON_ANIMATION, CAULDRON, CAULDRON_SINGLE,
					PLATE_TORSO_SLASH_UP, PLATE_TORSO_SLASH_DOWN, PLATE_TORSO_SLASH_LEFT, 
					PLATE_TORSO_SLASH_RIGHT,PLATE_HELMET_SLASH_UP, PLATE_HELMET_SLASH_DOWN, PLATE_HELMET_SLASH_LEFT, 
					PLATE_HELMET_SLASH_RIGHT, PLATE_LEGS_SLASH_UP, PLATE_LEGS_SLASH_DOWN, PLATE_LEGS_SLASH_LEFT, 
					PLATE_LEGS_SLASH_RIGHT, PLATE_FEET_SLASH_UP, PLATE_FEET_SLASH_DOWN, PLATE_FEET_SLASH_LEFT, 
					PLATE_FEET_SLASH_RIGHT, PICK, MOUNTAIN_COAL_TOP, MOUNTAIN_COAL_BOTTOM, SPACE, SNOW_ITEM,
					DARK_GRASS_ITEM, DIRT_MOUNTAIN_ITEM
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
			
			Animation[] animationsMob = new Animation[animationIdsMob.length];
			Animation[] bat = new Animation[animationIdsMob.length];
			
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
			
			bat = new Animation[] {
					Sprites.animations.get(BAT_STAND_UP),
					Sprites.animations.get(BAT_STAND_DOWN),
					Sprites.animations.get(BAT_STAND_RIGHT),
					Sprites.animations.get(BAT_STAND_LEFT),
					Sprites.animations.get(BAT_WALK_UP),
					Sprites.animations.get(BAT_WALK_DOWN),
					Sprites.animations.get(BAT_WALK_RIGHT),
					Sprites.animations.get(BAT_WALK_LEFT)
					
//					Sprites.animations.get(BAT_WALK_LEFT),
//					Sprites.animations.get(BAT_WALK_LEFT),
//					Sprites.animations.get(BAT_WALK_LEFT),
//					Sprites.animations.get(BAT_WALK_LEFT),
			};
			
			for(int i = 0; i < animations.length; i++) {
				animations[i].id = i;
				helmet[i].id = i;
				chest[i].id = i;
				pants[i].id = i;
				boots[i].id = i;
			}
			
			for(int i = 0; i < animationsMob.length; i++) {
				bat[i].id = i;
			}
			
			AnimatedSprite animatedSprite = new AnimatedSprite(animations);
			AnimatedSprite animatedHelmet = new AnimatedSprite(helmet);
			animatedHelmet.stackPosition = 5;
			AnimatedSprite animatedChest = new AnimatedSprite(chest);
			animatedChest.stackPosition = 4;
			AnimatedSprite animatedPants = new AnimatedSprite(pants);
			animatedPants.stackPosition = 3;
			AnimatedSprite animatedBoots = new AnimatedSprite(boots);
			animatedBoots.stackPosition = 2;
			
			AnimatedSprite animatedBat = new AnimatedSprite(bat);
			
			animatedSprites.put(HUMAN_BASE, animatedSprite);
			animatedSprites.put(PLATE_HELMET_ITEM, animatedHelmet);
			animatedSprites.put(PLATE_TORSO_ITEM, animatedChest);
			animatedSprites.put(PLATE_LEGS_ITEM, animatedPants);
			animatedSprites.put(PLATE_FEET_ITEM, animatedBoots);
			animatedSprites.put(BAT, animatedBat);
			
			fireAnimationSprites = new StaticSprite[4];
			fireAnimationSprites[0] = new StaticSprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[1] = new StaticSprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[2] = new StaticSprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
			fireAnimationSprites[3] = new StaticSprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4, Game.batch);
		
			//font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("res/8Bit16.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			font = new BitmapFont();
			font.setColor(1f, 1f, 1f, 1f);
			font.setScale(1f, -1f);
			font.setColor(1f, 1f, 1f, 1f);
	}
}