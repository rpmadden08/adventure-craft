package com.madbros.adventurecraft;

import java.io.File;

import com.madbros.adventurecraft.Items.*;
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
import com.madbros.adventurecraft.GameObjects.*;
import com.madbros.adventurecraft.GameStates.*;
import com.madbros.adventurecraft.Menus.*;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.Systems.AnimationSystem;
import com.madbros.adventurecraft.Systems.CollisionDetectionSystem;
import com.madbros.adventurecraft.Systems.RenderSystem;
import com.madbros.adventurecraft.Utils.Rect;

import static com.madbros.adventurecraft.Constants.*;

public class Game implements ApplicationListener {
	public static int renderWidth = (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int renderHeight = (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int pixelModifier = 1;
	public static int currentScreenSizeX = INITIAL_WINDOW_WIDTH;
	public static int currentScreenSizeY = INITIAL_WINDOW_HEIGHT;
	
	public static GameState currentState;
	public static String locOfSavedGame = null;
	
	public static SpriteBatch batch;
	public static SpriteBatch particleBatch;
	public static Debugger debugger;
	public static DebugMenu debugMenu;
	public static Level level;
	public static Hero hero;
	public static MobController mobController;
	public static SoundController soundController;
	public static MusicController musicController;
	public static CollectibleController collectibleController;
	public static Inventory inventory;
	public static MiniMap map;
	public static RenderSystem renderSystem;
	public static AnimationSystem animationSystem;
	public static CollisionDetectionSystem collisionDetectionSystem;
	
	public static OrthographicCamera camera;
	public static float zAngle;
	public static final float zSpeed = 15.0f;
	public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	public static Texture light;
	public static FrameBuffer fbo;
	
	public static ParticleEffect death = new ParticleEffect();
	
	public static ParticleEffect p = new ParticleEffect();
	
	public static ShaderProgram defaultShader;
	public static ShaderProgram ambientShader;
	public static ShaderProgram lightShader;
	public static ShaderProgram finalShader;
	public static ShaderProgram currentShader;
	
	public static float ambientIntensity = 0.05f; //0.05
	public static Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f); //0.3f, 0.3f, 0.7f
	
	public static float lightTransparency = 1f;
	public static float lightTransparency2 = 1f;
	
	public static float ambientIntensity2 = 0.05f; //0.05
	public static Vector3 ambientColor2 = new Vector3(0.3f, 0.3f, 0.7f); //0.3f, 0.3f, 0.7f
	
/*#########################################################*/
	public static void toggleInventoryState() {
		if(currentState.type == State.INVENTORY) {
			inventory.craftingTableOn = false;
			currentState = new MainState();
			hero.stop();
			inventory.close(hero);
			if(inventory.heldItem.id != 0) {
				Rect collectibleRect = new Rect(hero.absRect.x, hero.absRect.y, 16, 16);
				Game.collectibleController.add(inventory.heldItem.id, inventory.heldItem.sprite, collectibleRect, inventory.heldItem.stackSize);
				inventory.heldItem.stackSize = 0;
				inventory.heldItem = new NoItem();
				
			}
		} else {
			//StandardInventory standardInventory = new StandardInventory();
			currentState = new InventoryState();
			hero.stop();
			inventory.open(hero);
		}
	}
	
	public static void toggleInventoryTableState() {
		if(currentState.type == State.TABLE_INVENTORY) {
			//inventory.craftingTableOn = false;
			currentState = new MainState();
			hero.stop();
			inventory.close(hero);
			if(inventory.heldItem.id != 0) {
				Rect collectibleRect = new Rect(hero.absRect.x, hero.absRect.y, 16, 16);
				Game.collectibleController.add(inventory.heldItem.id, inventory.heldItem.sprite, collectibleRect, inventory.heldItem.stackSize);
				inventory.heldItem.stackSize = 0;
				inventory.heldItem = new NoItem();
			}
		} else {
			currentState = new InventoryState();
			hero.stop();
			inventory.open(hero);
		}
	}
	
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
		//make other folders...
		
		level = new Level();
		hero = new Hero();
		mobController = new MobController();
		soundController = new SoundController();
		collectibleController = new CollectibleController();
		musicController = new MusicController();
		inventory = new Inventory();
		map = new MiniMap();
		renderSystem = new RenderSystem();
		animationSystem = new AnimationSystem();
		
		collisionDetectionSystem = new CollisionDetectionSystem();
		
		Game.currentState = new MainState();
	}
	
	public static int getCenterScreenX() {
		return (int)Math.floor(currentScreenSizeX/2);
	}
	
	public static int getCenterScreenY() {
		return (int)Math.floor(currentScreenSizeY/2);
	}

	@Override
	public void create() {
		
		p.load(Gdx.files.internal("data/Chunks.p"), Gdx.files.internal("data")); //files.internal loads from the "assets" folder
		death.load(Gdx.files.internal("data/death.p"), Gdx.files.internal("data")); //files.internal loads from the "assets" folder
		
		camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		particleBatch = new SpriteBatch();
		new Sprites();
		new Constants();
		
		debugger = new Debugger();
		debugMenu = new DebugMenu(batch);
		
//		currentState = new MainMenuState(batch);
		currentState = new MainState(true);
		
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
	}
	
	public static void reShade(Vector3 color, float intensity) {
		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", color.x, color.y, color.z, intensity);
		finalShader.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		finalShader.dispose();
		lightShader.dispose();
		ambientShader.dispose();
		defaultShader.dispose();
		light.dispose();
		fbo.dispose();
		musicController.music.dispose();
//		bitmapFont.dispose();
//		tilemap.dispose();
	}

	@Override
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
