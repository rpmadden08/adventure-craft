package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.GameStates.MainMenuStateInput;
import com.madbros.adventurecraft.UI.PlainUIButton;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

//Chris' test imports
import com.madbros.adventurecraft.Game;

public class GameHowToPlay extends GameMainMenu {

	public boolean menuIsActive;
	//public Rect 
	public int HOW_TO_PLAY_WIDTH = 640;
	public int HOW_TO_PLAY_HEIGHT = 480;
	public int HOW_TO_PLAY_STARTX = Game.currentScreenSizeX /2 - 640 / 2;
	public int HOW_TO_PLAY_STARTY = Game.currentScreenSizeY /2 - 480 / 2;
	public Rect HOW_TO_PLAY_RECT = new Rect(HOW_TO_PLAY_STARTX, HOW_TO_PLAY_STARTY,HOW_TO_PLAY_WIDTH,HOW_TO_PLAY_HEIGHT);
	
	
	public GameHowToPlay(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void setupMenu(SpriteBatch batch) {

		menuIsActive = false;
		
		
		Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, 20, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);
		ButtonFunction goBack = new ButtonFunction() { public void invoke() { goBackFunction(); } };

		
		String s1;
		s1 = "Back";

		
		//Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);

		String[] strings = {s1};
		ButtonFunction[] functions = {goBack};
	
		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new PlainUIButton(r.x/2, r.y, r.w, r.h, strings[i], functions[i], batch);
			
		}
	}
	public void renderBefore() {
		//x and y need to be a few to the right and down...
		Game.inventory.menuSprites[0].draw(HOW_TO_PLAY_RECT.x, HOW_TO_PLAY_RECT.y, Z_INV_BACKDROP);	//top left
		Game.inventory.menuSprites[6].draw(HOW_TO_PLAY_RECT.x2()-INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y, Z_INV_BACKDROP); //top right
		Game.inventory.menuSprites[2].draw(HOW_TO_PLAY_RECT.x, HOW_TO_PLAY_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom left
		Game.inventory.menuSprites[8].draw(HOW_TO_PLAY_RECT.x2()-INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP);	//bottom right
		
		Game.inventory.menuSprites[3].draw(HOW_TO_PLAY_RECT.x+INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y, Z_INV_BACKDROP, HOW_TO_PLAY_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//top
		Game.inventory.menuSprites[5].draw(HOW_TO_PLAY_RECT.x+INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y2()-INV_MENU_TILE_SIZE, Z_INV_BACKDROP, HOW_TO_PLAY_RECT.w-INV_MENU_TILE_SIZE*2, INV_MENU_TILE_SIZE);	//bottom
		Game.inventory.menuSprites[1].draw(HOW_TO_PLAY_RECT.x, HOW_TO_PLAY_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.h-INV_MENU_TILE_SIZE*2);	//left
		Game.inventory.menuSprites[7].draw(HOW_TO_PLAY_RECT.x2()-INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.h-INV_MENU_TILE_SIZE*2);	//right
		
		Game.inventory.menuSprites[4].draw(HOW_TO_PLAY_RECT.x+INV_MENU_TILE_SIZE, HOW_TO_PLAY_RECT.y+INV_MENU_TILE_SIZE, Z_INV_BACKDROP, HOW_TO_PLAY_RECT.w-INV_MENU_TILE_SIZE*2, HOW_TO_PLAY_RECT.h-INV_MENU_TILE_SIZE*2); //middle
		
		howToPlaySprite.draw(Game.currentScreenSizeX /2 - howToPlaySprite.getWidth() / 2,
				Game.currentScreenSizeY /2 - howToPlaySprite.getHeight() / 2,
				0f, howToPlaySprite.getWidth(), howToPlaySprite.getHeight());	
	}
	public void toggleMenu() {
		if(menuIsActive) menuIsActive = false;
		else menuIsActive = true;
	}
	
	private void resumeGameFunction() {
		toggleMenu();
	}
	
	private void goBackFunction() {
		Game.gameMainMenu = new GameMainMenu(Game.batch);
		Game.gameMainMenu.menuIsActive = true;
	}

//	private void saveGameFunction() {
//		Game.level.saveCurrentChunks();
//		Game.level.saveGame.saveGame();
//	}

	private void quitGameFunction() {
		Game.quit();
		Display.destroy();
		System.exit(0);
	}
	
//	private void characterSpeedDown() {
//		float s = Game.hero.getCurrentSpeed();
//		if(s > 0.09f) Game.hero.setCurrentSpeed(s - 0.1f);
//	}
//	
//	private void characterSpeedUp() {
//		float s = Game.hero.getCurrentSpeed();
//		if(s < 0.9f) Game.hero.setCurrentSpeed(s + 0.1f);
//	}

}
