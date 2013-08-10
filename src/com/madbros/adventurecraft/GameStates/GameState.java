package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.madbros.adventurecraft.*;

public abstract class GameState {
	public State type;
	protected int delta;
	protected boolean leftMouseButtonPressed, rightMouseButtonPressed, leftMouseButtonUp, rightMouseButtonUp, middleMouseButtonPressed, middleMouseButtonUp;
	
	protected void getKeyboardInput() {}
	
	protected void getMouseButtonInput() {
		leftMouseButtonPressed = rightMouseButtonPressed = leftMouseButtonUp = rightMouseButtonUp = middleMouseButtonPressed = middleMouseButtonUp = false;
		
		while(Mouse.next()) {
			boolean eventState = Mouse.getEventButtonState();
			int key = Mouse.getEventButton();
			
			if(eventState) {
				switch(key) {
					case LEFT_MOUSE_BUTTON: leftMouseButtonPressed = true; break;
					case MIDDLE_MOUSE_BUTTON: middleMouseButtonPressed = true; break;
					case RIGHT_MOUSE_BUTTON: rightMouseButtonPressed = true; break;
				}
			} else {
				switch(key) {
					case LEFT_MOUSE_BUTTON: leftMouseButtonUp = true; break;
					case MIDDLE_MOUSE_BUTTON: middleMouseButtonUp = true; break;
					case RIGHT_MOUSE_BUTTON: rightMouseButtonUp = true; break;
				}
			}
		}
	}
	
	protected void updateStates() {}
	
	protected void renderTextures() {}
	protected void renderText() {}
	
	public void getInput() {
		getMouseButtonInput();
		getKeyboardInput();
	}
	
	public void update() {
		Game.debugger.start();
		delta = Time.getDelta();
		updateStates();
		
		Game.debugger.stopUpdateTime();
	}
	
	public void render() {
		Game.debugger.start();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		Game.batch.begin();
			renderTextures();
			if(Game.debugMenu.menuIsActive) Game.debugMenu.render();
		Game.batch.end();

		renderText();
		if(Game.debugger.isDebugging) Game.debugger.renderText(Game.hero.aRect);
		if(Game.debugMenu.menuIsActive) Game.debugMenu.renderText();
		
		Game.debugger.stopRenderTime();
		
		Display.update();
		Display.sync(FRAME_RATE);
	}
}