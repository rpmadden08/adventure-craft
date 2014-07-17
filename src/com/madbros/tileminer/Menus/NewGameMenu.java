package com.madbros.tileminer.Menus;

import java.io.File;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.*;
import com.madbros.tileminer.GameStates.*;
import com.madbros.tileminer.Sprites.Sprites;
import com.madbros.tileminer.UI.*;
import com.madbros.tileminer.Utils.*;

public class NewGameMenu extends Menu {
	private String fileName = "New Game";
	private Rect textRect;
	private Text gameName;
	public SpriteBatch textFieldBatch;
	
	public NewGameMenu(SpriteBatch batch) {
		super(batch);
		textFieldBatch = batch;
	}
	
	@Override
	public void setupMenu(SpriteBatch batch) {
		
		//fileName = "New Game";
		File f = new File(SAVE_LOC + fileName);
		if(f.exists()) {
			int gameNumber = 1;
			String baseName = "New Game ";
			while(true) {
				f = new File(SAVE_LOC + baseName + String.valueOf(gameNumber));
				if(!f.exists()) {
					fileName = baseName + String.valueOf(gameNumber);
					break;
				}
				gameNumber++;
			}
		}
		
		textRect = new Rect(0, 0, Game.currentScreenSizeX, Game.currentScreenSizeY-100);
		gameName = new Text(Sprites.font, "Game Name: " + fileName, batch);
		
		ButtonFunction create = new ButtonFunction() { public void invoke() { create(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		Rect r1 = new Rect(20, Game.currentScreenSizeY - 60, 100, 50);
		Rect r2 = new Rect(Game.currentScreenSizeX - 120, Game.currentScreenSizeY - 60, 100, 50);
		
		String[] strings = {"Create", "Cancel"};
		ButtonFunction[] functions = {create, cancel};
		Rect[] r = {r1, r2};

		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			RectInt rect = r[i].getRectInt();
			menuButtons[i] = new PlainUIButton(rect.x, rect.y, rect.w, rect.h, strings[i], functions[i], batch);
		}
	}
	
	@Override
	public void render() {
		super.render();
		//render text box..
	}
	
	@Override
	public void renderText() {
		super.renderText();
		gameName.drawCenter(textRect, Color.WHITE);
		
	}
	
	public void create() {
		Game.currentState = new LoadingState(Game.batch);
		Game.createNewGameAtLoc(SAVE_LOC + Game.gameFileName + "/");
	}
	
	public void cancel() {
		MainMenuState.cancel(Game.batch);
	}
}
