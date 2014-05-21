package com.madbros.adventurecraft.Menus;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.GameStates.MainMenuState;
import com.madbros.adventurecraft.UI.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

public class LoadGameMenu extends Menu {
	String fileName;
	String[] saveFolders;
	SelectUIButton[] selectUIButtons;
	SelectUIButton  currentlySelectedButton;
	public File[] listOfFiles;
	public SpriteBatch batch;
	
	public LoadGameMenu(SpriteBatch batch) {
		super(batch);
	}

	@Override
	public void setupMenu(SpriteBatch batch) {
		Game.createSavesFolderIfNecessary();
		File folder = new File(SAVE_LOC);
		listOfFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		    	return new File(dir, name).isDirectory();
		    }
		});
		this.batch = batch;
		
		saveFolders = new String[listOfFiles.length];
		for(int i = 0; i < listOfFiles.length; i++) {
			saveFolders[i] = listOfFiles[i].getName();
		}
		
		ButtonFunction load = new ButtonFunction() { public void invoke() { load(); } };
		ButtonFunction delete = new ButtonFunction() { public void invoke() { delete(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		//create an array of all the saved games...
		
		Rect r1 = new Rect(20, Game.currentScreenSizeY - 60, 100, 50);
		Rect r2 = new Rect(Game.currentScreenSizeX /2-50, Game.currentScreenSizeY - 60, 100, 50);
		Rect r3 = new Rect(Game.currentScreenSizeX - 120, Game.currentScreenSizeY - 60, 100, 50);
		
		String[] strings = {"Load","Delete", "Back"};
		ButtonFunction[] functions = {load, delete, cancel};
		Rect[] r = {r1, r3, r2};

		menuButtons = new UIButton[functions.length];
			for(int i = 0; i < functions.length; i++) {
				menuButtons[i] = new PlainUIButton(r[i].x, r[i].y, r[i].w, r[i].h, strings[i], functions[i], batch);
			}
		
		

		
		
		refreshMenu();
	}
	
	public void refreshMenu() {
		int marginY = 3;
		Rect r1 = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		selectUIButtons = new SelectUIButton[saveFolders.length];
		if(selectUIButtons.length < 6) {
			for(int i = 0; i < selectUIButtons.length; i++) {
				selectUIButtons[i] = new SelectUIButton(r1.x, r1.y + i * (r1.h + marginY), r1.w, r1.h, saveFolders[i], batch);
				selectUIButtons[i].iD = i;
			}
		} else {
			for(int i = 0; i < selectUIButtons.length; i++) {
				if(i< 5) {
					selectUIButtons[i] = new SelectUIButton(r1.x-r1.w/2-9, r1.y + i * (r1.h + marginY), r1.w, r1.h, saveFolders[i], batch);
					selectUIButtons[i].iD = i;
				} else {
					selectUIButtons[i] = new SelectUIButton(r1.x+r1.w/2+9, r1.y + (i-5) * (r1.h + marginY), r1.w, r1.h, saveFolders[i], batch);
					selectUIButtons[i].iD = i;
				}
			}
		}
	}
	
	@Override
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseReleased) {
		super.handleMouseInput(leftMousePressed, leftMouseReleased);
		for(int i = 0; i < selectUIButtons.length; i++) {
			boolean didPressDown = selectUIButtons[i].handleMouseInput(leftMousePressed, leftMouseReleased);
			if(didPressDown) {
				if(currentlySelectedButton != null && currentlySelectedButton != selectUIButtons[i]) currentlySelectedButton.buttonIsPressedDown = false;
				currentlySelectedButton = selectUIButtons[i];
			}
		}
	}
	
	@Override
	public void render() {
		super.renderSplashScreen();
		super.render();
		for(int i = 0; i < selectUIButtons.length; i++) {
			selectUIButtons[i].render();
		}
		//if more than say 10 games, have a next page button/previous page button
	}
	
	@Override
	public void renderText() {
		super.renderText();
		for(int i = 0; i < selectUIButtons.length; i++) {
			selectUIButtons[i].renderText();
		}
	}
	
	public void load() {
		Game.isNewGame = false;
		if(currentlySelectedButton != null) Game.createNewGameAtLoc(SAVE_LOC + currentlySelectedButton.text.getString() + "/");
		
	}
	
	public void delete() {
		if(currentlySelectedButton != null) {
			//System.out.println(currentlySelectedButton.iD);
			//listOfFiles[currentlySelectedButton.iD].delete();
			try {
				FileUtils.deleteDirectory(listOfFiles[currentlySelectedButton.iD]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentlySelectedButton = null;
		}
		setupMenu(batch);
		//refreshMenu();
		
		
	}
	public void cancel() {
		MainMenuState.cancel(Game.batch);
	}
}
