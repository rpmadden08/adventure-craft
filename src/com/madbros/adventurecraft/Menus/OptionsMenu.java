package com.madbros.adventurecraft.Menus;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.Game;

import static com.madbros.adventurecraft.Constants.*;

import com.madbros.adventurecraft.GameObjects.Mob;
import com.madbros.adventurecraft.GameStates.MainMenuState;
import com.madbros.adventurecraft.Sprites.Sprites;
import com.madbros.adventurecraft.UI.*;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;
import com.madbros.adventurecraft.Utils.Text;

public class OptionsMenu extends Menu {
	String fileName;
	String[] saveFolders;
	SelectUIButton[] selectUIButtons;
	SelectUIButton  currentlySelectedButton;
	//public File[] listOfFiles;
	public SpriteBatch batch;
	public ArrayList<DisplayMode> resolutions = new ArrayList<DisplayMode>();
	public int currentlySelectedR = 0;
	public String resolution;
	public String isStretched = " ";
	
	public OptionsMenu(SpriteBatch batch) {
		super(batch);
	}

	@Override
	public void setupMenu(SpriteBatch batch) {
		isStretched = " ";
		refreshMenu();
		
		

		
		getResolutions();
		//refreshMenu();
	}
	
	public void getResolutions() {
		resolutions = new ArrayList<DisplayMode>();
		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			
			for (int i=0;i<modes.length;i++) {
			    DisplayMode current = modes[i]; 
			    if(current.getBitsPerPixel() == 32 && current.getWidth() <= 1440 && current.getHeight() <=900 ) {
			    	
			    	resolutions.add(current);
			    	if(current.getWidth() == Game.currentScreenSizeX) {
			    		currentlySelectedR = resolutions.size()-1;
			    	}
			    }
			}
		} catch(LWJGLException e) {
			throw new RuntimeException("Could not initiate LWJGL.", e);
		}
	}
	
	
	public void refreshMenu() {
		ButtonFunction leftResolution = new ButtonFunction() { public void invoke() { leftResolution(); } };
		ButtonFunction rightResolution = new ButtonFunction() { public void invoke() { rightResolution(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		resolution = Game.currentScreenSizeX+"x"+Game.currentScreenSizeY+isStretched;
		
		
		//ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		//create an array of all the saved games...
		
		Rect r1 = new Rect(MAIN_MENU_STARTX-200, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r2 = new Rect(MAIN_MENU_STARTX+200, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r3 = new Rect(Game.currentScreenSizeX /2-50, Game.currentScreenSizeY - 60, 100, 50);
		Rect r4 = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		
		String[] strings = {"<",">", "cancel", resolution};
		ButtonFunction[] functions = {leftResolution, rightResolution, cancel, rightResolution};
		Rect[] r = {r1, r2, r3, r4};

		menuButtons = new UIButton[functions.length];
		for(int i = 0; i < functions.length; i++) {
			menuButtons[i] = new PlainUIButton(r[i].x, r[i].y, r[i].w, r[i].h, strings[i], functions[i], Game.batch);
		}
	}
	
	public void leftResolution() {
		getResolutions();
		if(currentlySelectedR == 0) {
			currentlySelectedR = resolutions.size()-1;
		} else {
			currentlySelectedR = currentlySelectedR-1;
		}
		changeResolution();
		
	}
	
	public void rightResolution() {
		getResolutions();
		if(currentlySelectedR == resolutions.size()-1) {
			currentlySelectedR = 0;
		} else {
			currentlySelectedR = currentlySelectedR+1;
		}
		changeResolution();
	}
	
	public void changeResolution() {
		try {
			Display.setDisplayMode(resolutions.get(currentlySelectedR));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((float)resolutions.get(currentlySelectedR).getWidth()/(float)resolutions.get(currentlySelectedR).getHeight() == (float)Gdx.graphics.getDesktopDisplayMode().width/(float)Gdx.graphics.getDesktopDisplayMode().height
		    	) {
		    		isStretched = " ";
		    	} else {
		    		isStretched = "(STRETCHED)";
		    	}
		Game.currentScreenSizeX = resolutions.get(currentlySelectedR).getWidth();
		Game.currentScreenSizeY = resolutions.get(currentlySelectedR).getHeight();
		Game.camera= new OrthographicCamera(Game.currentScreenSizeX,Game.currentScreenSizeY);
		Game.camera.setToOrtho(true, Game.currentScreenSizeX, Game.currentScreenSizeY);

		Game.camera.update();
		MAIN_MENU_STARTX = Game.currentScreenSizeX/2 - MAIN_MENU_WIDTH / 2;
		MAIN_MENU_STARTY = Game.currentScreenSizeY-230;
		refreshMenu();
	}
	
	@Override
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseReleased) {
		super.handleMouseInput(leftMousePressed, leftMouseReleased);
//		for(int i = 0; i < selectUIButtons.length; i++) {
//			boolean didPressDown = selectUIButtons[i].handleMouseInput(leftMousePressed, leftMouseReleased);
//			if(didPressDown) {
//				if(currentlySelectedButton != null && currentlySelectedButton != selectUIButtons[i]) currentlySelectedButton.buttonIsPressedDown = false;
//				currentlySelectedButton = selectUIButtons[i];
//			}
//		}
	}
	
	@Override
	public void render() {
		super.renderSplashScreen();
		super.render();
//		for(int i = 0; i < selectUIButtons.length; i++) {
//			selectUIButtons[i].render();
//		}
		//if more than say 10 games, have a next page button/previous page button
	}
	
	@Override
	public void renderText() {
		super.renderText();
//		for(int i = 0; i < selectUIButtons.length; i++) {
//			selectUIButtons[i].renderText();
//		}
	}
	
	public void load() {
		Game.isNewGame = false;
		if(currentlySelectedButton != null) Game.createNewGameAtLoc(SAVE_LOC + currentlySelectedButton.text.getString() + "/");
		
	}
	
//	public void delete() {
//		if(currentlySelectedButton != null) {
//			//System.out.println(currentlySelectedButton.iD);
//			//listOfFiles[currentlySelectedButton.iD].delete();
//			try {
//				FileUtils.deleteDirectory(listOfFiles[currentlySelectedButton.iD]);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			currentlySelectedButton = null;
//		}
//		setupMenu(batch);
//		//refreshMenu();
//		
//		
//	}
	public void cancel() {
		MainMenuState.cancel(Game.batch);
	}
}
