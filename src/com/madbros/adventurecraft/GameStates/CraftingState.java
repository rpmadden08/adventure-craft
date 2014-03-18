package com.madbros.adventurecraft.GameStates;

import static com.madbros.adventurecraft.Constants.*;

import com.badlogic.gdx.Gdx;
import com.madbros.adventurecraft.*;

public class CraftingState extends MainState {
	public CraftingState() {
		type = State.CRAFTING;
		input = new CraftingStateInput();
		Gdx.input.setInputProcessor(input);
	}
	
	@Override
	protected void updateStates() {
		Game.animationSystem.updateInventory(Game.hero, Game.inventory, Game.mobController);
		Game.hero.update();
		Game.mobController.update();
		Game.level.update();
		Game.debugger.update();
	}
	
	@Override
	protected void renderHud() {
		Game.batch.setProjectionMatrix(Game.camera.combined);
		Game.batch.setShader(Game.defaultShader);
		Game.batch.begin();
			Game.renderSystem.renderHud(Game.inventory);
			Game.renderSystem.renderText(Game.inventory, Game.batch);
			Game.renderSystem.renderInventory(Game.hero, Game.inventory);
			Game.renderSystem.renderCrafting(Game.inventory);
			Game.renderSystem.renderCraftingText(Game.inventory, Game.batch);
			Game.renderSystem.renderInventoryText(Game.inventory, Game.batch);
		Game.batch.end();
	}
}
