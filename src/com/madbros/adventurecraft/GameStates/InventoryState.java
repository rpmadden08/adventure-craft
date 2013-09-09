package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import org.lwjgl.input.Keyboard;

import com.madbros.adventurecraft.*;
import com.madbros.adventurecraft.Slots.Slot;
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
		Slot[][] slots = {Game.inventory.invBar, Game.inventory.invBag, Game.inventory.invCrafting, Game.inventory.invCrafted};
		
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					slots[i][j].isHighlighted = true;
					
					if(leftMouseButtonPressed) slots[i][j].handleLeftClick(Game.inventory);
					else if(rightMouseButtonPressed) slots[i][j].handleRightClick(Game.inventory);
				} else {
					slots[i][j].isHighlighted = false;
				}
			}
		}
	}
	
	@Override
	protected void updateStates() {
		Game.animationSystem.updateInventory(Game.inventory);
		Game.hero.update();
		Game.level.update();
		Game.debugger.update();
	}
	
	@Override
	protected void renderTextures() {
		super.renderTextures();
		Game.renderSystem.renderInventory(Game.inventory);
	}
	
	@Override
	protected void renderText() {
		super.renderText();
		Game.renderSystem.renderInventoryText(Game.inventory);
	}
}
