package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.newdawn.slick.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.madbros.adventurecraft.*;


public class DebugMainState extends GameState {
	public DebugMainState() {
		type = State.DEBUG_MAIN;
	}
	
	//the following four functions are altered in the inventory state
	public void handleOtherKeyboardInput(boolean eventState, int key) {
		if(eventState)
			switch(key) {
			case Keyboard.KEY_E:
				Game.inventory.toggleInventoryState();
				Game.character.turnWalkingOff();
				break;
			case Keyboard.KEY_W:
				Game.character.startMoving(UP);
				break;
			case Keyboard.KEY_A:
				Game.character.startMoving(LEFT);
				break;
			case Keyboard.KEY_S:
				Game.character.startMoving(DOWN);
				break;
			case Keyboard.KEY_D:
				Game.character.startMoving(RIGHT);
				break;
			case Keyboard.KEY_9:
				System.out.println(Game.level.chunkRect.x + ", " + Game.level.chunkRect.y);	//print debug info here
				break;
			}
		else {
			switch(key) {
			case Keyboard.KEY_W:
				Game.character.stopMoving(UP);
				break;
			case Keyboard.KEY_A:
				Game.character.stopMoving(LEFT);
				break;
			case Keyboard.KEY_S:
				Game.character.stopMoving(DOWN);
				break;
			case Keyboard.KEY_D:
				Game.character.stopMoving(RIGHT);
				break;
			}
		}
	}
	
	public void handleOtherMouseInput(boolean eventState, int key) {
		if(eventState) {
			switch(key) {
			case LEFT_MOUSE_BUTTON:
				Game.inventory.useActiveItem(LEFT);
				break;
			case RIGHT_MOUSE_BUTTON:
				Game.inventory.useActiveItem(RIGHT);
				break;
			}
		}
	}
	
	public void handleOtherInput(boolean leftMouseButtonPressed, boolean rightMouseButtonPressed) {
		
	}
	
	public void updateOther() {
		
	}
	
	@Override
	public void getInput() {
		while(Keyboard.next()) {
			boolean eventState = Keyboard.getEventKeyState();
			int key = Keyboard.getEventKey();
			
			if(eventState) {
				switch(key) {
				case Keyboard.KEY_M:
					Game.debugger.toggle();
					break;
				case Keyboard.KEY_P:
					Game.debugger.toggleMenu();
					break;
				case Keyboard.KEY_1:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_2:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_3:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_4:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_5:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_6:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_7:
					Game.inventory.changeSelectedItemTo(key);
					break;
				case Keyboard.KEY_8:
					Game.inventory.changeSelectedItemTo(key);
					break;
				}
			}
			handleOtherKeyboardInput(eventState, key);
		}
		
		boolean leftMouseButtonPressed = false;
		boolean rightMouseButtonPressed = false;
		boolean leftMouseButtonUp = false;
		
		while(Mouse.next()) {
			boolean eventState = Mouse.getEventButtonState();
			int key = Mouse.getEventButton();
			
			if(eventState) {
				switch(key) {
				case LEFT_MOUSE_BUTTON:
					leftMouseButtonPressed = true;
					break;
				case MIDDLE_MOUSE_BUTTON:
					break;
				case RIGHT_MOUSE_BUTTON:
					rightMouseButtonPressed = true;
					break;
				}
			} else {
				switch(key) {
				case LEFT_MOUSE_BUTTON:
					leftMouseButtonUp = true;
				}
			}
			
			handleOtherMouseInput(eventState, key);
		}
		
		if(Game.debugger.menuIsActive) {
			Game.debugger.checkMouseInput(leftMouseButtonPressed, leftMouseButtonUp);
		}
		
		int mouseD = Mouse.getDWheel();
		if(mouseD > 0) {
			Game.inventory.mouseWheelDidIncrement();
		} else if(mouseD < 0) {
			Game.inventory.mouseWheelDidDecrement();
		}
		
		handleOtherInput(leftMouseButtonPressed, rightMouseButtonPressed);
	}
	
	@Override
	public void update() {
		Game.debugger.start();
		
		int delta = Time.getDelta();
		Game.character.update(delta);
		Game.level.update();
		Game.debugger.update();
		updateOther();
		
		Game.debugger.stopUpdateTime();
	}
		
	@Override
	public void render() { 
		Game.debugger.start();
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		Game.batch.begin();
			Color.white.bind();
			Game.level.render();
			Game.character.render();
			renderMenu();
			Game.debugger.render();
		Game.batch.end();

		Game.inventory.renderFont();
		Game.debugger.renderFont();
		
		Game.debugger.stopRenderTime();
		
		Display.update();
		Display.sync(FRAME_RATE);
	}
	
	public void renderMenu() {
		Game.inventory.render();
	}
}