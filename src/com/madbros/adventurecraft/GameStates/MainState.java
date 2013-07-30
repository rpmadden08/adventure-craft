package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.madbros.adventurecraft.*;


public class MainState extends GameState {
	public MainState() {
		type = State.MAIN;
	}
	
	public MainState(boolean isQuickLoad) {
		type = State.MAIN;
		if(isQuickLoad) Game.createDefaultSaveGameIfNecessary();
	}
	
	protected void getAdditionalKeyboardInput(boolean eventState, int key) {
		if(eventState)
			switch(key) {
				case Keyboard.KEY_E: Game.toggleInventoryState(); Game.character.turnWalkingOff(); break;
				case Keyboard.KEY_W: Game.character.startMoving(UP); break;
				case Keyboard.KEY_A: Game.character.startMoving(LEFT); break;
				case Keyboard.KEY_S: Game.character.startMoving(DOWN); break;
				case Keyboard.KEY_D: Game.character.startMoving(RIGHT); break;
				case Keyboard.KEY_U: System.out.println(Game.debugger.getTimeDiff()); break;	//for debugging stuff
			}
		else {
			switch(key) {
				case Keyboard.KEY_W: Game.character.stopMoving(UP); break;
				case Keyboard.KEY_A: Game.character.stopMoving(LEFT); break;
				case Keyboard.KEY_S: Game.character.stopMoving(DOWN); break;
				case Keyboard.KEY_D: Game.character.stopMoving(RIGHT); break;
			}
		}
	}
	
	protected void getAdditionalMouseInput() {
		if(leftMouseButtonPressed) Game.inventory.useActiveItem(LEFT);
		else if(rightMouseButtonPressed) Game.inventory.useActiveItem(RIGHT);

		//level check mouse input for block highlightinGame...
	}
	
	@Override
	protected void getKeyboardInput() {
		while(Keyboard.next()) {
			boolean eventState = Keyboard.getEventKeyState();
			int key = Keyboard.getEventKey();
			
			if(eventState) {
				switch(key) {
					case Keyboard.KEY_M: Game.debugger.toggle(); break;
					case Keyboard.KEY_P: Game.debugMenu.toggleMenu(); break;
					case Keyboard.KEY_1: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_2: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_3: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_4: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_5: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_6: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_7: Game.inventory.changeSelectedItemTo(key); break;
					case Keyboard.KEY_8: Game.inventory.changeSelectedItemTo(key); break;
					//9
					//0
				}
			}
			getAdditionalKeyboardInput(eventState, key);
		}
	}
	
	@Override
	protected void getMouseButtonInput() {
		super.getMouseButtonInput();
		
		int mouseD = Mouse.getDWheel();
		if(mouseD > 0) Game.inventory.mouseWheelDidIncrement();
		else if(mouseD < 0) Game.inventory.mouseWheelDidDecrement();
		
		if(Game.debugMenu.menuIsActive) Game.debugMenu.handleMouseInput(leftMouseButtonPressed, leftMouseButtonUp);
		
		getAdditionalMouseInput();
	}
	
	@Override
	protected void updateStates() {
		Game.character.update(delta);
		Game.level.update();
		Game.debugger.update();
	}
		
	@Override
	protected void renderTextures() {
		Game.level.render();
		Game.character.render();
		Game.inventory.render();
	}
	
	@Override
	protected void renderText() {
		Game.inventory.renderText();
	}
}