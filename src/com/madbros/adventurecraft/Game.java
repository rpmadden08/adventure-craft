package com.madbros.adventurecraft;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import com.madbros.adventurecraft.GameStates.*;

import static org.lwjgl.opengl.GL11.*;

import static com.madbros.adventurecraft.Constants.*;

public class Game {
	public static int renderWidth = (int)Math.ceil(INITIAL_WINDOW_WIDTH * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int renderHeight = (int)Math.ceil(INITIAL_WINDOW_HEIGHT * 1.0 / TILE_SIZE) + RENDER_MARGIN;
	public static int pixelModifier = 1;
	public static int centerScreenX = (int)Math.floor(INITIAL_WINDOW_WIDTH/2);
	public static int centerScreenY = (int)Math.floor(INITIAL_WINDOW_HEIGHT/2);
	public static int currentScreenSizeX = INITIAL_WINDOW_WIDTH;
	public static int currentScreenSizeY = INITIAL_WINDOW_HEIGHT;
	
	public static GameState currentState = new MainState();
	
	Block tile;
	
	public static SpriteBatch batch;
	public static Debugger debugger;
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	
	protected void createWindow() {
		try {
			Display.setDisplayMode(new DisplayMode(INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT));
			Display.setTitle(GAME_TITLE);
			
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
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		new Textures();
		new Helpers();
		
		batch = new SpriteBatch(Textures.atlas);
		
		level = new Level();
		character = new Character();
		inventory = new Inventory();
		
		debugger = new Debugger();
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
}
