package com.madbros.adventurecraft.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.madbros.adventurecraft.Game;
import com.madbros.adventurecraft.UI.*;

import static com.madbros.adventurecraft.Constants.*;

public class Menu {
	public UIButton[] menuButtons;
//	public SelectUIButton[] selectUIButtons;
	
	public Menu(SpriteBatch batch) {
		setupMenu(batch);
	}
	
	public void setupMenu(SpriteBatch batch) {

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
//		Texture backdrop1 = new Texture(Gdx.files.internal("data/backdrop1.png"));
//		//Texture logo = new Texture(Gdx.files.internal("data/logo.png"));
//		TextureRegion backdrop1Region = new TextureRegion( backdrop1, 0, 0, INITIAL_WINDOW_WIDTH, INITIAL_WINDOW_HEIGHT );
//	    
//		Game.batch.draw(backdrop1, 0, INITIAL_WINDOW_HEIGHT, INITIAL_WINDOW_WIDTH, -INITIAL_WINDOW_HEIGHT);
//		//Game.batch.draw(logo, INITIAL_WINDOW_WIDTH /2 - logo.getWidth()*2 / 2, logo.getHeight()*2+40, logo.getWidth()*2, -logo.getHeight()*2);
//			
	}
	
	public void renderText() {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].renderText();
		}
	}
}
