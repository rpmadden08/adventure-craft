package com.madbros.adventurecraft.Menus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.adventurecraft.UI.*;

public class Menu {
	public UIButton[] menuButtons;
	
	public Menu() {
		setupMenu();
	}
	
	public void setupMenu() {

	}
	
	public void handleMouseInput(boolean leftMousePressed, boolean leftMouseUp) {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].handleMouseInput(leftMousePressed, leftMouseUp);
		}
	}
	
	public void handleMouseMove(int x, int y) {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].handleMouseMove(x, y);
		}
	}
	
//	public void handleMouseUp(int x, int y, int button) {
//		for(int i = 0; i < menuButtons.length; i++) {
//			menuButtons[i].handleMouseUp(x, y, button);
//		}
//	}
	
	public void render() {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].render();
		}
	}
	
	public void renderText() {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].renderText();
		}
	}
}
