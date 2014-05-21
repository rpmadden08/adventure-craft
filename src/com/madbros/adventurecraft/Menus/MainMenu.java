package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.*;

import java.io.File;
import java.io.FilenameFilter;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.MyTextInputListener;
import com.madbros.adventurecraft.GameStates.MainMenuState;
import com.madbros.adventurecraft.Sprites.StaticSprite;
import com.madbros.adventurecraft.UI.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class MainMenu extends Menu{
	public boolean isNewGameAvailable;
	public File[] listOfFiles;
	public MainMenu(SpriteBatch batch) {
		super(batch);
	}

	
	@Override
	public void setupMenu(SpriteBatch batch) {
		ButtonFunction newGame = new ButtonFunction() { public void invoke() { newGame(); } };
		ButtonFunction loadGame = new ButtonFunction() { public void invoke() { loadGame(); } };
		ButtonFunction options = new ButtonFunction() { public void invoke() { options(); } };
		ButtonFunction quitGame = new ButtonFunction() { public void invoke() { quitGame(); } };
		
		Rect r = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		int marginY = 10;
		String[] strings = {"New Game", "Load Game", "Options", "Quit"};
		
		ButtonFunction[] functions = {newGame, loadGame, options, quitGame};
		
		menuButtons = new TextUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new TextUIButton(r.x, r.y + i * (r.h + marginY), r.w, r.h, strings[i], functions[i], batch);
		}


	
		File folder = new File(SAVE_LOC);
		listOfFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		    	return new File(dir, name).isDirectory();
		    }
		});
		if(listOfFiles.length >9) {
			isNewGameAvailable = false;
			menuButtons[0].disableButton();
			
		} else {
			isNewGameAvailable = true;
		}
	}
	
	private void newGame() {
//		MyTextInputListener listener = new MyTextInputListener();
//		Gdx.input.getTextInput(listener, "Name of New Game", "New Game");
//		//MainMenuState.newGame(Game.batch);
		if(isNewGameAvailable == true) {
			Game.gameFileName = "Adventure 1";
			//MainMenuState.newGame(Game.batch);
			File f = new File(SAVE_LOC + Game.gameFileName);
			if(f.exists()) {
				int gameNumber = 1;
				String baseName = "Adventure ";
				while(true) {
					f = new File(SAVE_LOC + baseName + String.valueOf(gameNumber));
					if(!f.exists()) {
						Game.gameFileName = baseName + String.valueOf(gameNumber);
						break;
					}
					gameNumber++;
				}
			}
			Game.createNewGameAtLoc(SAVE_LOC + Game.gameFileName + "/");
		} else {
			System.out.println("test2");
		}
	}
	
	private void loadGame() {
		MainMenuState.loadGame(Game.batch);
	}
	
	private void options() {
		MainMenuState.options(Game.batch);
	}
	
	public void quitGame() {
		Display.destroy();
		System.exit(0);
	}
	

	
	public void render() {
		super.render();
		super.renderSplashScreen();
	}
}
