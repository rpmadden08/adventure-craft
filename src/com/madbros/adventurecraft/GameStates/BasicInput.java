package com.madbros.adventurecraft.GameStates;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public abstract class BasicInput extends InputAdapter {
	public int mouseX, mouseY;
	int scrolledAmount;
	boolean mouseLeftDown, mouseLeftUp, mouseRightDown, mouseRightUp, mouseMiddleDown, mouseMiddleUp;
	
	@Override
	public boolean scrolled(int amount) {
		scrolledAmount = amount;
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		switch(button) {
			case(Input.Buttons.LEFT): mouseLeftDown = false; mouseLeftUp = true; break;
			case(Input.Buttons.MIDDLE): mouseMiddleDown = false; mouseMiddleUp = true; break;
			case(Input.Buttons.RIGHT): mouseRightDown = false; mouseRightUp = true; break;
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		switch(button) {
			case(Input.Buttons.LEFT): mouseLeftDown = true; mouseLeftUp = false; break;
			case(Input.Buttons.MIDDLE): mouseMiddleDown = true; mouseMiddleUp = false; break;
			case(Input.Buttons.RIGHT): mouseRightDown = true; mouseRightUp = false; break;
		}
		return false;
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		mouseX = x;
		mouseY = y;
		return false;
	}
	
	@Override
	public boolean keyUp(int key) {

		return false;
	}
	
	@Override
	public boolean keyDown(int key) {
		//escape for full screen
		return false;
	}
}
