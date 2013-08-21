package com.madbros.adventurecraft;

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

public class Textures {
	public static Texture atlas;
	public static Texture atlas2;

	public static StaticSprite darkDirtTexture;
	public static AnimatedSprite heroSprite;
	public static StaticSprite[] grassTextures;
	public static StaticSprite[] dirtTextures;
	public static StaticSprite[] treeTextures;
	public static StaticSprite[] treeLeafTextures;
	public static StaticSprite[] waterTextures;
	public static StaticSprite[] holeTextures;
	public static StaticSprite[] sandTextures;
	public static StaticSprite[] saplingTexture;
	
	public static StaticSprite[] fireAnimationTextures;
	
	public static StaticSprite[][] menuTextures;
	public static StaticSprite slotTexture;
	public static StaticSprite selectTexture;
	public static StaticSprite saplingItemTexture;

	
	public static StaticSprite grassSeedTexture;
	public static StaticSprite earthClumpTexture;
	public static StaticSprite sandClumpTexture;
	public static StaticSprite logTexture;
	public static StaticSprite plankTexture;
	public static StaticSprite swordTexture;
	public static StaticSprite shovelTexture;
	
	public static StaticSprite buttonTexture;
	public static StaticSprite pressedButtonTexture;
	
	//for debugging
	public static StaticSprite collisionDebugger;
	public static StaticSprite pixel;
	
	public static TrueTypeFont font;
	public static HashMap<String, StaticSprite> staticSprites = new HashMap<String, StaticSprite>();
	public static HashMap<String, AnimatedSprite> animatedSprites = new HashMap<String, AnimatedSprite>();
	public static HashMap<String, StaticSprite> spriteCollections = new HashMap<String, StaticSprite>();
	
