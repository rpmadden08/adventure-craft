package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;

import com.madbros.adventurecraft.*;

public class DebugInventoryState extends DebugMainState {
	public DebugInventoryState() {
		type = State.DEBUG_INVENTORY;
	}
	
	public void checkHover(boolean leftMouseButtonPressed, boolean rightMouseButtonPressed) {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Cell[][] cells = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted};
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(mouseRect.detectCollision(cells[i][j].cellRect)) {
					cells[i][j].isHighlighted = true;
					if(leftMouseButtonPressed) {
						cells[i][j].handleLeftClick(Game.inventory);
					} else if(rightMouseButtonPressed) {
						cells[i][j].handleRightClick(Game.inventory);
					}
				} else {
					cells[i][j].isHighlighted = false;
				}
			}
		}
	}
	
	@Override
	public void handleOtherKeyboardInput(boolean eventState, int key) {
		if(eventState) {
			switch(key) {
			case Keyboard.KEY_E:
				Game.inventory.toggleInventoryState();
				//TODO: Game.inventory.dropItemsInCraftingGrid();
				break;
			}
		}
	}
	
	@Override
	public void handleOtherMouseInput(boolean eventState, int key) {
		
	}
	
	@Override
	public void handleOtherInput(boolean leftMouseButtonPressed, boolean rightMouseButtonPressed) {
		checkHover(leftMouseButtonPressed, rightMouseButtonPressed);
	}
	
	@Override
	public void updateOther() {
		Game.character.cycleWalkAnimation();
	}
	
	@Override
	public void renderMenu() {
		Game.inventory.renderFull();
	}
}
