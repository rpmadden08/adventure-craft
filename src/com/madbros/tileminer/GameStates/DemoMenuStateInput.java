package com.madbros.tileminer.GameStates;



import com.badlogic.gdx.Input.Keys;
import com.madbros.tileminer.Game;

public class DemoMenuStateInput extends MainStateInput {
	public boolean altKeyDown = false;
	public boolean droppedItemInSlot = false;
	public void additionalKeyDown(int key){
		switch(key) {
//			case Keys.E: Game.toggleInventoryState(); break; //TODO: Game.inventory.dropItemsInCraftingGrid();
//			case Keys.A: Game.inventory.craftingMenu.lastPage2(); break;
//			case Keys.D: Game.inventory.craftingMenu.nextPage2(); break;
//			case Keys.ALT_LEFT: altKeyDown = true; break; //TODO: Game.inventory.dropItemsInCraftingGrid();
		}
	}
	
	public void additionalKeyUp(int key) {
		
	}
	
	@Override
	public boolean keyDown(int key) {
		//super.keyDown(key);
		switch(key) {
		//case Keys.ESCAPE: Game.toggleMainMenu(); break;
		case Keys.M: Game.debugger.toggle(); break;
		}

		//additionalKeyDown(key);
		return false;
	}
	
	public void additionalMouseUp() {
		
	}
	
}
