package com.madbros.tileminer.GameStates;

import com.badlogic.gdx.Input.Keys;
import com.madbros.tileminer.Game;
import com.madbros.tileminer.Slots.Slot;
import com.madbros.tileminer.Utils.Helpers;
import com.madbros.tileminer.Utils.Rect;



public class MainStateInput extends BasicInput {
	public boolean altKeyDown = false;
	public boolean shiftKeyDown = false;
	public boolean clickedAButton = false;
	@Override
	public boolean scrolled(int amount) {
		super.scrolled(amount);
		if(amount > 0) Game.inventory.mouseWheelDidIncrement();
		else if(amount < 0) Game.inventory.mouseWheelDidDecrement();
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer, button);
		Game.inventory.stopUsingItem(button);
		if(Game.debugMenu.menuIsActive) Game.debugMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		if(Game.gameMainMenu.menuIsActive) Game.gameMainMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		Game.hero.attackButtonReleased = true;
		Game.level.hasPlacedItemOnClick = false;
		
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer, button);
		if(Game.debugMenu.menuIsActive) Game.debugMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		if(Game.gameMainMenu.menuIsActive) Game.gameMainMenu.handleMouseInput(mouseLeftDown, mouseLeftUp);
		additionalMouseDown();
		if(clickedAButton == false) {
			
			Game.inventory.useItem(button);
		}
			
		clickedAButton = false;
		return false;
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		super.mouseMoved(x, y);
		if(Game.debugMenu.menuIsActive) Game.debugMenu.handleMouseMove(x, y);
		if(Game.gameMainMenu.menuIsActive) Game.gameMainMenu.handleMouseMove(x, y);
		additionalMouseMove();
		return false;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		mouseMoved(x, y);
		return false;
	}
	
	@Override
	public boolean keyUp(int key) {
		super.keyUp(key);
		additionalKeyUp(key);
		return false;
	}
	
	@Override
	public boolean keyDown(int key) {
		super.keyDown(key);
		switch(key) {
		case Keys.M: Game.debugger.toggle(); break;
		case Keys.ESCAPE: Game.toggleMainMenu(); break;
		case Keys.P: Game.debugMenu.toggleMenu(); break;
		case Keys.NUM_1: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_2: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_3: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_4: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_5: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_6: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_7: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_8: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_9: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.NUM_0: Game.inventory.changeSelectedItemTo(key); break;
		case Keys.MINUS: Game.zoomOut(); break;
		case Keys.EQUALS: Game.zoomIn(); break;
		}

		additionalKeyDown(key);
		return false;
	}
	
	public void additionalKeyDown(int key){
		if(!Game.hero.isAttacking) {
			switch(key) {
				
				case Keys.T: System.out.println(Game.rgenseed); break;
				case Keys.W: Game.hero.moveUp(); break;
				case Keys.A: Game.hero.moveLeft(); break;
				case Keys.S: Game.hero.moveDown(); break;
				case Keys.D: Game.hero.moveRight(); break;
				case Keys.U: System.out.println(Game.debugger.getTimeDiff()); break;	//for debugging stuff
				case Keys.E: Game.toggleInventoryState(); break;
			}
		}
	}
	
	public void additionalKeyUp(int key) {
		if(!Game.hero.isAttacking) {
			switch(key) {
				case Keys.W: Game.hero.stopUp(); break;
				case Keys.A: Game.hero.stopLeft(); break;
				case Keys.S: Game.hero.stopDown(); break;
				case Keys.D: Game.hero.stopRight(); break;
			}
		}
	}
	
	public void additionalMouseDown() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
		Slot[][] slots = {Game.inventory.invBar};
		//droppedItemInSlot = false;
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					
//					if(mouseLeftDown && altKeyDown) slots[i][j].handleLeftClickCrafting(Game.inventory, Game.inventory.craftingMenu.craftableList, slots[i][j].item.itemsPossiblyCraftable);
//					else if(mouseLeftDown && shiftKeyDown) slots[i][j].handleLeftClickShift(Game.inventory);
//					else if(mouseRightDown && shiftKeyDown) slots[i][j].handleRightClickShift(Game.inventory);
					if(mouseLeftDown) {
						slots[i][j].handleLeftClickMainState(Game.inventory);
						clickedAButton = true;
					}
//					else if(mouseRightDown) slots[i][j].handleRightClick(Game.inventory);
				} 
			}
		}
	}
	
	public void additionalMouseMove() {
		Rect mouseRect = new Rect(Helpers.getX(), Helpers.getY(), 1, 1);
//		Game.inventory.craftingMenu.handleMouseMove(mouseRect.x, mouseRect.y);
		Slot[][] slots = {Game.inventory.invBar};
		for(int i = 0; i < slots.length; i++) {
			for(int j = 0; j < slots[i].length; j++) {
				if(mouseRect.detectCollision(slots[i][j].slotRect)) {
					slots[i][j].isHighlighted = true;
				} else {
					slots[i][j].isHighlighted = false;
				}
			}
		}
	}
	
	public void additionalMouseUp() {
		
	}
}
