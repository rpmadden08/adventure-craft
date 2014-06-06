package com.madbros.tileminer.Menus;



import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.madbros.tileminer.Constants.*;

import com.madbros.tileminer.Game;
import com.madbros.tileminer.GameStates.MainMenuState;
import com.madbros.tileminer.UI.*;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;

public class OptionsMenu extends Menu {
	String fileName;
	String[] saveFolders;
	SelectUIButton[] selectUIButtons;
	SelectUIButton  currentlySelectedButton;
	//public File[] listOfFiles;
	public SpriteBatch batch;
	//public ArrayList<DisplayMode> resolutions = new ArrayList<DisplayMode>();
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
		

		getCurrentResolution();
	}
	
	public void getCurrentResolution() {
		for(int i = 0; i < Game.resolutions.size(); i++) {
			if(Game.resolutions.get(i).getWidth() == Game.currentScreenSizeX
					&& Game.resolutions.get(i).getHeight() == Game.currentScreenSizeY) {
				currentlySelectedR = i;
			}
		}
	}
	
	
	public void refreshMenu() {
		ButtonFunction leftResolution = new ButtonFunction() { public void invoke() { leftResolution(); } };
		ButtonFunction rightResolution = new ButtonFunction() { public void invoke() { rightResolution(); } };
		ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		ButtonFunction toggleSound = new ButtonFunction() { public void invoke() { toggleSound(); } };

		ButtonFunction toggleMusic = new ButtonFunction() { public void invoke() { toggleMusic(); } };
		
		resolution = Game.currentScreenSizeX+"x"+Game.currentScreenSizeY+isStretched;
		
		
		//ButtonFunction cancel = new ButtonFunction() { public void invoke() { cancel(); } };
		
		//create an array of all the saved games...
		
		Rect r1 = new Rect(MAIN_MENU_STARTX-200, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r2 = new Rect(MAIN_MENU_STARTX+200, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r3 = new Rect(Game.currentScreenSizeX /2-50, Game.currentScreenSizeY - 60, 100, 50);
		Rect r4 = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r5 = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY+40, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		Rect r6 = new Rect(MAIN_MENU_STARTX, MAIN_MENU_STARTY+80, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
		
		String sound;
		String music;
		if(Game.isSoundOn) {sound = "On";} else {sound = "Off";}
		if(Game.isMusicOn) {music = "On";} else {music = "Off";}
		String[] strings = {"<",">", "Back", resolution, "Sound is "+sound,  "Music is "+music};
		ButtonFunction[] functions = {leftResolution, rightResolution, cancel, rightResolution, toggleSound, toggleMusic};
		Rect[] r = {r1, r2, r3, r4, r5, r6};

		menuButtons = new UIButton[functions.length];
		for(int i = 0; i < functions.length; i++) {
			menuButtons[i] = new PlainUIButton(r[i].x, r[i].y, r[i].w, r[i].h, strings[i], functions[i], Game.batch);
		}
		//getResolutions();
	}
	
	public void leftResolution() {
		//getResolutions();
		if(currentlySelectedR == 0) {
			currentlySelectedR = Game.resolutions.size()-1;
		} else {
			currentlySelectedR = currentlySelectedR-1;
		}
		changeResolution();
		
	}
	
	public void rightResolution() {
		
		//getResolutions();

		if(currentlySelectedR == Game.resolutions.size()-1) {
			currentlySelectedR = 0;
		} else {
			currentlySelectedR = currentlySelectedR+1;
		}
		changeResolution();
	}
	
	public void changeResolution() {
		try {
			Display.setDisplayMode(Game.resolutions.get(currentlySelectedR));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((float)Game.resolutions.get(currentlySelectedR).getWidth()/(float)Game.resolutions.get(currentlySelectedR).getHeight() == (float)Gdx.graphics.getDesktopDisplayMode().width/(float)Gdx.graphics.getDesktopDisplayMode().height
		    	) {
		    		isStretched = " ";
		    	} else {
		    		isStretched = "(STRETCHED)";
		    	}
		
		
		Game.currentScreenSizeX = Game.resolutions.get(currentlySelectedR).getWidth();
		Game.currentScreenSizeY = Game.resolutions.get(currentlySelectedR).getHeight();
		Game.camera= new OrthographicCamera(Game.currentScreenSizeX,Game.currentScreenSizeY);
		Game.camera.setToOrtho(true, Game.currentScreenSizeX, Game.currentScreenSizeY);

		
		
		Game.camera.update();
		MAIN_MENU_STARTX = Game.currentScreenSizeX/2 - MAIN_MENU_WIDTH / 2;
		MAIN_MENU_STARTY = Game.currentScreenSizeY-230;
		refreshMenu();
	}
	
	public void toggleSound() {
		if(Game.isSoundOn) {
			Game.isSoundOn = false;
		} else {
			Game.isSoundOn = true;
		}
		refreshMenu();
	}
	
	public void toggleMusic() {
		if(Game.isMusicOn) {
			Game.isMusicOn = false;
		} else {
			Game.isMusicOn = true;
		}
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
