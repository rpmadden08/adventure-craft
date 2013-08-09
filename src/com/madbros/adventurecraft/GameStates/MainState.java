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
				case Keyboard.KEY_E: Game.toggleInventoryState(); Game.hero.turnWalkingOff(); break;
				case Keyboard.KEY_W: Game.hero.startMoving(UP); break;
				case Keyboard.KEY_A: Game.hero.startMoving(LEFT); break;
				case Keyboard.KEY_S: Game.hero.startMoving(DOWN); break;
				case Keyboard.KEY_D: Game.hero.startMoving(RIGHT); break;
				case Keyboard.KEY_U: System.out.println(Game.debugger.getTimeDiff()); break;	//for debugging stuff
			}
		else {
			switch(key) {
				case Keyboard.KEY_W: Game.hero.stopMoving(UP); break;
				case Keyboard.KEY_A: Game.hero.stopMoving(LEFT); break;
				case Keyboard.KEY_S: Game.hero.stopMoving(DOWN); break;
				case Keyboard.KEY_D: Game.hero.stopMoving(RIGHT); break;
			}
		}
	}
	
	protected void getAdditionalMouseInput() {}
	
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
					case Keyboard.KEY_ESCAPE: Game.debugMenu.tester();
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
		//clear moved entities from level
		//movement phase
		//collision phase
		//update chunks if necessary phase
		//debug stuff
		Game.hero.update(delta);
		Game.level.update();
		Game.inventory.update();	//should be in input
		Game.debugger.update();
	}
		
	@Override
	protected void renderTextures() {
		Game.level.render();
		Game.hero.render();
//		Game.map.render(Game.level.activeBlocks);
		Game.inventory.render();
	}
	
	@Override
	protected void renderText() {
		Game.inventory.renderText();
	}
}