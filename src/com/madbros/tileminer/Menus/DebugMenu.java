package com.madbros.tileminer.Menus;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Chris' test imports
import com.madbros.tileminer.Game;
import com.madbros.tileminer.UI.PlainUIButton;
import com.madbros.tileminer.Utils.ButtonFunction;
import com.madbros.tileminer.Utils.Rect;
import com.madbros.tileminer.Utils.RectInt;

public class DebugMenu extends Menu {
	public boolean collisionTilesAreOn;
	public boolean collisionRectsAreOn;
	public boolean chunkBoundariesAreOn;
	public boolean collisionDetectionIsOn;
	public boolean menuIsActive;
	public boolean fullscreenIsOn;
	
	public DebugMenu(SpriteBatch batch) {
		super(batch);
	}
	
	@Override
	public void setupMenu(SpriteBatch batch) {
		collisionTilesAreOn = false;
		collisionRectsAreOn = false;
		chunkBoundariesAreOn = false;
		collisionDetectionIsOn = true;
		menuIsActive = false;
		fullscreenIsOn = false;
		
		
		Rect r = new Rect(Game.currentScreenSizeX - DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY, DEBUG_MENU_SIZEX, DEBUG_MENU_SIZEY);
		ButtonFunction collisionDetection = new ButtonFunction() { public void invoke() { toggleCollisionDetection(); } };
		ButtonFunction collisionRectangles = new ButtonFunction() { public void invoke() { toggleCollisionRectangles(); } };
		ButtonFunction collisionTiles = new ButtonFunction() { public void invoke() { toggleCollisionTiles(); } };
		ButtonFunction chunkBoundaries = new ButtonFunction() { public void invoke() { toggleChunkBoundaries(); } };
		ButtonFunction characterSpeedDown = new ButtonFunction() { public void invoke() { characterSpeedDown(); } };
		ButtonFunction characterSpeedUp = new ButtonFunction() { public void invoke() { characterSpeedUp(); } };
		ButtonFunction fullscreenToggle = new ButtonFunction() { public void invoke() { Game.fullscreenToggle(); } };
		
		String s1, s2, s3, s4, s5;
		if(collisionDetectionIsOn) s1 = "Collision Detection Is On"; else s1 = "Collision Detection Is Off";
		if(collisionRectsAreOn) s2 = "Collision Rectangles Are On"; else s2 = "Collision Rectangles Are Off";
		if(collisionTilesAreOn) s3 = "Collision Tiles Are On"; else s3 = "Collision Tiles Are Off";
		if(chunkBoundariesAreOn) s4 = "Chunk Boundaries Are On"; else s4 = "Chunk Boundaries Are Off";
		if(fullscreenIsOn) s5 = "Fullscreen Toggle"; else s5 = "Fullscreen Toggle";

		String[] strings = {s1, s2, s3, s4, "Speed-", "Speed+", s5};
		ButtonFunction[] functions = {collisionDetection, collisionRectangles, collisionTiles, chunkBoundaries, characterSpeedDown, characterSpeedUp, fullscreenToggle};
		
		menuButtons = new PlainUIButton[functions.length];
		for(int i = 0; i < menuButtons.length; i++) {
			RectInt r2 = r.getRectInt();
			menuButtons[i] = new PlainUIButton(r2.x, r2.y*i, r2.w, r2.h, strings[i], functions[i], batch);
		}
	}
	
	public void fullscreenToggle() {
		Game.fullscreenToggle();
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
		float s = Game.hero.getCurrentSpeed();
		if(s > 0.09f) Game.hero.setCurrentSpeed(s - 0.1f);
	}
	
	private void characterSpeedUp() {
		float s = Game.hero.getCurrentSpeed();
		if(s < 0.9f) Game.hero.setCurrentSpeed(s + 0.1f);
	}

}
