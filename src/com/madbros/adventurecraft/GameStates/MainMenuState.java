package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Menus.*;

public class MainMenuState extends GameState{
	public static Menu mainMenu;
	
	public MainMenuState() {
		mainMenu = new MainMenu();
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
	
	public static void newGame() {
		mainMenu = new NewGameMenu();
	}
	
	public static void cancel() {
		mainMenu = new MainMenu();
	}
	
	public static void loadGame() {
		mainMenu = new LoadGameMenu();
	}
}