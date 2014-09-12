package com.madbros.tileminer.Menus;

import static com.madbros.tileminer.Constants.*;

import java.io.File;
import java.io.FilenameFilter;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameStates.MainMenuState;
import com.madbros.tileminer.UI.*;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class MainMenu extends Menu{
	public boolean isNewGameAvailable;
	public File[] listOfFiles;
	public MainMenu(SpriteBatch batch) {
		super(batch);
	}

	
	@Override
	public void setupMenu(SpriteBatch batch) {
		ButtonFunction newGame = new ButtonFunction() { public void invoke() { newGame(); } };
		String loadGameString = "Load Game";
		ButtonFunction loadGame = new ButtonFunction() { public void invoke() { loadGame(); } };
		if(Game.isDemo) {
			loadGame = new ButtonFunction() { public void invoke() { buyNow(); } };
			loadGameString = "BUY NOW";
		} 
		//ButtonFunction loadGame = new ButtonFunction() { public void invoke() { loadGame(); } };
		
		ButtonFunction options = new ButtonFunction() { public void invoke() { options(); } };
		ButtonFunction credits = new ButtonFunction() { public void invoke() { credits(); } };
		ButtonFunction quitGame = new ButtonFunction() { public void invoke() { quitGame(); } };
		
		Rect r = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		int marginY = 10;
		
			String[] strings = {"New Game", loadGameString, "Options", "Credits", "Quit"};
			ButtonFunction[] functions = {newGame, loadGame, options, credits, quitGame};
		
		menuButtons = new TextUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			RectInt r2 = r.getRectInt();
			menuButtons[i] = new TextUIButton(r2.x, r2.y + i * (r2.h + marginY), r2.w, r2.h, strings[i], functions[i], batch);
		}


	
		File folder = new File(SAVE_LOC);
		listOfFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		    	return new File(dir, name).isDirectory();
		    }
		});
		if(listOfFiles != null && listOfFiles.length >9) {
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
		}
	}
	
	private void loadGame() {
		MainMenuState.loadGame(Game.batch);
	}
	private void buyNow() {
		Gdx.net.openURI("http://tileminer.itch.io/tile-miner");
	}
	
	private void options() {
		MainMenuState.options(Game.batch);
		
	}
	
	private void credits() {
		Gdx.net.openURI("http://www.tileminer.org/credits/");
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
