package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.GameStates.MainMenuState;
import com.madbros.adventurecraft.UI.PlainUIButton;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

//Chris' test imports
import com.madbros.adventurecraft.Game;

public class GameOptionsMenu extends GameMainMenu {

	public boolean menuIsActive;
	
	public GameOptionsMenu(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void setupMenu(SpriteBatch batch) {
		refreshMenu();

	}
	
	public void refreshMenu() {
		menuIsActive = false;

		Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);
		ButtonFunction toggleSound = new ButtonFunction() { public void invoke() { toggleSound(); } };
		ButtonFunction toggleMusic = new ButtonFunction() { public void invoke() { toggleMusic(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };


		String sound;
		String music;
		if(Game.isSoundOn) {sound = "On";} else {sound = "Off";}
		if(Game.isMusicOn) {music = "On";} else {music = "Off";}
		String s1, s2, s3;
		s1 = "Sound is "+sound;
		s2 = "Music is "+music;
		s3 = "Back";
		
		//Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);

		String[] strings = {s1, s2, s3};
		ButtonFunction[] functions = {toggleSound, toggleMusic, cancel};
	
		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new PlainUIButton(r.x/2, r.y*i+Game.currentScreenSizeY/2-DEBUG_MENU_SIZEY*menuButtons.length/2, r.w, r.h, strings[i], functions[i], Game.batch);
			
		}
	}
	public void toggleSound() {
		if(Game.isSoundOn) {
			Game.isSoundOn = false;
			
		} else {
			Game.isSoundOn = true;
		}
		refreshMenu();
	}
	
	public void toggleMusic() {
		if(Game.isMusicOn) {
			Game.isMusicOn = false;
			Game.musicController.stopPlaying();
		} else {
			Game.isMusicOn = true;
			Game.musicController.musicSelection = 0;
		}
		refreshMenu();
	}
	
	public void cancel() {
		Game.gameMainMenu = new GameMainMenu(Game.batch);
		Game.gameMainMenu.menuIsActive = true;
	}
	
	private void resumeGameFunction() {
		Game.toggleMainMenu();
	}
	
	private void howToPlayFunction() {
		Game.gameMainMenu = new GameHowToPlay(Game.batch);
		Game.gameMainMenu.menuIsActive = true;
	}
	
	private void optionsFunction() {
		Game.gameMainMenu = new GameHowToPlay(Game.batch);
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
