package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Menus.*;

public class MainMenuState extends GameState{
	public static Menu mainMenu;
	
	public MainMenuState(SpriteBatch batch) {
		mainMenu = new MainMenu(batch);
		type = State.MAIN_MENU;
		
		//FIXME: fix mouse input
	}

	@Override
	protected void renderTextures() {
		mainMenu.render();
	}
	
	@Override
	protected void renderText() {
		mainMenu.renderText();
	}
	
	public static void newGame(SpriteBatch batch) {
		mainMenu = new NewGameMenu(batch);
	}
	
	public static void cancel(SpriteBatch batch) {
		mainMenu = new MainMenu(batch);
	}
	
	public static void loadGame(SpriteBatch batch) {
		mainMenu = new LoadGameMenu(batch);
	}
}