	public Textures() {
		try {
			atlas = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"), GL11.GL_NEAREST);
			//atlas2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas2.png"), GL11.GL_NEAREST);
			
			String[] names = {"hero", "baseDirt"};	//Phase this out entirely...
			
			try {
				JSONParser parser = new JSONParser();
				Object f = parser.parse(new FileReader("res/atlas.json"));
				JSONObject obj = (JSONObject) f;
				
				for(int i = 0; i < obj.size() - 1; i++) {
					JSONObject sprite = (JSONObject) obj.get(names[i]);
					
					if(sprite.get("type").equals("static")) {
						int x = Integer.parseInt((String)sprite.get("x"));
						int y = Integer.parseInt((String)sprite.get("y"));
						int width = Integer.parseInt((String)sprite.get("width"));
						int height = Integer.parseInt((String)sprite.get("height"));
						
						staticSprites.put(names[i], new StaticSprite(atlas, x, y, width, height));
					}
					
					else if(sprite.get("type").equals("animated")) {
						JSONArray animations = (JSONArray) sprite.get("animations");
						
						Animation[] spriteAnimations = new Animation[animations.size()];
						String[] animationNames = new String[animations.size()];
						
						for(int j = 0; j < animations.size(); j++) {
							JSONObject animation = (JSONObject) animations.get(j);
							String n = (String) animation.get("name");
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
							
							animationNames[j] = n;
							spriteAnimations[j] = new Animation(sprites, frameTimesInMilliseconds);
						}
						
						animatedSprites.put(names[i], new AnimatedSprite(animationNames, spriteAnimations));
					}
					
					
				}
				
//			JSONArray tiles = (JSONArray) jO.get("tiles" + x + "-" + y);
//			long absX = (Long) jO.get("x" + x + "-" + y);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
//			darkDirtTexture = new StaticSprite[]{new StaticSprite(atlas, 0, 0, TEXTURE_SIZE, TEXTURE_SIZE)};
			
			grassTextures = new StaticSprite[13]; Point pGrass = new Point(1*TEXTURE_SIZE, 0);	//where does the grass texture begin
			dirtTextures = new StaticSprite[13]; Point pDirt = new Point(6*TEXTURE_SIZE, 0);
			waterTextures = new StaticSprite[13]; Point pWater = new Point(11*TEXTURE_SIZE, 0);
			holeTextures = new StaticSprite[13]; Point pHole = new Point(21*TEXTURE_SIZE, 0);
			sandTextures = new StaticSprite[13]; Point pSand = new Point(26*TEXTURE_SIZE, 0);
			
			
			
			StaticSprite[][] t = {grassTextures, dirtTextures, waterTextures, holeTextures, sandTextures};
			Point[] pts = {pGrass, pDirt, pWater, pHole, pSand};
			
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
			
			saplingTexture = new StaticSprite[]{new StaticSprite(atlas, 96, 608, 64, 64)};
			
			treeLeafTextures = new StaticSprite[6];
			treeLeafTextures[0] = new StaticSprite(atlas, 16, 608, 64, 64);
			treeLeafTextures[1] = new StaticSprite(atlas, -48, 608, 64, 64);
			treeLeafTextures[2] = new StaticSprite(atlas, -48, 544, 64, 64);
			treeLeafTextures[3] = new StaticSprite(atlas, 16, 544, 64, 64);
			treeLeafTextures[4] = new StaticSprite(atlas, 80, 544, 64, 64);
			treeLeafTextures[5] = new StaticSprite(atlas, 80, 608, 64, 64);
			
			treeTextures = new StaticSprite[1];
			treeTextures[0] = new StaticSprite(atlas, 16, 672, 64, 64);
			
			menuTextures = new StaticSprite[3][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					menuTextures[i][j] = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
													1024-INV_MENU_TILE_SIZE*3+j*INV_MENU_TILE_SIZE,
													INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
				}
			}
			
			buttonTexture = new StaticSprite(atlas, 1024-189, 1024-64, 66, 32);
			pressedButtonTexture = new StaticSprite(atlas, 1024-189, 1024-32, 66, 32);
			
			slotTexture = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_SLOT_SIZE, 1024-INV_SLOT_SIZE, INV_SLOT_SIZE, INV_SLOT_SIZE);
			selectTexture = new StaticSprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_SLOT_SIZE-4, 1024-INV_SLOT_SIZE*2-4, INV_SLOT_SIZE+4, INV_SLOT_SIZE+4);
			
			
			collisionDebugger = new StaticSprite(atlas, 0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
			pixel = new StaticSprite(atlas, 1023, 0, 1, 1);
			
			String[] animationNames = {"standDown", "standRight", "standUp", "standLeft", "walkDown", "walkRight", "walkUp", "walkLeft"};
			Animation[] animations = new Animation[animationNames.length];
			
			int sX = 0; int sY = 96;
			k = 0;
			for(int i = 0; i < 4; i ++) {
				StaticSprite[] frames = new StaticSprite[9];
				for(int j = 0; j < 9; j++) {
					frames[j] = new StaticSprite(atlas, sX + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
					k++;
				}
				animations[i] = new Animation(frames[0], 80);
				animations[i+4] = new Animation(frames, 80);
			}
			
			animatedSprites.put("heroTemp", new AnimatedSprite(animationNames, animations));
			
			fireAnimationTextures = new StaticSprite[4];
			fireAnimationTextures[0] = new StaticSprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[1] = new StaticSprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[2] = new StaticSprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[3] = new StaticSprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);

			
			grassSeedTexture = new StaticSprite(atlas, TEXTURE_SIZE*0, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			earthClumpTexture = new StaticSprite(atlas, TEXTURE_SIZE*1, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			sandClumpTexture = new StaticSprite(atlas, TEXTURE_SIZE*2, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			logTexture = new StaticSprite(atlas, TEXTURE_SIZE*3, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			plankTexture = new StaticSprite(atlas, TEXTURE_SIZE*4, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			saplingItemTexture = new StaticSprite(atlas, 112, 624, TEXTURE_SIZE, TEXTURE_SIZE);
			
			swordTexture = new StaticSprite(atlas, TEXTURE_SIZE*0, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			shovelTexture = new StaticSprite(atlas, TEXTURE_SIZE*1, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load textures.");
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