package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.UI.PlainUIButton;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

//Chris' test imports
import com.madbros.adventurecraft.Game;

public class GameMainMenu extends Menu {

	public boolean menuIsActive;
	
	public GameMainMenu(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void setupMenu(SpriteBatch batch) {

		menuIsActive = false;

		Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);
		ButtonFunction resumeGame = new ButtonFunction() { public void invoke() { resumeGameFunction(); } };
		ButtonFunction options = new ButtonFunction() { public void invoke() { optionsFunction(); } };
		ButtonFunction howToPlay = new ButtonFunction() { public void invoke() { howToPlayFunction(); } };
		//ButtonFunction saveGame = new ButtonFunction() { public void invoke() { saveGameFunction(); } };
		ButtonFunction quitGame = new ButtonFunction() { public void invoke() { quitGameFunction(); } };

		
		String s1, s2, s3, s4;
		s1 = "Resume";
		s2 = "Options";
		s3 = "How To Play";
		s4 = "Save & Quit";
		
		//Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);

		String[] strings = {s1, s2, s3,s4};
		ButtonFunction[] functions = {resumeGame, options, howToPlay, quitGame};
	
		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new PlainUIButton(r.x/2, r.y*i+Game.currentScreenSizeY/2-DEBUG_MENU_SIZEY*menuButtons.length/2, r.w, r.h, strings[i], functions[i], batch);
			
		}
	}
	
//	public void toggleMenu() {
//		if(menuIsActive) menuIsActive = false;
//		else menuIsActive = true;
//	}
	
	private void resumeGameFunction() {
		Game.toggleMainMenu();
	}
	
	private void howToPlayFunction() {
		Game.gameMainMenu = new GameHowToPlay(Game.batch);
		Game.gameMainMenu.menuIsActive = true;
	}
	
	private void optionsFunction() {
		Game.gameMainMenu = new GameOptionsMenu(Game.batch);
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
