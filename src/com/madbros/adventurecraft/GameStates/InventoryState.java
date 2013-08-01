package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Cells.Cell;
import com.madbros.adventurecraft.Utils.Helpers;
import com.madbros.adventurecraft.Utils.Rect;

public class InventoryState extends MainState {
	public InventoryState() {
		type = State.INVENTORY;
	}
	
	@Override
	protected void getAdditionalKeyboardInput(boolean eventState, int key) {
		if(eventState) {
			switch(key) {
				case Keyboard.KEY_E: Game.toggleInventoryState(); break; //TODO: Game.inventory.dropItemsInCraftingGrid();
			}
		}
	}
	
	@Override
	protected void getAdditionalMouseInput() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Cell[][] cells = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted};
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				if(mouseRect.detectCollision(cells[i][j].cellRect)) {
					cells[i][j].isHighlighted = true;
					
					if(leftMouseButtonPressed) cells[i][j].handleLeftClick(Game.inventory);
					else if(rightMouseButtonPressed) cells[i][j].handleRightClick(Game.inventory);
				} else {
					cells[i][j].isHighlighted = false;
				}
			}
		}
	}
	
	@Override
	protected void updateStates() {
		Game.hero.update(delta);
		Game.level.update();
		Game.debugger.update();
		Game.hero.cycleWalkAnimation();
	}
	
	@Override
	protected void renderTextures() {
		super.renderTextures();
		Game.inventory.renderFull();
	}
	
	@Override
	protected void renderText() {
		super.renderText();
		Game.inventory.renderTextFull();
	}
}
