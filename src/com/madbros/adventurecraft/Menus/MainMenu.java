package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
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
	public MainMenu(SpriteBatch batch) {
		super(batch);
	}
	public StaticSprite sprite;
	public StaticSprite sprite2;
	
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
		Texture backdrop1 = new Texture(Gdx.files.internal("data/backdrop1.png"));
		Texture logo = new Texture(Gdx.files.internal("data/logo.png"));

		sprite = new StaticSprite(backdrop1, 0,0,1440,900,Game.batch);
		sprite2 = new StaticSprite(logo, 0,0,logo.getWidth(),logo.getHeight(),Game.batch);
	}
	
	private void newGame() {
		MyTextInputListener listener = new MyTextInputListener();
		Gdx.input.getTextInput(listener, "Name of New Game", "New Game");
		//MainMenuState.newGame(Game.batch);
	}
	
	private void loadGame() {
		MainMenuState.loadGame(Game.batch);
	}
	
	private void options() {
		System.out.println("options");
	}
	
	public void quitGame() {
		Display.destroy();
		System.exit(0);
	}
	
	public void render() {
		super.render();
		sprite.draw(Game.batch);
		sprite2.draw(INITIAL_WINDOW_WIDTH /2 - sprite2.getWidth()*2 / 2, 40,0f, sprite2.getWidth()*2, sprite2.getHeight()*2);	
	}
}
