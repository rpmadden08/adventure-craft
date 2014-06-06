package com.madbros.tileminer.GameStates;

import static com.madbros.tileminer.Constants.*;

import com.badlogic.gdx.Gdx;
import com.madbros.tileminer.*;

public class CauldronState extends MainState {
	public CauldronState() {
		type = State.CAULDRON;
		input = new CauldronStateInput();
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	protected void updateStates() {
		Game.animationSystem.updateInventory(Game.hero, Game.inventory, Game.mobController);
		Game.hero.update();
		Game.mobController.update();
		Game.level.update();
		Game.debugger.update();
		Game.inventory.craftingMenu.refreshCraftSlots(Game.inventory.craftingMenu.currentCraftableList);
	}
	
	@Override
	protected void renderHud() {
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.setShader(Game.defaultShader);
		Game.batch.begin();
			Game.renderSystem.renderHud(Game.inventory);
			Game.renderSystem.renderText(Game.inventory, Game.batch);
			Game.renderSystem.renderInventory(Game.hero, Game.inventory);
			//Game.renderSystem.renderInventoryPage2(Game.hero, Game.inventory);
			Game.renderSystem.renderCrafting(Game.inventory);
			Game.renderSystem.renderArmorSlots(Game.hero, Game.inventory);
			Game.renderSystem.renderCauldron(Game.inventory);
			Game.inventory.craftingMenu.render();
			
			Game.renderSystem.renderInventoryText(Game.inventory, Game.batch);
			Game.renderSystem.renderCraftingText(Game.inventory, Game.batch);
			Game.renderSystem.renderCrafting(Game.inventory);
			Game.renderSystem.renderHeldItem(Game.inventory);
		Game.batch.end();
	}
}
