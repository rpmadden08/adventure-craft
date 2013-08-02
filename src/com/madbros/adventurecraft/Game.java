package com.madbros.adventurecraft;
//I'm a git master!aslkdfjas;ldkfj
import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import com.madbros.adventurecraft.GameStates.*;
import com.madbros.adventurecraft.Menus.*;

import static org.lwjgl.opengl.GL11.*;

import static com.madbros.adventurecraft.Constants.*;

public class Game {
	public static int renderWidth = (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int renderHeight = (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int pixelModifier = 1;
	public static int centerScreenX = (int)Math.floor(INITIAL_WINDOW_WIDTH/2);	//640/2
	public static int centerScreenY = (int)Math.floor(INITIAL_WINDOW_HEIGHT/2);	//480/2
	public static int currentScreenSizeX = INITIAL_WINDOW_WIDTH;
	public static int currentScreenSizeY = INITIAL_WINDOW_HEIGHT;
	
	public static GameState currentState;
	public static String locOfSavedGame = null;
	
	public static SpriteBatch batch;
	public static Debugger debugger;
	public static DebugMenu debugMenu;
	public static Level level;
	public static Hero hero;
	public static Inventory inventory;
	//public static DisplayMode displayMode = new DisplayMode(2048, 1280);
	
	protected void createWindow() {
		try {
//			Display.setDisplayMode(new DisplayMode(INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
//			Display.setTitle(GAME_TITLE);
			
			//Chris' fullscreen display.
			//Display.setDisplayMode(new DisplayMode(INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			for (int i=0;i<modes.length;i++) {
			    DisplayMode current = modes[i];
			    System.out.println(current.getWidth() + "x" + current.getHeight() + "x" +
			                        current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
			}
			
			setDisplayMode(INITIAL_WINDOW_WIDTH,INITIAL_WINDOW_HEIGHT, true);
			
			//Display.setFullscreen(true);
			


			// limit video card to refresh rate of screen (60 Hertz on most LCDs)
			Display.setVSyncEnabled(true);
 
			/* set up OpenGL to run in forward-compatible mode
			 so that using deprecated functionality will
			 throw an error. This ensures that we are making
			 use of OpenGL */
			PixelFormat pixelFormat = new PixelFormat();
			ContextAttribs contextAtrributes = new ContextAttribs(4, 0);
			contextAtrributes.withForwardCompatible(true);
			contextAtrributes.withProfileCore(true);
			Display.create(pixelFormat, contextAtrributes);
		} catch(LWJGLException e) {
			throw new RuntimeException("Could not initiate LWJGL.", e);
		}
	}
	
	public static void setDisplayMode(int width, int height, boolean fullscreen) {

	    // return if requested DisplayMode is already set
	    if ((Display.getDisplayMode().getWidth() == width) && 
	        (Display.getDisplayMode().getHeight() == height) && 
		(Display.isFullscreen() == fullscreen)) {
		    return;
	    }

	    try {
	        DisplayMode targetDisplayMode = null;
			
		if (fullscreen) {
		    DisplayMode[] modes = Display.getAvailableDisplayModes();
		    int freq = 0;
					
		    for (int i=0;i<modes.length;i++) {
		        DisplayMode current = modes[i];
						
			if ((current.getWidth() == width) && (current.getHeight() == height)) {
			    if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
			        if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
				    targetDisplayMode = current;
				    freq = targetDisplayMode.getFrequency();
	                        }
	                    }

			    // if we've found a match for bpp and frequence against the 
			    // original display mode then it's probably best to go for this one
			    // since it's most likely compatible with the monitor
			    if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
	                        (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
	                            targetDisplayMode = current;
	                            break;
	                    }
	                }
	            }
	        } else {
	            targetDisplayMode = new DisplayMode(width,height);
	        }

	        if (targetDisplayMode == null) {
	            System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
	            return;
	        }

	        Display.setDisplayMode(targetDisplayMode);
	        Display.setFullscreen(fullscreen);
				
	    } catch (LWJGLException e) {
	        System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
	    }
	}
	
	protected void initGL() {
		// Initialize OpenGL to be able to draw to the window
		glViewport(0, 0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
		
		glDisable(GL_DEPTH_TEST);	//we don't need depth for 2d unless we want advanced layers
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);	//clear screen color

		// enable alpha blending and 2d texture binding
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		
		glMatrixMode(GL_PROJECTION);	//applies matrix operations to the projection matrix stack
		glLoadIdentity();	//resets the matrix to default state
		glOrtho(0, INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT, 0, 1, -1);	//essentially sets up the perspective the matrix is viewed from
		glMatrixMode(GL_MODELVIEW); //applies matrix operations to the modelview matrix stack
	}
 
	protected void setupGame() {
		new Constants();
		new Textures();
		
		debugger = new Debugger();
		batch = new SpriteBatch(Textures.atlas);
		debugMenu = new DebugMenu();
		
//		currentState = new MainMenuState();
		currentState = new MainState(true);
	}
	
	public void gameLoop() {
		while(!Display.isCloseRequested()) {
			currentState.getInput();
			currentState.update();
			currentState.render();
		}
	}
	
	public void quit() {
		Display.destroy();
		System.exit(0);
	}

/*#########################################################*/
	
	public static void main(String[] programArguments) {
		Game game = new Game();
		
		game.createWindow();
		game.initGL();
		game.setupGame();
		
		game.gameLoop();
		game.quit();
	}
	
/*#########################################################*/
	
	protected void resize() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// ... update our projection matrices here ...
		//
	}
	
	public static void toggleInventoryState() {
		if(currentState.type == State.INVENTORY) currentState = new MainState();
		else currentState = new InventoryState();
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
		inventory = new Inventory();
		
		Game.currentState = new MainState();
	}
}
