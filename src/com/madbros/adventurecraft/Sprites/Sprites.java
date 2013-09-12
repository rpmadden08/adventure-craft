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

import com.madbros.adventurecraft.Utils.Point;

import static com.madbros.adventurecraft.Constants.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Sprites {
	public static Texture atlas;
	public static Texture atlas2;
	
	public static final int DARK_DIRT = 0;
	public static final int HUMAN_BASE = 1;
	public static final int IRON_HELMET = 2;
	public static final int IRON_ARMOR = 3;
	public static final int IRON_LEGGINGS = 4;
	public static final int IRON_BOOTS = 5;

	public static AnimatedSprite[] waterSprites;
	public static StaticSprite[] treeLeafSprites;
	public static StaticSprite[] grassSprites;
	public static StaticSprite[] dirtSprites;
	public static StaticSprite[] treeSprites;
	public static StaticSprite[] holeSprites;
	public static StaticSprite[] sandSprites;
	public static StaticSprite[] saplingSprite;
	public static Sprite[] dirtMountainBottomSprites;
	public static Sprite[] dirtMountainTopSprites;
	
	public static AnimatedSprite[] helmetAnimations;
	public static AnimatedSprite[] armorAnimations;
	public static AnimatedSprite[] leggingsAnimations;
	public static AnimatedSprite[] feetAnimations;
	
	public static StaticSprite[] fireAnimationSprites;
	
	public static StaticSprite[] menuSprites1;
	public static StaticSprite[] menuSprites2;
	public static StaticSprite[] menuSprites3;
	public static StaticSprite slotSprite;
	public static StaticSprite selectSprite;
	public static StaticSprite saplingItemSprite;

	
	public static StaticSprite grassSeedSprite;
	public static StaticSprite earthClumpSprite;
	public static StaticSprite sandClumpSprite;
	public static StaticSprite logSprite;
	public static StaticSprite plankSprite;
	public static StaticSprite swordSprite;
	public static StaticSprite shovelSprite;
	public static StaticSprite ironHelmetSprite;
	public static StaticSprite ironArmorSprite;
	public static StaticSprite ironLeggingsSprite;
	public static StaticSprite ironBootsSprite;
	
	public static StaticSprite buttonSprite;
	public static StaticSprite pressedButtonSprite;
	
	//for debugging
	public static StaticSprite collisionDebugger;
	public static StaticSprite pixel;
	
	public static TrueTypeFont font;
	public static HashMap<Integer, Sprite> sprites = new HashMap<Integer, Sprite>();
	public static HashMap<Integer, Sprite[]> spriteCollections = new HashMap<Integer, Sprite[]>();
	public static HashMap<Integer, AnimatedSprite> animatedSprites = new HashMap<Integer, AnimatedSprite>();
	public static HashMap<Integer, AnimatedSprite[]> animatedSpriteCollections = new HashMap<Integer, AnimatedSprite[]>();
//	public static HashMap<Integer, CompoundAnimatedSprite> compoundAnimatedSprite = new HashMap<Integer, CompoundAnimatedSprite>();
	
	//Sprite hash (sprites)
	//ArrayList<Sprite> hash (sprite collections)
	
	public Sprites() {
		try {
			atlas = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"), GL11.GL_NEAREST);
			//atlas2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas2.png"), GL11.GL_NEAREST);
			
			int[] ids = {DARK_DIRT, HUMAN_BASE, 2};

			//not finished yet...
			try {
				JSONParser parser = new JSONParser();
				Object f = parser.parse(new FileReader("res/atlas.json"));
				JSONObject obj = (JSONObject) f;
				
				for(int i = 0; i < obj.size() - 1; i++) {
					JSONObject sprite = (JSONObject) obj.get(Integer.toString(ids[i]));
					
					if(sprite.get("type").equals("static")) {
						JSONArray sprts = (JSONArray) sprite.get("sprites");
						
						if(sprts.size() == 1) {
							for(Object sprt : sprts) {
								JSONObject staticSprite = (JSONObject) sprt;
								int x = Integer.parseInt((String)staticSprite.get("x"));
								int y = Integer.parseInt((String)staticSprite.get("y"));
								int width = Integer.parseInt((String)staticSprite.get("width"));
								int height = Integer.parseInt((String)staticSprite.get("height"));
								int leftRenderOffset = Integer.parseInt((String)staticSprite.get("leftRenderOffset"));
								int topRenderOffset = Integer.parseInt((String)staticSprite.get("topRenderOffset"));
								
								sprites.put(ids[i], new StaticSprite(atlas, x, y, width, height, leftRenderOffset, topRenderOffset));
							}
						} else {
							StaticSprite[] list = new StaticSprite[sprts.size()];
							for(int j = 0; j < sprts.size(); j++) {
								JSONObject staticSprite = (JSONObject) sprts.get(j);
								int x = Integer.parseInt((String)staticSprite.get("x"));
								int y = Integer.parseInt((String)staticSprite.get("y"));
								int width = Integer.parseInt((String)staticSprite.get("width"));
								int height = Integer.parseInt((String)staticSprite.get("height"));
								int leftRenderOffset = Integer.parseInt((String)staticSprite.get("leftRenderOffset"));
								int topRenderOffset = Integer.parseInt((String)staticSprite.get("topRenderOffset"));
								
								list[i] = new StaticSprite(atlas, x, y, width, height, leftRenderOffset, topRenderOffset);
								spriteCollections.put(ids[i], list);
							}
						}
					}
					
					else if(sprite.get("type").equals("animated")) {
						JSONArray animations = (JSONArray) sprite.get("animations");
						
						Animation[] spriteAnimations = new Animation[animations.size()];
						int[] animationIds = new int[animations.size()];
						
						for(int j = 0; j < animations.size(); j++) {
							JSONObject animation = (JSONObject) animations.get(j);
							int id = Integer.parseInt((String)animation.get("id"));
							JSONArray animationArray = (JSONArray) animation.get("animation");
							
							StaticSprite[] sprites = new StaticSprite[animationArray.size()];
							int[] frameTimesInMilliseconds = new int[animationArray.size()];
		
							for(int k = 0; k < animationArray.size(); k++) {
								JSONObject frame = (JSONObject) animationArray.get(k);
								int x = Integer.parseInt((String)frame.get("x"));
								int y = Integer.parseInt((String)frame.get("y"));
								int width = Integer.parseInt((String)frame.get("width"));
								int height = Integer.parseInt((String)frame.get("height"));
								int frameTimeInMilliseconds = Integer.parseInt((String)frame.get("frameTimeInMilliseconds"));
								
								sprites[k] = new StaticSprite(atlas, x, y, width, height);
								frameTimesInMilliseconds[k] = frameTimeInMilliseconds;
							}
							
							animationIds[j] = id;
							spriteAnimations[j] = new Animation(sprites, frameTimesInMilliseconds, animationIds[j]);
						}
						
						
						animatedSprites.put(ids[i], new AnimatedSprite(spriteAnimations));
					}
					
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			grassSprites = new StaticSprite[13]; Point pGrass = new Point(1*TEXTURE_SIZE, 0);	//where does the grass texture begin
			dirtSprites = new StaticSprite[13]; Point pDirt = new Point(6*TEXTURE_SIZE, 0);
			holeSprites = new StaticSprite[13]; Point pHole = new Point(21*TEXTURE_SIZE, 0);
			sandSprites = new StaticSprite[13]; Point pSand = new Point(26*TEXTURE_SIZE, 0);
			StaticSprite[] waterSprites1 = new StaticSprite[13]; Point pWater = new Point(11*TEXTURE_SIZE, 0);
			StaticSprite[] waterSprites2 = new StaticSprite[13]; Point pAltWater = new Point(26*TEXTURE_SIZE, 13*TEXTURE_SIZE);
			dirtMountainBottomSprites = new StaticSprite[13]; Point pMountainBottom = new Point(0, 23*TEXTURE_SIZE);
			dirtMountainTopSprites = new StaticSprite[13]; Point pMountainTop = new Point(5*TEXTURE_SIZE, 23*TEXTURE_SIZE);

			Sprite[][] t = {grassSprites, dirtSprites, waterSprites1, holeSprites, sandSprites, waterSprites2, dirtMountainBottomSprites, dirtMountainTopSprites};
			Point[] pts = {pGrass, pDirt, pWater, pHole, pSand, pAltWater, pMountainBottom, pMountainTop};
			
			int k = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					for(int tex = 0; tex < pts.length; tex++) {
						t[tex][k] = new StaticSprite(atlas, pts[tex].x+j*TEXTURE_SIZE, pts[tex].y+i*TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
					}
					k++;
				}
			}
			
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					for(int tex = 0; tex < pts.length; tex++) {
						t[tex][k] = new StaticSprite(atlas, pts[tex].x+TEXTURE_SIZE*3+j*TEXTURE_SIZE, pts[tex].y+i*TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
					}
					k++;
				}
			}
			
			waterSprites = new AnimatedSprite[13];
			for(int i = 0; i < waterSprites.length; i++) {
//				waterSprites1[i].id = 0;
//				waterSprites2[i].id = 1;
				Animation waterAnimation = new Animation(new StaticSprite[]{waterSprites1[i], waterSprites2[i]}, 800, i);
				waterSprites[i] = new AnimatedSprite(waterAnimation);
			}
			
//			sprites.put(0, waterSprites);
			
			saplingSprite = new StaticSprite[]{new StaticSprite(atlas, 96, 608, 64, 64)};
			
			treeLeafSprites = new StaticSprite[6];
			treeLeafSprites[0] = new StaticSprite(atlas, 16, 608, 64, 31);
			treeLeafSprites[1] = new StaticSprite(atlas, 1, 608, 15, 25, 49, 0);
			treeLeafSprites[2] = new StaticSprite(atlas, 2, 560, 14, 48, 50, 16);
			treeLeafSprites[3] = new StaticSprite(atlas, 16, 560, 64, 48, 0, 16);
			treeLeafSprites[4] = new StaticSprite(atlas, 80, 560, 13, 48, 0, 16); 
			treeLeafSprites[5] = new StaticSprite(atlas, 80, 608, 14, 26);
			
			treeSprites = new StaticSprite[1];
			treeSprites[0] = new StaticSprite(atlas, 16, 672, 64, 64);
			
			menuSprites1 = new StaticSprite[3]; menuSprites2 = new StaticSprite[3]; menuSprites3 = new StaticSprite[3];
			for(int i = 0; i < 3; i++) {
				menuSprites1[i] = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
											1024-INV_MENU_TILE_SIZE*3+0*INV_MENU_TILE_SIZE,
											INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
			}
			
			for(int i = 0; i < 3; i++) {
				menuSprites2[i] = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
											1024-INV_MENU_TILE_SIZE*3+1*INV_MENU_TILE_SIZE,
											INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
			}
			
			for(int i = 0; i < 3; i++) {
				menuSprites3[i] =new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
											1024-INV_MENU_TILE_SIZE*3+2*INV_MENU_TILE_SIZE,
											INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
			}
			
			buttonSprite = new StaticSprite(atlas, 1024-189, 1024-64, 66, 32);
			pressedButtonSprite = new StaticSprite(atlas, 1024-189, 1024-32, 66, 32);
			
			slotSprite = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_SLOT_SIZE, 1024-INV_SLOT_SIZE, INV_SLOT_SIZE, INV_SLOT_SIZE);
			selectSprite = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_SLOT_SIZE-4, 1024-INV_SLOT_SIZE*2-4, INV_SLOT_SIZE+4, INV_SLOT_SIZE+4);
			
			
			collisionDebugger = new StaticSprite(atlas, 0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
			pixel = new StaticSprite(atlas, 1023, 0, 1, 1);
			
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
						animations[i] = new Animation(new StaticSprite(atlas, sX + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 130, animationIds[i]);
						helmet[i] = new Animation(new StaticSprite(atlas, sX+192 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 130, animationIds[i]);
						chest[i] = new Animation(new StaticSprite(atlas, sX+384 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 130, animationIds[i]);
						pants[i] = new Animation(new StaticSprite(atlas, sX+576 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 130, animationIds[i]);
						boots[i] = new Animation(new StaticSprite(atlas, sX+768 + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE), 130, animationIds[i]);
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
				animations[i+4] = new Animation(frames, 100, animationIds[i+4]);
				helmet[i+4] = new Animation(helmetFrames, 100, animationIds[i+4]);
				chest[i+4] = new Animation(chestFrames, 100, animationIds[i+4]);
				pants[i+4] = new Animation(pantFrames, 100, animationIds[i+4]);
				boots[i+4] = new Animation(bootFrames, 100, animationIds[i+4]);
			}
			
			AnimatedSprite animatedSprite = new AnimatedSprite(animations);
			AnimatedSprite animatedHelmet = new AnimatedSprite(helmet);
			AnimatedSprite animatedChest = new AnimatedSprite(chest);
			AnimatedSprite animatedPants = new AnimatedSprite(pants);
			AnimatedSprite animatedBoots = new AnimatedSprite(boots);
			
			animatedSprites.put(HUMAN_BASE, animatedSprite);
			animatedSprites.put(IRON_HELMET, animatedHelmet);
			animatedSprites.put(IRON_ARMOR, animatedChest);
			animatedSprites.put(IRON_LEGGINGS, animatedPants);
			animatedSprites.put(IRON_BOOTS, animatedBoots);
			
			fireAnimationSprites = new StaticSprite[4];
			fireAnimationSprites[0] = new StaticSprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[1] = new StaticSprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[2] = new StaticSprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationSprites[3] = new StaticSprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);

			
			grassSeedSprite = new StaticSprite(atlas, TEXTURE_SIZE*0, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			earthClumpSprite = new StaticSprite(atlas, TEXTURE_SIZE*1, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			sandClumpSprite = new StaticSprite(atlas, TEXTURE_SIZE*2, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			logSprite = new StaticSprite(atlas, TEXTURE_SIZE*3, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			plankSprite = new StaticSprite(atlas, TEXTURE_SIZE*4, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			
			saplingItemSprite = new StaticSprite(atlas, 234, 624, TEXTURE_SIZE, TEXTURE_SIZE);
			ironHelmetSprite = new StaticSprite(atlas, TEXTURE_SIZE*5, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			ironArmorSprite = new StaticSprite(atlas, TEXTURE_SIZE*6, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			ironLeggingsSprite = new StaticSprite(atlas, TEXTURE_SIZE*7, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			ironBootsSprite = new StaticSprite(atlas, TEXTURE_SIZE*8, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			
			swordSprite = new StaticSprite(atlas, TEXTURE_SIZE*0, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			shovelSprite = new StaticSprite(atlas, TEXTURE_SIZE*1, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			
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