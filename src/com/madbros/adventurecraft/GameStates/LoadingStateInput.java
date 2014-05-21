package com.madbros.adventurecraft.GameStates;
import com.badlogic.gdx.Input.Keys;
import com.madbros.adventurecraft.Menus.Menu;

public class LoadingStateInput extends BasicInput {
	

	
	public LoadingStateInput() {
		//this.mainMenu = mainMenu;
	}
	
	@Override
	public boolean scrolled(int amount) {
		//super.scrolled(amount);
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
//		super.touchUp(x, y, pointer, button);
//		for(int i = 0; i < mainMenu.menuButtons.length; i++) {
//			mainMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
//		}
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
//		super.touchDown(x, y, pointer, button);
//		for(int i = 0; i < mainMenu.menuButtons.length; i++) {
//			mainMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
//		}
		return false;
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
//		super.mouseMoved(x, y);
//		for(int i = 0; i < mainMenu.menuButtons.length; i++) {
//			mainMenu.handleMouseMove(x, y);
//		}
//		
//		additionalMouseMove();
		return false;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
//		mouseMoved(x, y);
		return false;
	}
	
	@Override
	public boolean keyUp(int key) {
//		super.keyUp(key);
		return false;
	}
	
	@Override
	public boolean keyDown(int key) {
//		super.keyDown(key);
//		additionalKeyDown(key);


		return false;
	}
	
	public void additionalKeyDown(int key){
		
	}
	
	public void additionalKeyUp(int key) {
		
	}
	
	public void additionalMouseDown() {
		
	}
	
	public void additionalMouseMove() {
		
	}
	
	public void additionalMouseUp() {
		
	}
}
