package com.madbros.tileminer;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.madbros.tileminer.GameObjects.*;
import com.madbros.tileminer.GameStates.*;
import com.madbros.tileminer.Items.*;
import com.madbros.tileminer.LevelTypes.*;
import com.madbros.tileminer.Menus.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.Systems.AnimationSystem;
import com.madbros.tileminer.Systems.CollisionDetectionSystem;
import com.madbros.tileminer.Systems.RenderSystem;
import com.madbros.tileminer.Utils.Rect;

import static com.madbros.tileminer.Constants.*;

public class Game implements ApplicationListener {
	public static int renderWidth = (int)Math.ceil(Game.currentScreenSizeX * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int renderHeight = (int)Math.ceil(Game.currentScreenSizeY * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int pixelModifier = 1;
	public static int currentScreenSizeX = INITIAL_WINDOW_WIDTH;
	public static int currentScreenSizeY = INITIAL_WINDOW_HEIGHT;
	
	public static double oceanTally, mountainTally, desertTally, grasslandTally, forestTally, jungleTally, swampTally, taigaTally, tundraTally = 0;

	public static boolean spawnSet = false;
	public static int replaceableX = 0;
	public static int replaceableY = 0;
	public static boolean isSoundOn = true;
	public static boolean isMusicOn = true;
	public static GameState currentState;
	public static String locOfSavedGame = null;
	public static String gameFileName = null;
	public static boolean isNewGame = true;
	public static SpriteBatch batch;
	public static SpriteBatch particleBatch;
	public static Debugger debugger;
	public static GameMainMenu gameMainMenu;
	public static DebugMenu debugMenu;
	public static Level level;
	public static Hero hero;
	public static MobController mobController;
	public static SoundController soundController;
	public static MusicController musicController;
	public static CollectibleController collectibleController;
	public static NotificationController notificationController;
	public static ParticleEffectController particleEffectController;
	
	public static String currentLevel = OVERWORLD_FOLDER;
	public static SaveGame saveGame = new SaveGame();
	public static Inventory inventory;
	public static MiniMap map;
	public static RenderSystem renderSystem;
	public static AnimationSystem animationSystem;
	public static CollisionDetectionSystem collisionDetectionSystem;
	
	public static long gameStartTime;
	public static long timeSpentInPreviousSaves;
	
	public static float totalLoadingPoints;
	public static float currentLoadingPoints;
	
	public static OrthographicCamera camera;
	public static float zAngle;
	public static final float zSpeed = 15.0f;
	public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	public static Texture light;
	public static FrameBuffer fbo;
	
	
	
//	public static ParticleEffect death = new ParticleEffect();
//	
//	public static ParticleEffect p = new ParticleEffect();
	
	public static ShaderProgram defaultShader;
	public static ShaderProgram ambientShader;
	public static ShaderProgram lightShader;
	public static ShaderProgram finalShader;
	public static ShaderProgram currentShader;
	
	public static float ambientIntensity = 0.4f; //0.05
	public static Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f); //0.3f, 0.3f, 0.7f
	
	public static float lightTransparency = 1f;
	public static float lightTransparency2 = 1f;
	
	public static float ambientIntensity2 = 0.05f; //0.05
	public static Vector3 ambientColor2 = new Vector3(0.3f, 0.3f, 0.7f); //0.3f, 0.3f, 0.7f
	
/*#########################################################*/
	public static void closeInventory() {
		currentState = new MainState();
		hero.stop();
		inventory.close(hero);
		inventory.currentWorkSpace = 0;
		if(inventory.heldItem.id != 0) {
			Rect collectibleRect = new Rect(hero.absRect.x, hero.absRect.y, 16, 16);
			Game.collectibleController.add(inventory.heldItem.id, inventory.heldItem.sprite, collectibleRect, inventory.heldItem.stackSize, inventory.heldItem.uses);
			inventory.heldItem.stackSize = 0;
			inventory.heldItem = new NoItem();
		}
		
	}
	
	public static void toggleInventoryState() {
		//TODO if current state == Furnace/Cauldron/Chest
		if(currentState.type == State.INVENTORY) {
			closeInventory();
			
		} else {
			currentState = new InventoryState();
			hero.stop();
			inventory.open(hero);
			Game.inventory.craftingMenu.currentCraftableList = Game.inventory.craftingMenu.craftableList; 
		}
	}
	
	public static void toggleMainMenu() {
		if(gameMainMenu.menuIsActive) {
			gameMainMenu.menuIsActive = false;
			currentState = new MainState();
		} else {
			gameMainMenu = new GameMainMenu(batch);
			gameMainMenu.menuIsActive = true;
			currentState = new GameMainMenuState();
		}		
	}
	
	
	public static void toggleFurnaceState() {
		if(currentState.type == State.FURNACE) {
			closeInventory();
		} else {
			currentState = new FurnaceState();
			hero.stop();
			inventory.open(hero);
		}
	}
	
	public static void toggleCauldronState() {
		if(currentState.type == State.CAULDRON) {
			
			closeInventory();
		} else {
			currentState = new CauldronState();
			hero.stop();
			inventory.open(hero);
		}
	}
	
	public static void toggleChestState() {
		if(currentState.type == State.CHEST) {
			//Not sure why but this doesn't change state?  Could be a bug...
			inventory.chestOn = false;
			int x = inventory.currentInvBlockX;
			int y = inventory.currentInvBlockY;
			saveGame.saveChest(Game.inventory.invChest,x, y);
			
			saveGame.saveGame();
			level.saveCurrentChunks();
			
			closeInventory();
		} else {
			currentState = new ChestState();
			hero.stop();
			inventory.open(hero);
		}
	}
	
	public static void fullscreenToggle() {
		if(!Gdx.graphics.isFullscreen()) {
			Gdx.graphics.setDisplayMode(Game.currentScreenSizeX, Game.currentScreenSizeY, true);
		} else {
			Gdx.graphics.setDisplayMode(Game.currentScreenSizeX, Game.currentScreenSizeY, false);
		}
	}
//	public static void toggleLoadingState() {
//		if(currentState.type == State.LOADING) {
//			currentState = new MainState();
//		} else {
//			currentState = new LoadingState(batch);
//		}
//	}
	

	
//	public static void switchToMainState() {
//		currentState = new MainState();
//	}
	
	
	
	public static void createSavesFolderIfNecessary() {
		File f = new File(SAVE_LOC);
		if(!f.exists()) f.mkdir();
	}
	
	public static void createDefaultSaveGameIfNecessary() {
		createNewGameAtLoc(SAVE_LOC + "default/");
	}
	
	public static void createNewGameAtLoc(String loc) {
		createSavesFolderIfNecessary();
		locOfSavedGame = loc;
		
		File f = new File(loc);
		if(!f.exists()) f.mkdir();
		
		f = new File(Game.locOfSavedGame + CHUNKS_FOLDER);  
		if(!f.exists()) f.mkdir();
		f = new File(Game.locOfSavedGame + CHESTS_FOLDER);
		if(!f.exists()) f.mkdir();
		f = new File(Game.locOfSavedGame + FURNACES_FOLDER);
		if(!f.exists()) f.mkdir();
		//make other folders...
		//level = new Level();
		createNewLevel();
		hero = new Hero();
		mobController = new MobController();
		soundController = new SoundController();
		collectibleController = new CollectibleController();
		notificationController = new NotificationController();
		particleEffectController = new ParticleEffectController();
		musicController = new MusicController();
		inventory = new Inventory();
		//map = new MiniMap();
		renderSystem = new RenderSystem();
		animationSystem = new AnimationSystem();
		
		collisionDetectionSystem = new CollisionDetectionSystem();
//		level.loadGame();
//		isNewGame = false;
		
		//Game.currentState = new MainState();
	}
	
	public static void switchLevel() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Game.saveGame.saveGame();
					Game.level.saveCurrentChunks();
					Game.gameStartTime = Time.getTime();
					collectibleController = new CollectibleController();
					if(Game.currentLevel == OVERWORLD_FOLDER) {
						level = new Underground1();
					} else if (Game.currentLevel == UNDERGROUND_1_FOLDER) {
						level = new Overworld();	
					}
					SaveGameData saveData = saveGame.saveData();
					level.spawnX = saveData.heroX;
					level.spawnY = saveData.heroY;
					level.masterSpawnX = saveData.spawnX;
					level.masterSpawnY = saveData.spawnY;
					level.startChunkX = level.spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
					level.startChunkY = level.spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
					level.chunkRect = new Rect(level.startChunkX, level.startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
					
					level.activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
					level.currentChunk = new Block[CHUNK_SIZE][CHUNK_SIZE];
					
					File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel);
					if(!f.exists()) {
						Game.totalLoadingPoints = CHUNKS_LENGTH_TOTAL *CHUNKS_LENGTH_TOTAL +1;
						Game.currentLoadingPoints = 0;
						f.mkdir();
						for(int i = 0; i < CHUNKS_LENGTH_TOTAL; i++) {
							for(int j = 0; j < CHUNKS_LENGTH_TOTAL; j++) {
								level.createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, i, j);
								Game.currentLoadingPoints = Game.currentLoadingPoints+1;
							}
						}	
					} else {
						Game.totalLoadingPoints = 1;
						Game.currentLoadingPoints = 0;
					}
					for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
						for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
							level.loadChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, level.chunkRect.x + i, level.chunkRect.y + j);
							
						}
						
					}
					f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel + "Level.sv");
					if(f.exists()) {
						Game.saveGame.loadCurrentLevel();
					}
					Game.currentLoadingPoints = Game.currentLoadingPoints+1;
					gameStartTime = Time.getTime();		

					level.autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
					
					mobController = new MobController();		
				} catch (Exception e) {
				}

				// time to post on the main thread!
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						level.loadGame();
						level.finishLoading();
						
						int x = level.masterSpawnX/TILE_SIZE;
						int y = level.masterSpawnY/TILE_SIZE;
						int bX = x - (level.chunkRect.x * CHUNK_SIZE);
						int bY = y - (level.chunkRect.y * CHUNK_SIZE);
						if(Game.level.activeBlocks[bX][bY].absRect.detectCollision(Game.hero.absRect)) {
							Game.level.activeBlocks[bX][bY].layers[OBJECT_LAYER].deleteMe(bX, bY, level.activeBlocks);
						}
						
					}
				});
			}
		}).start();	
	}
	
	public static void createNewLevel() {
		Game.currentState = new LoadingState(Game.batch);
		Game.gameStartTime = Time.getTime();
		if(isNewGame == false) {
			
			SaveGame saveGame = new SaveGame();
			SaveGameData saveData = saveGame.saveData();
			if(saveData.currentLevel.equals(UNDERGROUND_1_FOLDER)) {
				level = new Underground1();
			} else {
				level = new Overworld();
			}
			//SaveGameData saveData = Game.saveGame.saveData();
			level.spawnX = saveData.heroX;
			level.spawnY = saveData.heroY;
			level.masterSpawnX = saveData.spawnX;
			level.masterSpawnY = saveData.spawnY;
			level.startChunkX = level.spawnX /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
			level.startChunkY = level.spawnY /(CHUNK_SIZE*TILE_SIZE) - (CHUNKS_IN_A_ROW /2);
			level.chunkRect = new Rect(level.startChunkX, level.startChunkY, CHUNKS_IN_A_ROW-1, CHUNKS_IN_A_ROW-1);
		} else {
			level = new Overworld();
		}
		level.activeBlocks = new Block[TILES_PER_ROW][TILES_PER_ROW];
		level.currentChunk = new Block[CHUNK_SIZE][CHUNK_SIZE];


		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					
				
					File f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel);
					if(!f.exists()) {
						Game.totalLoadingPoints = CHUNKS_LENGTH_TOTAL *CHUNKS_LENGTH_TOTAL +1;
						Game.currentLoadingPoints = 0;
						f.mkdir();
						for(int i = 0; i < CHUNKS_LENGTH_TOTAL; i++) {
							for(int j = 0; j < CHUNKS_LENGTH_TOTAL; j++) {
								level.createNewChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, i, j);
								Game.currentLoadingPoints = Game.currentLoadingPoints+1;
							}
						}	
					} else {
						Game.totalLoadingPoints = 1;
						Game.currentLoadingPoints = 0;
					}
					for(int i = 0; i < CHUNKS_IN_A_ROW; i++) {
						for(int j = 0; j < CHUNKS_IN_A_ROW; j++) {
							level.loadChunk(CHUNK_SIZE*i, CHUNK_SIZE*j, level.chunkRect.x + i, level.chunkRect.y + j);
							
						}
						
					}
					f = new File(Game.locOfSavedGame + CHUNKS_FOLDER + Game.currentLevel + "Level.sv");
					if(f.exists()) {
						Game.saveGame.loadCurrentLevel();
					}
					Game.currentLoadingPoints = Game.currentLoadingPoints+1;
					gameStartTime = Time.getTime();		

					level.autoTileNewArea(2, 2, TILES_PER_ROW-2, TILES_PER_ROW-2);
				} catch (Exception e) {
				}

				// time to post on the main thread!
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						level.loadGame();
						level.finishLoading();
						
					}
				});
			}
		}).start();	
		
	}
	
	public static int getCenterScreenX() {
		return (int)Math.floor(currentScreenSizeX/2);
	}
	
	public static int getCenterScreenY() {
		return (int)Math.floor(currentScreenSizeY/2);
	}


	public static ArrayList<DisplayMode> resolutions = new ArrayList<DisplayMode>();
	
	public static ArrayList<DisplayMode> getResolutions() {
		ArrayList<DisplayMode> tempResolutions = new ArrayList<DisplayMode>();
		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			
			for (int i=0;i<modes.length;i++) {
			    DisplayMode current = modes[i]; 
//			    if((float)current.getWidth()/(float)current.getHeight() == (float)Gdx.graphics.getDesktopDisplayMode().width/(float)Gdx.graphics.getDesktopDisplayMode().height
//			    	 && current.getWidth() <= 1440 && current.getHeight() <=900 ) {
			    if(current.isFullscreenCapable() 
			    		&& current.getWidth() >= 640 
			    		&& current.getHeight() >= 480
			    		&& current.getWidth() <= 1440 
			    		&& current.getHeight() <=900)	{
			    	tempResolutions.add(current);
//			    	System.out.println(current.getWidth() + "x" + current.getHeight() + "x" +
//			                        	current.getBitsPerPixel() + " " + current.getFrequency() + "Hz"+ current.isFullscreenCapable());
			    }
			}
		} catch(LWJGLException e) {
			throw new RuntimeException("Could not initiate LWJGL.", e);
		}
		Game.resolutions = tempResolutions;
		return tempResolutions;
	}
	
	@Override
	public void create() {

		ArrayList<DisplayMode> resolutions = getResolutions();

		gameStartTime = Time.getTime();
		timeSpentInPreviousSaves = 0;  //TODO set this on game load:)
		//p.load(Gdx.files.internal("data/Chunks.p"), Gdx.files.internal("data")); //files.internal loads from the "assets" folder
		//death.load(Gdx.files.internal("data/death.p"), Gdx.files.internal("data")); //files.internal loads from the "assets" folder
		
		int smallestX = resolutions.get(0).getWidth();
		int smallestY = resolutions.get(0).getHeight();
		for (int i=0;i<resolutions.size();i++) {
			if(smallestX > resolutions.get(i).getWidth()) {
				smallestX = resolutions.get(i).getWidth();
				smallestY = resolutions.get(i).getHeight();
				try {
					Display.setDisplayMode(resolutions.get(i));
				} catch (LWJGLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}


		currentScreenSizeX = smallestX;
		currentScreenSizeY = smallestY;
		camera= new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		

		camera.update();
		
		batch = new SpriteBatch();
		particleBatch = new SpriteBatch();
		new Sprites();
		new Constants();
		
		debugger = new Debugger();
		debugMenu = new DebugMenu(batch);
		gameMainMenu = new GameMainMenu(batch);
		

		
		final String vertexShader = new FileHandle("data/vertexShader.glsl").readString();
		final String defaultPixelShader = new FileHandle("data/defaultPixelShader.glsl").readString();
		final String ambientPixelShader = new FileHandle("data/ambientPixelShader.glsl").readString();
		final String lightPixelShader =  new FileHandle("data/lightPixelShader.glsl").readString();
		final String finalPixelShader =  new FileHandle("data/pixelShader.glsl").readString();
		
		ShaderProgram.pedantic = false;
		defaultShader = new ShaderProgram(vertexShader, defaultPixelShader);
		ambientShader = new ShaderProgram(vertexShader, ambientPixelShader);
		lightShader = new ShaderProgram(vertexShader, lightPixelShader);
		finalShader = new ShaderProgram(vertexShader, finalPixelShader);


		ambientShader.begin();
		ambientShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y,
				ambientColor.z, ambientIntensity);
		ambientShader.end();
		

		lightShader.begin();
		lightShader.setUniformi("u_lightmap", 1);
		lightShader.end();
		
		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y, ambientColor.z, ambientIntensity);
		finalShader.end();
		
		currentShader = finalShader;
		light = new Texture("data/light.png");
		
		currentState = new MainMenuState(batch);
//		currentState = new MainState(true);
	}
	
	public static void reShade(Vector3 color, float intensity) {
		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", color.x, color.y, color.z, intensity);
		finalShader.end();
	}

	public static void quit() {
		if(level != null) {
			Game.saveGame.saveGame();
			Game.level.saveCurrentChunks();
			musicController.music.dispose();
		}
		batch.dispose();
		if(fbo != null && level != null) {
			finalShader.dispose();
			lightShader.dispose();
			ambientShader.dispose();
			defaultShader.dispose();
			light.dispose();
			fbo.dispose();
			}
	}

	@Override
	public void dispose() {
		quit();
		Display.destroy();
		System.exit(0);
		
//		bitmapFont.dispose();
//		tilemap.dispose();
	}

//	@Override
	public void render() {
		currentState.update();
		
		currentState.render();
	}

	@Override
	public void resize(int width, int height) {
		currentState.resize(width, height);
	}

	public static void zoomIn() {
		camera.zoom -= 0.1f;
		camera.update();
		//reposition gui
	}
	
	public static void zoomOut() {
		camera.zoom += 0.1f;
		camera.update();
		//reposition gui
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {
	}
}
