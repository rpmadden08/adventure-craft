package com.madbros.tileminer.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Sprites.StaticSprite;
import com.madbros.tileminer.UI.*;


public class Menu {
	public UIButton[] menuButtons;
	public StaticSprite sprite;
	public StaticSprite sprite2;
	public StaticSprite howToPlaySprite;
//	public SelectUIButton[] selectUIButtons;
	
	public Menu(SpriteBatch batch) {
		setupMenu(batch);
		Texture backdrop1 = new Texture(Gdx.files.internal("data/backdrop1.png"));
		Texture logo = new Texture(Gdx.files.internal("data/logo.png"));
		Texture howToPlay = new Texture(Gdx.files.internal("res/howToPlay.png"));
		sprite = new StaticSprite(backdrop1, 0,0,1440,900,Game.batch);
		sprite2 = new StaticSprite(logo, 0,0,logo.getWidth(),logo.getHeight(),Game.batch);
		howToPlaySprite = new StaticSprite(howToPlay, 0,0,howToPlay.getWidth(),howToPlay.getHeight(),Game.batch);
	}
	
	public void setupMenu(SpriteBatch batch) {

	}
	public void delete(){};
	
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
		renderBefore();
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].render();
		}
		renderExtra();
	}
	
	public void renderText() {
		for(int i = 0; i < menuButtons.length; i++) {
			menuButtons[i].renderText();
		}
	}
	
	public void renderSplashScreen() {
		sprite.draw(0,0,0, Game.currentScreenSizeX, Game.currentScreenSizeY);
		sprite2.draw(Game.currentScreenSizeX /2 - sprite2.getWidth()*2 / 2, 20,0f, sprite2.getWidth()*2, sprite2.getHeight()*2);	
	
	}
	public void renderExtra() {
		
	}
	
	public void renderBefore() {
		
	}
	
}
