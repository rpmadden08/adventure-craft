package com.madbros.adventurecraft;

import java.awt.Font;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.madbros.adventurecraft.Utils.Point;

import static com.madbros.adventurecraft.Constants.*;

public class Textures {
	public static Texture atlas;
	public static Texture atlas2;

	public static Sprite[] darkDirtTexture;
	public static Sprite[] grassTextures;
	public static Sprite[] dirtTextures;
	public static Sprite[] treeTextures;
	public static Sprite[] treeLeafTextures;
	public static Sprite[] waterTextures;
	public static Sprite[] holeTextures;
	public static Sprite[] sandTextures;
	public static Sprite[] saplingTexture;
	
	public static Sprite[] characterAnimations;
	public static Sprite[] fireAnimationTextures;
	
	public static Sprite[][] menuTextures;
	public static Sprite cellTexture;
	public static Sprite selectTexture;
	public static Sprite saplingItemTexture;

	
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
			//atlas2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/atlas2.png"), GL11.GL_NEAREST);
			
			/*
			* To create a new terrain texture:
			* Set texture array size, set starting point, add texture to t array, add starting point to pts array
			*/
			darkDirtTexture = new Sprite[]{new Sprite(atlas, 0, 0, TEXTURE_SIZE, TEXTURE_SIZE)};
			
			grassTextures = new Sprite[13]; Point pGrass = new Point(1*TEXTURE_SIZE, 0);	//where does the grass texture begin
			dirtTextures = new Sprite[13]; Point pDirt = new Point(6*TEXTURE_SIZE, 0);
			waterTextures = new Sprite[13]; Point pWater = new Point(11*TEXTURE_SIZE, 0);
			holeTextures = new Sprite[13]; Point pHole = new Point(21*TEXTURE_SIZE, 0);
			sandTextures = new Sprite[13]; Point pSand = new Point(26*TEXTURE_SIZE, 0);
			
			
			
			Sprite[][] t = {grassTextures, dirtTextures, waterTextures, holeTextures, sandTextures};
			Point[] pts = {pGrass, pDirt, pWater, pHole, pSand};
			
			int k = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					for(int tex = 0; tex < pts.length; tex++) {
						t[tex][k] = new Sprite(atlas, pts[tex].x+j*TEXTURE_SIZE, pts[tex].y+i*TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
					}
					k++;
				}
			}
			
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					for(int tex = 0; tex < pts.length; tex++) {
						t[tex][k] = new Sprite(atlas, pts[tex].x+TEXTURE_SIZE*3+j*TEXTURE_SIZE, pts[tex].y+i*TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
					}
					k++;
				}
			}
			
			saplingTexture = new Sprite[]{new Sprite(atlas, 224, 608, 64, 64)};
			
			treeLeafTextures = new Sprite[6];
			treeLeafTextures[0] = new Sprite(atlas, 16, 608, 64, 64);
			treeLeafTextures[1] = new Sprite(atlas, -48, 608, 64, 64);
			treeLeafTextures[2] = new Sprite(atlas, -48, 544, 64, 64);
			treeLeafTextures[3] = new Sprite(atlas, 16, 544, 64, 64);
			treeLeafTextures[4] = new Sprite(atlas, 80, 544, 64, 64);
			treeLeafTextures[5] = new Sprite(atlas, 80, 608, 64, 64);
			
			treeTextures = new Sprite[1];
			treeTextures[0] = new Sprite(atlas, 16, 672, 64, 64);
			
			menuTextures = new Sprite[3][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					menuTextures[i][j] = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3+i*INV_MENU_TILE_SIZE,
													1024-INV_MENU_TILE_SIZE*3+j*INV_MENU_TILE_SIZE,
													INV_MENU_TILE_SIZE, INV_MENU_TILE_SIZE);
				}
			}
			
			buttonTexture = new Sprite(atlas, 1024-189, 1024-64, 66, 32);
			pressedButtonTexture = new Sprite(atlas, 1024-189, 1024-32, 66, 32);
			
			cellTexture = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_CELL_SIZE, 1024-INV_CELL_SIZE, INV_CELL_SIZE, INV_CELL_SIZE);
			selectTexture = new Sprite(atlas, 1024-INV_MENU_TILE_SIZE*3-INV_CELL_SIZE-4, 1024-INV_CELL_SIZE*2-4, INV_CELL_SIZE+4, INV_CELL_SIZE+4);
			
			
			collisionDebugger = new Sprite(atlas, 0, 32, TEXTURE_SIZE, TEXTURE_SIZE);
			pixel = new Sprite(atlas, 1023, 0, 1, 1);
			
			characterAnimations = new Sprite[36];

			int sX = 0; int sY = 96;
			k = 0;
			for(int i = 0; i < 4; i ++) {
				for(int j = 0; j < 9; j++) {
					characterAnimations[k] = new Sprite(atlas, sX + CHARACTER_SIZE * j, sY + CHARACTER_SIZE * i, CHARACTER_SIZE, CHARACTER_SIZE);
					k++;
				}
			}
			
			fireAnimationTextures = new Sprite[4];
			fireAnimationTextures[0] = new Sprite(atlas, 0, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[1] = new Sprite(atlas, 64, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[2] = new Sprite(atlas, 128, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);
			fireAnimationTextures[3] = new Sprite(atlas, 192, 416, TEXTURE_SIZE*4, TEXTURE_SIZE*4);

			
			grassSeedTexture = new Sprite(atlas, TEXTURE_SIZE*0, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			earthClumpTexture = new Sprite(atlas, TEXTURE_SIZE*1, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			sandClumpTexture = new Sprite(atlas, TEXTURE_SIZE*2, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			logTexture = new Sprite(atlas, TEXTURE_SIZE*3, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			plankTexture = new Sprite(atlas, TEXTURE_SIZE*4, 480, TEXTURE_SIZE, TEXTURE_SIZE);
			saplingItemTexture = new Sprite(atlas, 240, 624, TEXTURE_SIZE, TEXTURE_SIZE);
			
			swordTexture = new Sprite(atlas, TEXTURE_SIZE*0, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			shovelTexture = new Sprite(atlas, TEXTURE_SIZE*1, 512, TEXTURE_SIZE, TEXTURE_SIZE);
			
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