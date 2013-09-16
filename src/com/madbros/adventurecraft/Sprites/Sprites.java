package com.madbros.adventurecraft.Sprites;

import java.awt.Font;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static com.madbros.adventurecraft.Constants.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Sprites {
	public static Texture atlas;
	
	public static final String HUMAN_BASE = "1";
	
	//singles
	public static final String DARK_DIRT = "darkDirtSingle";
	public static final String COLLISION_DETECTION = "collisionDetection";
	public static final String INVENTORY_MENU_SELECTOR = "inventoryMenuSelector";
	public static final String INVENTORY_MENU_SLOT = "inventoryMenuSlot";
	public static final String PIXEL = "pixel";
	public static final String SAPLING = "sapling";
	public static final String STUMP = "stump";
	public static final String GRASS_ITEM = "grassItem";
	public static final String DIRT_ITEM = "dirtItem";
	public static final String SAND_ITEM = "sandItem";
	public static final String LOG_ITEM = "logItem";
	public static final String PLANK_ITEM = "plankItem";
	public static final String PLATE_HELMET_ITEM = "plateHelmetItem";
	public static final String PLATE_TORSO_ITEM = "plateTorsoItem";
	public static final String PLATE_LEGS_ITEM = "plateLegsItem";
	public static final String PLATE_FEET_ITEM = "plateFeetItem";
	public static final String SWORD_ITEM = "swordItem";
	public static final String SHOVEL_ITEM = "shovelItem";
	
	//static collections
//	public static final String GEM = "Gem Blue";
	public static final String DESERT = "desert";
	public static final String GRASS = "grass";
	public static final String HOLE = "hole";
	public static final String LIGHT_DIRT = "lightDirt";
	public static final String MOUNTAIN_BOTTOM = "mountainBottom";
	public static final String MOUNTAIN_TOP = "mountainTop";
	public static final String TREE = "tree";
	public static final String WATER1 = "water";
	public static final String WATER2 = "waterTwo";
	public static final String INVENTORY_MENU = "inventoryMenu";
	
	//animated collections
	public static final String CAMPFIRE_ANIMATION = "campfire";
	public static final String MALE_WALK_DOWN = "maleWalkDown";
	public static final String MALE_WALK_LEFT = "maleWalkLeft";
	public static final String MALE_WALK_RIGHT = "maleWalkRight";
	public static final String MALE_WALK_UP = "maleWalkUp";
	public static final String MALE_STAND_DOWN = "maleStandDown";
	public static final String MALE_STAND_LEFT = "maleStandLeft";
	public static final String MALE_STAND_RIGHT = "maleStandRight";
	public static final String MALE_STAND_UP = "maleStandUp";
	
	public static final String PLATE_FEET_WALK_DOWN = "plateFeetWalkDown";
	public static final String PLATE_FEET_WALK_LEFT = "plateFeetWalkLeft";
	public static final String PLATE_FEET_WALK_RIGHT = "plateFeetWalkRight";
	public static final String PLATE_FEET_WALK_UP = "plateFeetWalkUp";
	public static final String PLATE_FEET_STAND_DOWN = "plateFeetStandDown";
	public static final String PLATE_FEET_STAND_LEFT = "plateFeetStandLeft";
	public static final String PLATE_FEET_STAND_RIGHT = "plateFeetStandRight";
	public static final String PLATE_FEET_STAND_UP = "plateFeetStandUp";
	
	public static final String PLATE_LEGS_WALK_DOWN = "plateLegsWalkDown";
	public static final String PLATE_LEGS_WALK_LEFT = "plateLegsWalkLeft";
	public static final String PLATE_LEGS_WALK_RIGHT = "plateLegsWalkRight";
	public static final String PLATE_LEGS_WALK_UP = "plateLegsWalkUp";
	public static final String PLATE_LEGS_STAND_DOWN = "plateLegsStandDown";
	public static final String PLATE_LEGS_STAND_LEFT = "plateLegsStandLeft";
	public static final String PLATE_LEGS_STAND_RIGHT = "plateLegsStandRight";
	public static final String PLATE_LEGS_STAND_UP = "plateLegsStandUp";
	
	public static final String PLATE_HELMET_WALK_DOWN = "plateHelmetWalkDown";
	public static final String PLATE_HELMET_WALK_LEFT = "plateHelmetWalkLeft";
	public static final String PLATE_HELMET_WALK_RIGHT = "plateHelmetWalkRight";
	public static final String PLATE_HELMET_WALK_UP = "plateHelmetWalkUp";
	public static final String PLATE_HELMET_STAND_DOWN = "plateHelmetStandDown";
	public static final String PLATE_HELMET_STAND_LEFT = "plateHelmetStandLeft";
	public static final String PLATE_HELMET_STAND_RIGHT = "plateHelmetStandRight";
	public static final String PLATE_HELMET_STAND_UP = "plateHelmetStandUp";
	
	public static final String PLATE_TORSO_WALK_DOWN = "plateTorsoWalkDown";
	public static final String PLATE_TORSO_WALK_LEFT = "plateTorsoWalkLeft";
	public static final String PLATE_TORSO_WALK_RIGHT = "plateTorsoWalkRight";
	public static final String PLATE_TORSO_WALK_UP = "plateTorsoWalkUp";
	public static final String PLATE_TORSO_STAND_DOWN = "plateTorsoStandDown";
	public static final String PLATE_TORSO_STAND_LEFT = "plateTorsoStandLeft";
	public static final String PLATE_TORSO_STAND_RIGHT = "plateTorsoStandRight";
	public static final String PLATE_TORSO_STAND_UP = "plateTorsoStandUp";
	
	public static final String SKELETON_WALK_DOWN = "skeletonWalkDown";
	public static final String SKELETON_WALK_RIGHT = "skeletonWalkRight";
	public static final String SKELETON_WALK_LEFT = "skeletonWalkLeft";
	public static final String SKELETON_WALK_UP = "skeletonWalkUp";
	public static final String SKELETON_STAND_DOWN = "skeletonStandDown";
	public static final String SKELETON_STAND_RIGHT = "skeletonStandRight";
	public static final String SKELETON_STAND_LEFT = "skeletonStandLeft";
	public static final String SKELETON_STAND_UP = "skeletonStandUp";

	public static StaticSprite[] fireAnimationSprites;
	
	public static StaticSprite buttonSprite;
	public static StaticSprite pressedButtonSprite;
	
	public static AnimatedSprite[] waterSprites;
	public static StaticSprite[] saplingSprite;
	public static StaticSprite[] treeSprites;
	public static StaticSprite[] treeLeafSprites;
	
	//for debugging
	public static StaticSprite collisionDebugger;
	public static StaticSprite pixel;
	
	public static TrueTypeFont font;
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
		try {
			atlas = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"), GL11.GL_NEAREST);
			//atlas2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas2.png"), GL11.GL_NEAREST);
			
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
					PLATE_TORSO_STAND_RIGHT, PLATE_TORSO_STAND_UP, TREE, STUMP
			};

			try {
				JSONParser parser = new JSONParser();
				Object f = parser.parse(new FileReader("res/atlas.json"));
				JSONObject obj = (JSONObject) f;
				
				for(String name : names) {
					JSONObject sprite = (JSONObject) obj.get(name);
					if(sprite.get("type").equals("single")) {
						StaticSprite s = new StaticSprite(atlas,
								getInteger(sprite.get("x")), getInteger(sprite.get("y")),
								getInteger(sprite.get("width")), getInteger(sprite.get("height")),
								getInteger(sprite.get("offsetX")), getInteger(sprite.get("offsetY"))
							);
						
						sprites.put(name, s);
					}
					
					if(sprite.get("type").equals("static")) {
						JSONArray spts = (JSONArray) sprite.get("sprites");
						StaticSprite[] sList = new StaticSprite[spts.size()];
						
						for(int i = 0; i < sList.length; i++) {
							JSONObject s = (JSONObject) spts.get(i);
							sList[i] = new StaticSprite(atlas,
									getInteger(s.get("x")), getInteger(s.get("y")),
									getInteger(s.get("width")), getInteger(s.get("height")),
									getInteger(s.get("offsetX")), getInteger(s.get("offsetY"))
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
							sList[i] = new StaticSprite(atlas,
									getInteger(s.get("x")), getInteger(s.get("y")),
									getInteger(s.get("width")), getInteger(s.get("height")),
									getInteger(s.get("offsetX")), getInteger(s.get("offsetY"))
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
			
			waterSprites = new AnimatedSprite[13];
			for(int i = 0; i < waterSprites.length; i++) {
				Animation waterAnimation = new Animation(new StaticSprite[]{spriteCollections.get(WATER1)[i], spriteCollections.get(WATER2)[i]}, 800, i);
				waterSprites[i] = new AnimatedSprite(waterAnimation);
			}
			
			pixel = sprites.get(PIXEL);
			
//			sprites.put(0, waterSprites);
			
			saplingSprite = new StaticSprite[]{Sprites.sprites.get(STUMP)};
			
			treeLeafSprites = new StaticSprite[6];
			treeLeafSprites[0] = new StaticSprite(atlas, 16, 608, 64, 31);
			treeLeafSprites[1] = new StaticSprite(atlas, 1, 608, 15, 25, 49, 0);
			treeLeafSprites[2] = new StaticSprite(atlas, 2, 560, 14, 48, 50, 16);
			treeLeafSprites[3] = new StaticSprite(atlas, 16, 560, 64, 48, 0, 16);
			treeLeafSprites[4] = new StaticSprite(atlas, 80, 560, 13, 48, 0, 16); 
			treeLeafSprites[5] = new StaticSprite(atlas, 80, 608, 14, 26);
			
			treeSprites = new StaticSprite[1];
			treeSprites[0] = new StaticSprite(atlas, 16, 672, 64, 64);
			
			buttonSprite = new StaticSprite(atlas, 1024-189, 1024-64, 66, 32);
			pressedButtonSprite = new StaticSprite(atlas, 1024-189, 1024-32, 66, 32);
			
			collisionDebugger = new StaticSprite(atlas, 0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
			
			int[] animationIds = {STAND_DOWN, STAND_RIGHT, STAND_UP, STAND_LEFT, WALK_DOWN, WALK_RIGHT, WALK_UP, WALK_LEFT};
			Animation[] animations = new Animation[animationIds.length];
			Animation[] helmet = new Animation[animationIds.length];
			Animation[] chest = new Animation[animationIds.length];
			Animation[] pants = new Animation[animationIds.length];
			Animation[] boots = new Animation[animationIds.length];
			
			int sX = 0; int sY = 96;
			
			for(int i = 0; i < 4; i ++) {
				StaticSprite[] frames = new StaticSprite[4];
				StaticSprite[] helmetFrames = new StaticSprite[4];
				StaticSprite[] chestFrames = new StaticSprite[4];
				StaticSprite[] pantFrames = new StaticSprite[4];
				StaticSprite[] bootFrames = new StaticSprite[4];
				

				for(int j = 0; j < 4; j++) {
					if(j == 1) {
						animations[i] = new Animation(new StaticSprite(atlas, sX + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 400, animationIds[i]);
						helmet[i] = new Animation(new StaticSprite(atlas, sX+192 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 400, animationIds[i]);
						chest[i] = new Animation(new StaticSprite(atlas, sX+384 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 400, animationIds[i]);
						pants[i] = new Animation(new StaticSprite(atlas, sX+576 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 400, animationIds[i]);
						boots[i] = new Animation(new StaticSprite(atlas, sX+768 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 400, animationIds[i]);
					} 
					
					if(j == 1 || j == 3) {
						frames[j] = new StaticSprite(atlas, sX + CHARACTER_SIZE, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						helmetFrames[j] = new StaticSprite(atlas, sX+192 + CHARACTER_SIZE, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						chestFrames[j] = new StaticSprite(atlas, sX+384 + CHARACTER_SIZE, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						pantFrames[j] = new StaticSprite(atlas, sX+576 + CHARACTER_SIZE, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						bootFrames[j] = new StaticSprite(atlas, sX+768 + CHARACTER_SIZE, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
					}
					else {
						frames[j] = new StaticSprite(atlas, sX + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						helmetFrames[j] = new StaticSprite(atlas, sX+192 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						chestFrames[j] = new StaticSprite(atlas, sX+384 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						pantFrames[j] = new StaticSprite(atlas, sX+576 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
						bootFrames[j] = new StaticSprite(atlas, sX+768 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
					}
				}

				animations[i+4] = new Animation(frames, 200, animationIds[i+4]);
				helmet[i+4] = new Animation(helmetFrames, 200, animationIds[i+4]);
				chest[i+4] = new Animation(chestFrames, 200, animationIds[i+4]);
				pants[i+4] = new Animation(pantFrames, 200, animationIds[i+4]);
				boots[i+4] = new Animation(bootFrames, 200, animationIds[i+4]);
			}
			
			animations = new Animation[]{
					Sprites.animations.get(MALE_STAND_UP),
					Sprites.animations.get(MALE_STAND_DOWN),
					Sprites.animations.get(MALE_STAND_RIGHT),
					Sprites.animations.get(MALE_STAND_LEFT),
					Sprites.animations.get(MALE_WALK_UP),
					Sprites.animations.get(MALE_WALK_DOWN),
					Sprites.animations.get(MALE_WALK_RIGHT),
					Sprites.animations.get(MALE_WALK_LEFT)
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
			};
			
			for(int i = 0; i < animations.length; i++) {
				animations[i].id = i;
				helmet[i].id = i;
				chest[i].id = i;
				pants[i].id = i;
				boots[i].id = i;
			}
			
			AnimatedSprite animatedSprite = new AnimatedSprite(animations);
			AnimatedSprite animatedHelmet = new AnimatedSprite(helmet);
			AnimatedSprite animatedChest = new AnimatedSprite(chest);
			AnimatedSprite animatedPants = new AnimatedSprite(pants);
			AnimatedSprite animatedBoots = new AnimatedSprite(boots);
			
			animatedSprites.put(HUMAN_BASE, animatedSprite);
			animatedSprites.put(PLATE_HELMET_ITEM, animatedHelmet);
			animatedSprites.put(PLATE_TORSO_ITEM, animatedChest);
			animatedSprites.put(PLATE_LEGS_ITEM, animatedPants);
			animatedSprites.put(PLATE_FEET_ITEM, animatedBoots);
			
			fireAnimationSprites = new StaticSprite[4];
			fireAnimationSprites[0] = new StaticSprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[1] = new StaticSprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[2] = new StaticSprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[3] = new StaticSprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);

		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sprites.");
		}
		
		try {
//			Font awtFont = new Font("Times New Roman", Font.BOLD, 12);
//			font = new TrueTypeFont(awtFont, false);
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/Lato-Regular.ttf");
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(DEBUG_FONT_SIZE); // set font size
			font = new TrueTypeFont(awtFont2, false);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}  
	}
}