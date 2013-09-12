package com.madbros.adventurecraft.Menus;

import com.madbros.adventurecraft.UI.*;

public class Menu {
	public UIButton[] menuButtons;
	
	public Menu() {
		setupMenu();
	}
	
	public void setupMenu() {

	}
	
	public void handleMouseInput(boolean leftMouseButtonPressed, boolean leftMouseButtonUp) {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].handleMouseInput(leftMouseButtonPressed, leftMouseButtonUp);
		}
	}
	
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
