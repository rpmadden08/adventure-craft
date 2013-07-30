package com.madbros.adventurecraft;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static com.madbros.adventurecraft.Constants.*;

public class Textures {
	public static Texture atlas;

	public static Sprite[] grassTextures;
	public static Sprite[] dirtTextures;
	public static Sprite[] treeTextures;
	public static Sprite[] waterTextures;
	
	public static Sprite[] characterAnimations;
	public static Sprite[] fireAnimationTextures;
	
	public static Sprite[][] menuTextures;
	public static Sprite cellTexture;
	public static Sprite selectTexture;
	
	public static Sprite grassSeedTexture;
	public static Sprite earthClumpTexture;
	public static Sprite sandClumpTexture;
	public static Sprite logTexture;
	public static Sprite plankTexture;
	public static Sprite swordTexture;
	public static Sprite shovelTexture;
	
	public static Sprite buttonTexture;
	public static Sprite pressedButtonTexture;
	
	//for debugging
	public static Sprite collisionDebugger;
	public static Sprite pixel;
	
	public static TrueTypeFont font;
	
	public Textures() {
		try {
			atlas = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas.png"), GL11.GL_NEAREST);
			
			/* texture = new Sprite(texture, x, y, width, height) */
			grassTextures = new Sprite[1];
			grassTextures[0] = new Sprite(atlas, 16, 0, TILE_SIZE, TILE_SIZE);
			
			dirtTextures = new Sprite[11];
			dirtTextures[0] = new Sprite(atlas, 0, 112, TILE_SIZE, TILE_SIZE);
			dirtTextures[1] = new Sprite(atlas, 32, 112, TILE_SIZE, TILE_SIZE);
			dirtTextures[2] = new Sprite(atlas, 0, 128, TILE_SIZE, TILE_SIZE);
			dirtTextures[3] = new Sprite(atlas, 16, 128, TILE_SIZE, TILE_SIZE);
			dirtTextures[4] = new Sprite(atlas, 32, 128, TILE_SIZE, TILE_SIZE);
			dirtTextures[5] = new Sprite(atlas, 0, 144, TILE_SIZE, TILE_SIZE);
			dirtTextures[6] = new Sprite(atlas, 16, 144, TILE_SIZE, TILE_SIZE);
			dirtTextures[7] = new Sprite(atlas, 32, 144, TILE_SIZE, TILE_SIZE);
			dirtTextures[8] = new Sprite(atlas, 0, 160, TILE_SIZE, TILE_SIZE);
			dirtTextures[9] = new Sprite(atlas, 16, 160, TILE_SIZE, TILE_SIZE);
			dirtTextures[10] = new Sprite(atlas, 32, 160, TILE_SIZE, TILE_SIZE);
			
			treeTextures = new Sprite[1];
			treeTextures[0] = new Sprite(atlas, 32, 64, TILE_SIZE, TILE_SIZE);
			
			waterTextures = new Sprite[11];
			waterTextures[0] = new Sprite(atlas, 48, 112, TILE_SIZE, TILE_SIZE);
			waterTextures[1] = new Sprite(atlas, 80, 112, TILE_SIZE, TILE_SIZE);
			waterTextures[2] = new Sprite(atlas, 48, 128, TILE_SIZE, TILE_SIZE);
			waterTextures[3] = new Sprite(atlas, 64, 128, TILE_SIZE, TILE_SIZE);
			waterTextures[4] = new Sprite(atlas, 80, 128, TILE_SIZE, TILE_SIZE);
			waterTextures[5] = new Sprite(atlas, 48, 144, TILE_SIZE, TILE_SIZE);
			waterTextures[6] = new Sprite(atlas, 64, 144, TILE_SIZE, TILE_SIZE);
			waterTextures[7] = new Sprite(atlas, 80, 144, TILE_SIZE, TILE_SIZE);
			waterTextures[8] = new Sprite(atlas, 48, 160, TILE_SIZE, TILE_SIZE);
			waterTextures[9] = new Sprite(atlas, 64, 160, TILE_SIZE, TILE_SIZE);
			waterTextures[10] = new Sprite(atlas, 80, 160, TILE_SIZE, TILE_SIZE);
			
			menuTextures = new Sprite[3][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					menuTextures[i][j] = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
													1024-INV_MENU_TILE_SIZE*3+j*INV_MENU_TILE_SIZE,
													INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
				}
			}
			
			cellTexture = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_CELL_SIZE, 1024-INV_CELL_SIZE, INV_CELL_SIZE, INV_CELL_SIZE);
			selectTexture = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_CELL_SIZE-4, 1024-INV_CELL_SIZE*2-4, INV_CELL_SIZE+4, INV_CELL_SIZE+4);
			
			buttonTexture = new Sprite(atlas, 1024-189, 1024-64, 66, 32);
			pressedButtonTexture = new Sprite(atlas, 1024-189, 1024-32, 66, 32);
			
			collisionDebugger = new Sprite(atlas, 336, 0, TILE_SIZE, TILE_SIZE);
			pixel = new Sprite(atlas, 1023, 0, 1, 1);
			
			characterAnimations = new Sprite[24];
			for(int i = 0; i < 24; i++) {
				characterAnimations[i] = new Sprite(atlas, 0 + CHARACTER_SIZE*i, 232, CHARACTER_SIZE, CHARACTER_SIZE);
			}
			
			fireAnimationTextures = new Sprite[5];
			fireAnimationTextures[0] = new Sprite(atlas, 0, 256, TILE_SIZE*4, TILE_SIZE*4);
			fireAnimationTextures[1] = new Sprite(atlas, 64, 256, TILE_SIZE*4, TILE_SIZE*4);
			fireAnimationTextures[2] = new Sprite(atlas, 128, 256, TILE_SIZE*4, TILE_SIZE*4);
			fireAnimationTextures[3] = new Sprite(atlas, 192, 256, TILE_SIZE*4, TILE_SIZE*4);
			fireAnimationTextures[4] = new Sprite(atlas, 256, 256, TILE_SIZE*4, TILE_SIZE*4);
			
			grassSeedTexture = new Sprite(atlas, TILE_SIZE*0, TILE_SIZE*2, TILE_SIZE, TILE_SIZE);
			earthClumpTexture = new Sprite(atlas, TILE_SIZE*1, TILE_SIZE*2, TILE_SIZE, TILE_SIZE);
			sandClumpTexture = new Sprite(atlas, TILE_SIZE*2, TILE_SIZE*2, TILE_SIZE, TILE_SIZE);
			logTexture = new Sprite(atlas, TILE_SIZE*3, TILE_SIZE*2, TILE_SIZE, TILE_SIZE);
			plankTexture = new Sprite(atlas, TILE_SIZE*4, TILE_SIZE*2, TILE_SIZE, TILE_SIZE);
			swordTexture = new Sprite(atlas, TILE_SIZE*0, TILE_SIZE*5, TILE_SIZE, TILE_SIZE);
			shovelTexture = new Sprite(atlas, TILE_SIZE*1, TILE_SIZE*5, TILE_SIZE, TILE_SIZE);
			
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
