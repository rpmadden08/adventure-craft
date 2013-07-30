package com.madbros.adventurecraft.Menus;

import java.io.File;

import org.newdawn.slick.Color;

import com.madbros.adventurecraft.*;
import static com.madbros.adventurecraft.Constants.*;
import com.madbros.adventurecraft.GameStates.*;
import com.madbros.adventurecraft.UI.*;
import com.madbros.adventurecraft.Utils.*;

public class NewGameMenu extends Menu {
	private String fileName;
	private Rect textRect;
	private Text gameName;
	
	public NewGameMenu() {
		super();
	}
	
	@Override
	public void setupMenu() {
		fileName = "New Game";
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
		gameName = new Text(Textures.font, "Game Name: " + fileName);
		
		ButtonFunction create = new ButtonFunction() { public void invoke() { create(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		Rect r1 = new Rect(20, Game.currentScreenSizeY - 60, 100, 50);
		Rect r2 = new Rect(Game.currentScreenSizeX - 120, Game.currentScreenSizeY - 60, 100, 50);
		
		String[] strings = {"Create", "Cancel"};
		ButtonFunction[] functions = {create, cancel};
		Rect[] r = {r1, r2};

		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new PlainUIButton(r[i].x, r[i].y, r[i].w, r[i].h, strings[i], functions[i]);
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
		gameName.drawCenter(textRect, Color.white);
	}
	
	public void create() {
		Game.createNewGameAtLoc(SAVE_LOC + fileName + "/");
	}
	
	public void cancel() {
		MainMenuState.cancel();
	}
}
