package com.madbros.adventurecraft.Menus;

import static com.madbros.adventurecraft.Constants.DEBUG_MENU_SIZEX;

import static com.madbros.adventurecraft.Constants.DEBUG_MENU_SIZEY;
import static com.madbros.adventurecraft.Constants.INITIAL_WINDOW_HEIGHT;
import static com.madbros.adventurecraft.Constants.INITIAL_WINDOW_WIDTH;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.UI.PlainUIButton;
import com.madbros.adventurecraft.Utils.ButtonFunction;
import com.madbros.adventurecraft.Utils.Rect;

//Chris' test imports
import com.madbros.adventurecraft.Game;

public class DebugMenu extends Menu {
	public boolean collisionTilesAreOn;
	public boolean collisionRectsAreOn;
	public boolean chunkBoundariesAreOn;
	public boolean collisionDetectionIsOn;
	public boolean menuIsActive;
	public boolean fullscreenIsOn;
	
	public DebugMenu() {
		super();
	}
	
	@Override
	public void setupMenu() {
		collisionTilesAreOn = false;
		collisionRectsAreOn = false;
		chunkBoundariesAreOn = false;
		collisionDetectionIsOn = true;
		menuIsActive = false;
		fullscreenIsOn = true;
		
		
		Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);
		ButtonFunction collisionDetection = new ButtonFunction() { public void invoke() { toggleCollisionDetection(); } };
		ButtonFunction collisionRectangles = new ButtonFunction() { public void invoke() { toggleCollisionRectangles(); } };
		ButtonFunction collisionTiles = new ButtonFunction() { public void invoke() { toggleCollisionTiles(); } };
		ButtonFunction chunkBoundaries = new ButtonFunction() { public void invoke() { toggleChunkBoundaries(); } };
		ButtonFunction characterSpeedDown = new ButtonFunction() { public void invoke() { characterSpeedDown(); } };
		ButtonFunction characterSpeedUp = new ButtonFunction() { public void invoke() { characterSpeedUp(); } };
		ButtonFunction tester = new ButtonFunction() { public void invoke() { tester(); } };
		
		String s1, s2, s3, s4, s5;
		if(collisionDetectionIsOn) s1 = "Collision Detection Is On"; else s1 = "Collision Detection Is Off";
		if(collisionRectsAreOn) s2 = "Collision Rectangles Are On"; else s2 = "Collision Rectangles Are Off";
		if(collisionTilesAreOn) s3 = "Collision Tiles Are On"; else s3 = "Collision Tiles Are Off";
		if(chunkBoundariesAreOn) s4 = "Chunk Boundaries Are On"; else s4 = "Chunk Boundaries Are Off";
		if(fullscreenIsOn) s5 = "Fullscreen Is On"; else s5 = "Fullscreen Is Off";

		String[] strings = {s1, s2, s3, s4, "Speed-", "Speed+", s5};
		ButtonFunction[] functions = {collisionDetection, collisionRectangles, collisionTiles, chunkBoundaries, characterSpeedDown, characterSpeedUp, tester};
		
		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i] = new PlainUIButton(r.x, r.y*i, r.w, r.h, strings[i], functions[i]);
		}
	}
	
	public void tester() {
		if(fullscreenIsOn == false) {
			Game.setDisplayMode(INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT, true);
			fullscreenIsOn = true;
			menuButtons[6].setString("Fullscreen Is On");
		} else {
			Game.setDisplayMode(INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT, false);
			fullscreenIsOn = false;
			menuButtons[6].setString("Fullscreen Is Off");
		}
		
	}
	
	public void toggleMenu() {
		if(menuIsActive) menuIsActive = false;
		else menuIsActive = true;
	}
	
	private void toggleCollisionDetection() {
		if(collisionDetectionIsOn) {
			collisionDetectionIsOn = false;
			menuButtons[0].setString("Collision Detection Is Off");
		} else {
			collisionDetectionIsOn = true;
			menuButtons[0].setString("Collision Detection Is On");
		}	}
	
	private void toggleCollisionRectangles() {
		if(collisionRectsAreOn) {
			collisionRectsAreOn = false;
			menuButtons[1].setString("Collision Rectangles Are Off");
		} else {
			collisionRectsAreOn = true;
			menuButtons[1].setString("Collision Rectangles Are On");
		}
	}
	
	private void toggleCollisionTiles() {
		if(collisionTilesAreOn) {
			collisionTilesAreOn = false;
			menuButtons[2].setString("Collision Tiles Are Off");
		} else {
			collisionTilesAreOn = true;
			menuButtons[2].setString("Collision Tiles Are On");
		}
	}
	
	private void toggleChunkBoundaries() {
		if(chunkBoundariesAreOn) {
			chunkBoundariesAreOn = false;
			menuButtons[3].setString("Chunk Boundaries Are Off");
		} else {
			chunkBoundariesAreOn = true;
			menuButtons[3].setString("Chunk Boundaries Are On");
		}
	}
	
	private void characterSpeedDown() {
		float s = Hero.getCurrentSpeed();
		if(s > 0.09f) Hero.setCurrentSpeed(s - 0.1f);
	}
	
	private void characterSpeedUp() {
		float s = Hero.getCurrentSpeed();
		if(s < 0.9f) Hero.setCurrentSpeed(s + 0.1f);
	}

}
