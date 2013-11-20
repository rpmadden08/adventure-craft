package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.Menus.*;

public class MainMenuState extends GameState{
	public static Menu mainMenu;
	
	public MainMenuState(SpriteBatch batch) {
		mainMenu = new MainMenu(batch);
		type = State.MAIN_MENU;
		
		input = new MainMenuStateInput(mainMenu);
		Gdx.input.setInputProcessor(input);
		//FIXME: fix mouse input
	}

	@Override
	protected void renderTextures() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.begin();
			mainMenu.render();
			mainMenu.renderText();
		Game.batch.end();
	}
	
	@Override
	protected void renderText() {
		mainMenu.renderText();
	}
	
	public static void newGame(SpriteBatch batch) {
		mainMenu = new NewGameMenu(batch);
		input = new MainMenuStateInput(mainMenu);
		Gdx.input.setInputProcessor(input);
	}
	
	public static void cancel(SpriteBatch batch) {
		mainMenu = new MainMenu(batch);
		input = new MainMenuStateInput(mainMenu);
		Gdx.input.setInputProcessor(input);
	}
	
	public static void loadGame(SpriteBatch batch) {
		mainMenu = new LoadGameMenu(batch);
		input = new MainMenuStateInput(mainMenu);
		Gdx.input.setInputProcessor(input);
	}
}